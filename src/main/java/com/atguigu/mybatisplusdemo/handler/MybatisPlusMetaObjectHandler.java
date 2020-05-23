package com.atguigu.mybatisplusdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author qyy
 * @create 2020-05-12 22:35
 */
@Configuration
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    //在mybatis-plus实现插入操作时，会执行该方法
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);

        //version版本号的初始化操作
        this.setFieldValByName("version",1,metaObject);
    }

    //在mybatis-plus实现更新操作时，会执行该方法
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
