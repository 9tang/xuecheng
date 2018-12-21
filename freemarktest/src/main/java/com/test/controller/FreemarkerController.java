package com.test.controller;

import com.test.model.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {
//    @Autowired
//    private RestTemplate restTemplate;


    @RequestMapping("/test1")
    public String freemarker(Map<String, Object> map){
        //向数据模型放数据
       // map.put("name","程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
// stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
//向数据模型放数据
        map.put("stus",stus);
//准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
//向数据模型放数据
        map.put("stu1",stu1);
//向数据模型放数据
        map.put("stuMap",stuMap);
        map.put("point", 102920122);
//返回模板文件名称
        return "test1";

    }
    @RequestMapping("/test2")
    public void test2() throws Exception{
         testGenerateHtml();
    }

    @RequestMapping("/test3")
    public void test3() throws Exception{
        testGenerateHtmlByString();
    }

    public void testGenerateHtmlByString() throws Exception {
//创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
//模板内容，这里测试时使用简单的字符串作为模板
        String templateString = "" +
                "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                " 名称：${name}\n" +
                " </body>\n" +
                "</html>";
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",templateString);
        configuration.setTemplateLoader(stringTemplateLoader);
        Template template = configuration.getTemplate("template","utf-8");
        //数据模型
        Map<String,Object> map = getModel();
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
        System.out.println(content);
        InputStream stream = IOUtils.toInputStream(content);
        FileOutputStream outputStream = new FileOutputStream(new File("d:/test3.html"));
        int copy = IOUtils.copy(stream,outputStream);
    }

        public void testGenerateHtml() throws Exception {
        //创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        String classpath = this.getClass().getResource("/").getPath();
        configuration.setDirectoryForTemplateLoading(new File(classpath + "/templates"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("test1.ftl");
        //数据模型
        Map<String,Object> map = getModel();
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
        System.out.println(content);
        InputStream stream = IOUtils.toInputStream(content);
        FileOutputStream outputStream = new FileOutputStream(new File("d:/test1.html"));
        int copy = IOUtils.copy(stream,outputStream);
    }


    public Map getModel(){
        Map<String, Object> map = new HashMap<>();
            //向数据模型放数据
            map.put("name","程序员");
            Student stu1 = new Student();
            stu1.setName("小明");
            stu1.setAge(18);
            stu1.setMoney(1000.86f);
            stu1.setBirthday(new Date());
            Student stu2 = new Student();
            stu2.setName("小红");
            stu2.setMoney(200.1f);
            stu2.setAge(19);
// stu2.setBirthday(new Date());
            List<Student> friends = new ArrayList<>();
            friends.add(stu1);
            stu2.setFriends(friends);
            stu2.setBestFriend(stu1);
            List<Student> stus = new ArrayList<>();
            stus.add(stu1);
            stus.add(stu2);
//向数据模型放数据
            map.put("stus",stus);
//准备map数据
            HashMap<String, Student> stuMap = new HashMap<>();
            stuMap.put("stu1",stu1);
            stuMap.put("stu2",stu2);
//向数据模型放数据
            map.put("stu1",stu1);
//向数据模型放数据
            map.put("stuMap",stuMap);
            map.put("point", 102920122);
            return map;
    }
}

