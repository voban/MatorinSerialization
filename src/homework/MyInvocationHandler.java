package homework;

import annotation.Cache;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by Владимир on 20.12.2016.
 */
public class MyInvocationHandler implements InvocationHandler {
    private final Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class))
            try {
                Cache annotation = method.getAnnotation(Cache.class);
                List<Class> list = Arrays.asList(annotation.identityBy());
                if (list.size() == 0)
                    for (Object o : args)
                        list.add(o.getClass());
                String arguments = "";
                for (int i = 0; i < args.length; i++)
                    if (list.contains(args[i].getClass()))
                        arguments += "arg" + i + "=" + args[i];
                File file = new File(annotation.filePath(), annotation.fileNamePrefix());
                if (!file.exists()) {
                    file.createNewFile();
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    Map<String, Object> map = new HashMap<>();
                    Object result = method.invoke(object, args);
                    map.put(arguments, result);
                    out.writeObject(map);
                    out.close();
                    return result;
                } else {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    Map<String, Object> loadMap = (Map<String, Object>) in.readObject();
                    in.close();
                    if (loadMap.get(arguments) != null) {
                        System.out.println("Данные загружены из памяти!");
                        return loadMap.get(arguments);
                    } else {
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                        Object result = method.invoke(object, args);
                        loadMap.put(arguments, result);
                        out.writeObject(loadMap);
                        out.close();
                        return result;
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handler(e);
            }
        return method.invoke(object, args);
    }
}
