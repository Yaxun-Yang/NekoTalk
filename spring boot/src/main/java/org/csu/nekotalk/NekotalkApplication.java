package org.csu.nekotalk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("org.csu.nekotalk.persistence")
@CrossOrigin //表示允许跨域访问
public class NekotalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(NekotalkApplication.class, args);
    }

}
