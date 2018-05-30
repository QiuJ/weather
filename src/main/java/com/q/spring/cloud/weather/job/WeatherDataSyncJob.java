package com.q.spring.cloud.weather.job;

import com.q.spring.cloud.weather.service.CityDataService;
import com.q.spring.cloud.weather.service.WeatherDataService;
import com.q.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("Weather Data sync job. Start!");
        //获取成绩id列表
        List<City> cityList = null;
        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            logger.info("Exception",e);
        }
        //遍历城市id获取天气
        for(City city : cityList){
            String cityId = city.getCityId();
            logger.info("Weather Data sync job,cityId:"+cityId);
            weatherDataService.syncDateByCityId(cityId);
        }
        logger.info("Weather Data sync job. End!");
    }
}
