package com.q.spring.cloud.weather.service.Impl;

import com.q.spring.cloud.weather.service.WeatherDataService;
import com.q.spring.cloud.weather.service.WeatherReportService;
import com.q.spring.cloud.weather.vo.Weather;
import com.q.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse weatherResponse = weatherDataService.getDataByCityId(cityId);
        return weatherResponse.getData();
    }
}
