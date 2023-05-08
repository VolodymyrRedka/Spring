package com.example.spring2;

public class Main {

    public static void main(String[] args) throws Exception {
        Context context = new Context(AppConfig.class);
        MyController controller = context.getBean(MyController.class);
        controller.hello();
    }

}