package com.jk.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Conversion {

    public static <T> List<T> mapper(List<Map<String, Object>> result, Class<T> clas) {

        List<T> lst = new ArrayList<>();
        for (Map<String, Object> map : result){

            T instance = null;
            try {
                instance = clas.getDeclaredConstructor().newInstance();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
            List<Method> methods = Arrays.stream(clas.getDeclaredMethods()).toList();
            Map<String,Object> kv = new HashMap<>();


            map.forEach((k,v) -> {
                String finalname = "set";
                for (String s : k.split("_")){
                    finalname += s.substring(0, 1).toUpperCase() + s.substring(1);
                }
                kv.put(finalname, v);
            });

            T finalInstance = instance;
            kv.forEach((k, v) -> {
                for (Method m : methods){
                    if (m.getName().equals(k)){
                        try {
                            m.invoke(finalInstance, v);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            lst.add(instance);
        }

        return lst;
    }
}
