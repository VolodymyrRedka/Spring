package com.example.spring2;
@Component
public class MyServiceImpl implements MyService {

    @Override
    public void doSomething() {
        System.out.println("Doing something in MyServiceImpl");
    }
}