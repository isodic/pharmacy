package hr.foi.air.myPharmacy.plugins.UserData;

import java.util.concurrent.ExecutionException;

import hr.foi.air.myPharmacy.core.ServiceAsyncTaskUserCredentials;
import hr.foi.air.myPharmacy.interfaces.IUsers;
import hr.foi.air.myPharmacy.types.User;

/*
remarks -> AV
 */
public class Users_Web_Service implements IUsers {

    /*
    Dobiva podatke o korisniku i provjerava te podatke unutar baze uz pomoc funkcije checkUserData
    ta funkcija vraca podatke o korisniku, ali mijenja Id_korisnika ovisno o uspješnosti prijave
    Ako je neuspješna prijava unutar ID_korisnika upisuje 0, inače upisuje originalni user id.
     */
    @Override
    public User checkLogin(String username, String  password){
        return ((checkUserData(username, password)));
    };

    /*
    Kao parametar se prosljeduju svih podaci za jednog korisnika i poziva se async task
    Async task komunicira sa web servisom i pokusava dohvatiti podatke
    Ako je rezultat komunikacije, odnosno (User)result == null, ili result.getId()==0, dogodila se ili pogreška ili korisnik ne postoji u bazi
    Ako je povratna vrijednost=1, korisnik je uspjesno registriran i preusmjerava se na ponovni login
     */
    public int registerUser(User one_user){
        ServiceAsyncTaskUserCredentials asyncTaskUser=new ServiceAsyncTaskUserCredentials();
        asyncTaskUser.execute(new String[]{"2",
                              one_user.getUsername(),one_user.getPassword(),
                              one_user.getIme(),one_user.getPrezime(),one_user.getSpol(),
                              one_user.getEmail()});
        User result = null;
        try {
            result = asyncTaskUser.get();
        } catch (InterruptedException e) {}
        catch (ExecutionException e) {}
        if(result==null)return -1;
        else if(result.getId()==0)return 0;
        else return 1;
    }

    /*
    SLicno kao i kod funkcije register, samo se ovdje provjerava da li korisnik postoji ili ne postoji u bazi. Rezultat provjere,
    odnosno podaci o korisniku se vracaju u obliku varijable User i user se vraca nazad na Activity
     */
    private User checkUserData(String username, String password){
        ServiceAsyncTaskUserCredentials asyncTaskUser=new ServiceAsyncTaskUserCredentials();
        asyncTaskUser.execute(new String[]{"1",username,password});
        User result = null;
        try {
            result = asyncTaskUser.get();
        } catch (InterruptedException e) {}
          catch (ExecutionException e) {}
        return result;
    }

    @Override
    public String getPluginName(){
        return "PHP->mysql";
    };
}
