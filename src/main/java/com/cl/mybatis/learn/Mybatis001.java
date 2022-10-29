package com.cl.mybatis.learn;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.cl.mybatis.learn.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: chengli
 * @Date: 2018/11/24 12:00
 */
public class Mybatis001 {
    public static void main(String[] args) throws IOException {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        SqlSession session = sqlSessionFactory.openSession();
//        try {
//            UserMapper mapper = session.getMapper(UserMapper.class);
//            System.out.println(mapper.selectById(1));
//        } finally {
//            session.close();
//        }

        //防止修改系统日期
        Period period = Period.between(LocalDate.now(), LocalDate.of(2022,10,27));
        if (period.getDays() < 0) {
            System.out.println("period = " + period);
        }



    }
    public static void test(){
        try {
            ArrayList<String> arrayList = new ArrayList<>();

            File file = new File("d:\\test.txt");
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            int a=1;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
                if (a%2==0){
//                    System.out.println("insert" + arrayList.get(0));
//                    System.out.println("param" + arrayList.get(1));

                    JSONObject paramMap = new JSONObject();
                    paramMap.put("sourceSql", arrayList.get(0));
                    paramMap.put("param", arrayList.get(1));
                    //System.out.println("arrayList= " + arrayList.get(1).substring(0,16));
                    String body = HttpUtil.createPost("http://127.0.0.1:8081/sql/format")
                            .contentType("application/json")
                            .body(paramMap.toJSONString()).execute().body();
                    System.out.println(body+";");
                    Thread.sleep(5l);
                    arrayList.clear();
                }
                a++;
            }
            bf.close();
            inputReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
