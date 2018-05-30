package com.q.spring.cloud.weather.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.q.spring.cloud.weather.common.WeatherTool;
import com.q.spring.cloud.weather.service.WeatherDataService;
import com.q.spring.cloud.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    private static final long TIME_OUT = 1800L; //1800s

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataBycityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    private WeatherResponse doGetWeather(String uri) {

        String key = uri;
        String strBody = null;
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //先查询缓存,有则去缓存数据
        if (stringRedisTemplate.hasKey(key)) {
            logger.info("Redis has data");
            strBody = ops.get(key);
        } else {
            //缓存没有,在调用服务接口来获取
            try {
                //调用工具方法讲返回的数据解压并处理成字符串
                logger.info("Redis don't has data");
                strBody = WeatherTool.getWeather(uri);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //数据写入缓存
            ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

        }

        try {
            //将字符串转换成对象
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("Error",e);
        }
        return weatherResponse;
    }


    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放入缓存
     * @param uri
     */
    private void saveWeatherData(String uri){
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        //缓存没有,在调用服务接口来获取
        try {
            //调用工具方法讲返回的数据解压并处理成字符串
            logger.info("Redis don't has data");
            strBody = WeatherTool.getWeather(uri);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //数据写入缓存
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
    }
}
