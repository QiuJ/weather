package com.q.spring.cloud.weather.service;

import com.q.spring.cloud.weather.vo.City;

import java.util.List;

public interface CityDataService {


    /**
     * 获取city列表
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
