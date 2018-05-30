package com.q.spring.cloud.weather.service.Impl;

import com.q.spring.cloud.weather.service.CityDataService;
import com.q.spring.cloud.weather.util.XmlBuilder;
import com.q.spring.cloud.weather.vo.City;
import com.q.spring.cloud.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCity() throws Exception {

        //读取xml内容
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = br.readLine()) != null){
            buffer.append(line);
        }

        br.close();
        //xml转为对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObjet(CityList.class,buffer.toString());

        return cityList.getCityList();
    }
}
