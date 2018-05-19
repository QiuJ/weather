package com.q.spring.cloud.weather.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * 响应对象
 */
@Data
public class WeatherResponse implements Serializable{
    private static final long serialVersionUID = 1L;

    private Weather data;

    private Integer status;

    private String desc;
}
