package com.testcase;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ObjectInputStream;

public class HelloKittyTest {
    Application application;

    @BeforeClass
    public void beforeClass(){
        application=new Application();
    }

    @Epic("正向epic")
    @Story("正向story")
    @Step("通过DataProvider注入三组不同的数据，验证生成邮箱为数据@testfan.com")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider ="emaildemo",description = "邮箱生成的正向测试用例" )
    public void testGenerateEmail(String input,String email){
        Assert.assertEquals(application.generrateEmail(input),email);

    }


    @Epic("反向epic")
    @Story("反向story")
    @Step("输入null验证邮箱生成器")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "邮箱生成的反向测试用例" )
    public void testGenerateEmailException(String input,String email){
       // Assert.assertEquals(application.generrateEmail(input),email);
        String result=application.generrateEmail(null);

    }


    @DataProvider(name="emaildemo")
    public Object[][] generateEmail(){
        return new Object[][]{{"fan","fan@testfan.com"},{"qq","qq@testfan.com"},{"jzh","jzh@testfan.com"}};
    }
}
