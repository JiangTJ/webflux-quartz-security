package com.example.quartz.dao;

import com.example.quartz.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/9/5.
 */
@Mapper
@Component
public interface UserDao {

    User getUser();

}
