package com.atguigu.crowd.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name="body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {

    @XmlElement(name="city")
    private List<City> cityList;

}


