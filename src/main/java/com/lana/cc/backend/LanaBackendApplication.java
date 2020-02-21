package com.lana.cc.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LANA CC的后台项目的入口
 *
 * @author LANA
 */

//此注解表示SpringBoot启动类
@SpringBootApplication
// 此注解表示动态扫描DAO接口所在包，实际上不加下面这条语句也可以找到
@MapperScan("com.lana.cc.backend.dao")

public class LanaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanaBackendApplication.class, args);
    }

}
