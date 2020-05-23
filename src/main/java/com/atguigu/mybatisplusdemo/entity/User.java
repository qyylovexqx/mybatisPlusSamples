package com.atguigu.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author qyy
 * @create 2020-05-11 21:56
 */
@Data
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private  Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //版本号
    @TableField(fill=FieldFill.INSERT)
    @Version
    private Integer version;


    @TableLogic
    private Integer deleted;
}
