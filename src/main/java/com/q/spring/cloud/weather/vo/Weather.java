package com.q.spring.cloud.weather.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Weather implements Serializable{

    private static final long serialVersionUID = 1L;

    private String city;

    private String aqi;

    private String ganmao;

    private String wendu;

    private List<Forecast> forecast;

    private Yesterday yesterday;


}
