package com.atguigu.mybatisplusdemo.mapper;

import com.atguigu.mybatisplusdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author qyy
 * @create 2020-05-11 21:59
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
