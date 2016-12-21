package services;

import annotation.Cache;

/**
 * Created by Владимир on 20.12.2016.
 */
public interface Service {

    @Cache(filePath = "C:\\results", identityBy = {String.class})
    int doAction(String par1, int par2);
}
