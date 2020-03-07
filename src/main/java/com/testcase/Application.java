package com.testcase;


/*
模拟待测应用：邮箱生成器
* */

public class Application {
    public String generrateEmail(String input){
        if(input==null){
            throw new RuntimeException("输入为null,请重新输入");
        }
        System.out.println(input+"@testfan.com");

        return input+"@testfan.com";

    }
}
