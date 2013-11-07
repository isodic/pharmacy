package hr.foi.air.myPharmacy.interfaces;

import hr.foi.air.myPharmacy.types.User;

/**
 * Created by stroj on 06.11.13..
 */
public interface ILogin {


    public Boolean checkLogin(String username, String password);

    public String getPluginName();
}
