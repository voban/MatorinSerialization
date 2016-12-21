package homework;

import annotation.Cache;
import services.Service;
import services.ServiceImpl;

import java.io.*;

/**
 * Created by Владимир on 20.12.2016.
 */
public class Application {
    public static void main(String[] args) throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceImpl());
        System.out.println(service.doAction("Hello", 20));
        System.out.println(service.doAction("Hello123", 30));
    }
}
