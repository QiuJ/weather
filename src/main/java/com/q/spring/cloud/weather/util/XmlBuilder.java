package com.q.spring.cloud.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

public class XmlBuilder {

    public static Object xmlStrToObjet(Class<?> clazz, String xmlStr) throws Exception{

        Object xmlObject = null;

        Reader reader = null;

        JAXBContext context = JAXBContext.newInstance(clazz);

        //xml转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();

        reader = new StringReader(xmlStr);
        xmlObject = unmarshaller.unmarshal(reader);

        if(null != reader){
            reader.close();
        }

        return xmlObject;
    }
}
