package com.example.spring2;

@Component
public class MyController {
    private MyService myService;

    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    public void doSomethingInController() {
        myService.doSomething();
    }

    public void hello() {
        System.out.println("Привіт від MyController!");
    }
}
