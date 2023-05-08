package com.example.spring2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<Class<?>, Object> beans = new HashMap<>();

    public Context(Class<?> configClass) throws Exception {
        scanComponents(configClass);
        instantiateBeans();
        autowireBeans();
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

    private void scanComponents(Class<?> configClass) throws Exception {
        ComponentScan componentScanAnnotation = configClass.getDeclaredAnnotation(ComponentScan.class);
        String[] packagesToScan = componentScanAnnotation.value();
        for (String packageToScan : packagesToScan) {
            for (Class<?> clazz : ClassScanner.findClasses(packageToScan)) {
                if (clazz.isAnnotationPresent(Component.class)) {
                    beans.put(clazz, null);
                }
            }
        }
    }

    private void instantiateBeans() throws Exception {
        for (Class<?> clazz : beans.keySet()) {
            beans.put(clazz, clazz.getDeclaredConstructor().newInstance());
        }
    }

    private void autowireBeans() throws Exception {
        for (Object bean : beans.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    field.set(bean, beans.get(field.getType()));
                }
            }
        }
    }

}

