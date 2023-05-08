package com.example.spring2;

@Configuration
@ComponentScan("com.example")
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }

}
