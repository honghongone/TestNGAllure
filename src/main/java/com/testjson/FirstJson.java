package com.testjson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class FirstJson {


    public void demo1() throws IOException {
        //super.getClass()父类object中的getClass()的方法是static的所以调用的时候自己写的方法不能是static的
        //**.getResourceAsStream该方法可以以相对路径的方式获取一个输入流
        InputStream inputStream=super.getClass().getClassLoader().getResourceAsStream("first.json");//这里是相对路径的话只能填写文件名，前面不能有任何路径
        int len=-1;
        byte[] bs=new byte[100];
        StringBuffer strbu=new StringBuffer();
        while((len=inputStream.read(bs))!=-1){//将文件输入流的数据读到内存的bs变量中
              String str=new String(bs);//将每次读到的字节信息转换成字符串
              strbu.append(str);//将每次的字符串拼接到stringbuffer后面
        }
        String str=strbu.toString();//stringbuffer转string
        JSONObject jsonObject=new JSONObject(str);//string转json
        System.out.println(str);
        System.out.println("--------\n");
        System.out.println(jsonObject);
    }

    public static void demo2() throws IOException {
        //new File这个构造方法里面传的路径是绝对路径
        //通过FileUtils.readFileToString方法将文件转成string
        String s=FileUtils.readFileToString(new File("/Users/jiangzaohong/IdeaProjects/TestNGAllure/src/main/resources/first.json"));
        //JSONObject jsonObject=new JSONObject(s);//string转json
        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();
        jsonObject=jsonObject.fromObject(s);

        System.out.println("json:"+jsonObject);

    }

    //json数据生成json文件
    public static void demo3() throws IOException {
        //准备数据，map数据
        Map<String,Person> map=new HashMap<>();
        Person per1=new Person("zs",23,new Address("sh1","ww1"));
        Person per2=new Person("ls",24,new Address("sh2","ww2"));

        Person per3=new Person("ww",25,new Address("sh3","ww3"));
        map.put("zs",per1);
        map.put("ls",per2);
        map.put("ww",per3);
        //将map转成json
        JSONObject json=new JSONObject(map);
        Writer writer=new FileWriter("/Users/jiangzaohong/Documents/jzh5.json");
        //json.write(writer)将json数据写到文件输出流中去
        //不能写成json.write(new FileWriter("/Users/jiangzaohong/Documents/jzh1.json"))，要分开写因为要将write这个输出流给关闭才能把数据从流中写到文件里面去,不然的话文件里面是空的
        json.write(writer);
        writer.close();//一定要关闭将流的数据清理到文件里去，不然内容无法写进文件里面去

    }
    //string格式的json数组转成json数组
    //JSONArray jsonArray=new JSONArray(strArray);
    public  static void demo4(){
        String strArray="[{\"name\":\"ww\",\"age\":25},{\"name\":\"zs\",\"age\":23},{\"name\":\"ls\",\"age\":24}]";
        JSONArray jsonArray=new JSONArray(strArray);
        System.out.println(jsonArray);
    }


    //map转成json数组
    public  static void demo5(){
        Map<String,String> map=new HashMap<>();
        map.put("zs","1");
        map.put("ls","2");
        map.put("ww","3");
        //冲突：JSONArray既存在于org.json里面，又存在于json-lib库中，所以
        //写的时候要包名.类名
        net.sf.json.JSONArray jsonArray=new net.sf.json.JSONArray();
        //map>>jsonarray
        jsonArray=jsonArray.fromObject(map);
        //注意这里犯了一个错误就是没有将jsonArray.fromObject(map);赋值给jsonArray就直接打印jsonArray了，发现是空的，要赋值才可以
        System.out.println(jsonArray);
    }

    //json数组转成map
    public  static void demo6(){
        String strArray="[{\"name\":\"ww\",\"age\":25},{\"class\":\"zs\",\"school\":23},{\"homeadd\":\"ls\",\"scholladd\":24}]";
        //先将string转成jsonarray
        net.sf.json.JSONArray jsonArray=new net.sf.json.JSONArray();
        jsonArray=jsonArray.fromObject(strArray);
        Map<String,Object> map=new HashMap<>();
        //jsonarray 获取每一个json
        for(int i=0;i<jsonArray.size();i++){
            //jsonArray.get(i)返回的是object所以要强转成jsonobject
            net.sf.json.JSONObject jsonObject=(net.sf.json.JSONObject)jsonArray.get(i);
            //获取每个json的key和value
            Set<String> keys=jsonObject.keySet();//每个json的所有key
            for(String key:keys){//遍历每个json的所有key
                Object value= jsonObject.get(key);//获取key对应的value
                map.put(key,value);
            }
        }
        System.out.println(map);

    }



    public static void main(String[] args) throws IOException {
//        FirstJson j=new FirstJson();
//        j.demo1();
       //demo2();
        //demo3();
        //demo4();
        //demo5();
        demo6();

    }


}
