
package com.promotion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class
//})
@SpringBootApplication
@EnableScheduling // 注意这里的 @EnableScheduling
                  // 注解，它的作用是发现注解@Scheduled的任务并由后台执行。没有它的话将无法执行定时任务。
@MapperScan("com.promotion.dao")
public class PromotionMonitorApplication {

    public static void main(String[] args) {
        // SpringApplication.run(PromotionMonitorApplication.class, args);
        // System.setProperty("java.awt.headless", "true");

        SpringApplicationBuilder builder = new SpringApplicationBuilder(
                PromotionMonitorApplication.class);
        builder.headless(false).run(args);// 不加这句话会报java.awt.HeadlessException:
                                          // null
    }
}
