package com.atguigu.mybatisplusdemo;

import com.atguigu.mybatisplusdemo.entity.User;
import com.atguigu.mybatisplusdemo.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisplusdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;


    //查询
    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //添加操作
    @Test
    public void addUser(){
        //这里还用到了自动注入一些字段，比如修改时间
        User user=new User();
        user.setName("xqx");
        user.setAge(24);
        user.setEmail("xqx@qq.com");
        int insert=userMapper.insert(user);
        System.out.println(insert);
    }

    //修改操作
    @Test
    public void updateUser(){
        User user=new User();
        user.setId(2L);
        user.setAge(120);
        int row = userMapper.updateById(user);
        System.out.println(row);
    }


    //测试乐观锁
    @Test
    public void testOptimisticLock(){
        //先往数据库中插入一条带有version的记录，执行insertUser()方法，用于测试

        //先查询出 需要更新的测试数据
        User user = userMapper.selectById(1264028683312574466L);

        //第一次更新email
        user.setEmail("sushi.@nju.cn");
        int result = userMapper.updateById(user);
        System.out.println("result="+result);

        //中间
        System.out.println(user);

        //第二次更新age
        user.setAge(20);
        int res = userMapper.updateById(user);
        System.out.println("res="+res);


    }

    @Test
    public void insertUser(){
        User user=new User();
        user.setName("sushi");
        user.setAge(18);
        user.setEmail("sushi.@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("新增用户：苏轼"+(insert==1?"成功":"失败"));
    }



    @Test
    public void testSelectById(){
        //根据id查询单条记录
        User user = userMapper.selectById(1264028683312574466L);
        System.out.println(user);

        //根据多个id批量查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L,2L,3L));
        System.out.println(users);
    }

    @Test
    public void testSelectByMap(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","sushi");
        map.put("age","20");
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }


    @Test
    public void testPage(){
        //1、创建Page对象
        //传入两个参数：当前页和 每页显示记录数
        Page<User> page=new Page<>(1,3);

        //调用Mybatis-plus分页查询的方法
        //调用Mybatis-plus查询后，会将所有数据封装到Page对象里面去
        userMapper.selectPage(page,null);

        //通过page对象获取分页信息
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数
    }


    @Test
    public void testDeleteById(){
        //根据id删除某条记录
        int res = userMapper.deleteById(1L);
        System.out.println(res);

        //根据多个id批量删除多条记录
        int result= userMapper.deleteBatchIds(Arrays.asList(2L,3L));
        System.out.println(result);
    }


    @Test
    public void testSelectQuery(){
        //复杂查询
        QueryWrapper<User> wrapper =new QueryWrapper<>();

        //通过QueryWrapper 设置条件

        //ge、gt、le、lt
        wrapper.ge("age",30);
        List<User> users=userMapper.selectList(wrapper);
        System.out.println(users);
    }
}















