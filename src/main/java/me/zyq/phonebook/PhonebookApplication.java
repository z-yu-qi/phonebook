package me.zyq.phonebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("me.zyq.phonebook.springboot.mapper")
public class PhonebookApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PhonebookApplication.class, args);
    }
    //为了打包springboot项目
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(this.getClass());
//    }
}
