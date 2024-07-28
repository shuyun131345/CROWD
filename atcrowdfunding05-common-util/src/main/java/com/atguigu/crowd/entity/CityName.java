package com.atguigu.crowd.entity;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name="name")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityName {

    @XmlAttribute(name="length")
    private String length;

    @XmlAttribute(name="type")
    private String type;
}
