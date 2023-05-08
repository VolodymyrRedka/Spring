package com.example.spring2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner {
    public static List<Class<?>> findClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            URL resources = classLoader.getResource(path);
            File dir = new File(resources.getFile());
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    classes.addAll(findClasses(packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
