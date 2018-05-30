package com.q.spring.cloud.weather.service;

import com.q.spring.cloud.weather.vo.Weather;

public interface WeatherReportService {

    /**
     * 根据城市id查询天气
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
