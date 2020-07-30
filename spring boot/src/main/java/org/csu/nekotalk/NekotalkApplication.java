package org.csu.nekotalk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("org.csu.nekotalk.persistence")
@CrossOrigin //表示允许跨域访问
public class NekotalkApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NekotalkApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(NekotalkApplication.class, args);
    }

}
