package homework;

import java.lang.reflect.Proxy;

/**
 * Created by Владимир on 20.12.2016.
 */
public class CacheProxy {

    public <T> T cache(T par) {
        T object = (T) Proxy.newProxyInstance(par.getClass().getClassLoader(),
                par.getClass().getInterfaces(), new MyInvocationHandler(par));
        return object;
    }
}
