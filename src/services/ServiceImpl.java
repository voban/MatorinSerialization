package services;

/**
 * Created by Владимир on 20.12.2016.
 */
public class ServiceImpl implements Service{
    @Override
    public int doAction(String par1, int par2) {
        return 2 * par2;
    }
}
