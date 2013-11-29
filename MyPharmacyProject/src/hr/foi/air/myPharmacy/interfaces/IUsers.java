package hr.foi.air.myPharmacy.interfaces;

import hr.foi.air.myPharmacy.types.User;

/*
remarks -> AV
 */
public interface IUsers {


    public User checkLogin(String username, String password);

    public String getPluginName();

    public int registerUser(User one_user);
}
