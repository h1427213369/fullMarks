package cn.hlsxn.fullmarks;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:kaptcha.xml"})
@MapperScan(basePackages = "cn.hlsxn.fullmarks.mapper")
@SpringBootApplication
public class FullMarksApplication {
    public static void main(String[] args) {
        SpringApplication.run(FullMarksApplication.class);
    }


}
