package com.atguigu.crowd.entity;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name="city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    @XmlElement(name="id")
    private String id;

    @XmlElement(name="name")
    private CityName name;


    @XmlAttribute(name="area")
    private String area;
}
