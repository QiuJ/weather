package com.q.spring.cloud.weather.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.q.spring.cloud.weather.common.WeatherTool;
import com.q.spring.cloud.weather.service.WeatherDataService;
import com.q.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;

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

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        String strBody = null;

        try {
            //调用工具方法讲返回的数据解压并处理成字符串
            strBody = WeatherTool.getWeather(uri);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            //将字符串转换成对象
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }
}
