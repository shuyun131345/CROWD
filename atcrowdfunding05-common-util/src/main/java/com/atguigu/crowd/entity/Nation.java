package com.atguigu.crowd.entity;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name="request")
@XmlAccessorType(XmlAccessType.FIELD)
public class Nation {

    @XmlAttribute(name="nation")
    private String value;

    @XmlElement(name="head")
    private String head;

    @XmlElement(name="common")
    private String common;

    @XmlElement(name="entity")
    private String entity;

    @XmlElement(name="body")
    private Body body;


}
