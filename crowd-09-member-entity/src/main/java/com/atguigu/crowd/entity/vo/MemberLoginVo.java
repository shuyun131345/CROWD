package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginVo implements Serializable {
    private static final long serialVersionUID = -684979432754667710L;
    private Integer id;

    private String username;

    private String email;
}
