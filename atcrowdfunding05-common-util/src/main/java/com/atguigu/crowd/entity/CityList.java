package com.atguigu.crowd.entity;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name="cityList")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {
    @XmlElement(name="city")
    private List<City> cityList;

}
