package com.q.spring.cloud.weather.service;

import com.q.spring.cloud.weather.vo.WeatherResponse;

public interface WeatherDataService {

    /**
     * 根据城市ID查询天气数据
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName
     * @return
     */
    WeatherResponse getDataBycityName(String cityName);

    /**
     * 根据城市id同步天气
     * @param cityId
     */
    void syncDateByCityId(String cityId);
}
