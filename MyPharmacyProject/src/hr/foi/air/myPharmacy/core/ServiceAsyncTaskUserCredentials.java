package hr.foi.air.myPharmacy.core;

import android.os.AsyncTask;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hr.foi.air.myPharmacy.types.User;
import hr.foi.air.myPharmacy.types.WebServiceLinks;

/*
remarks -> AV
Async task kreiran za dohvaćanje i upravljanje korisničkim podacima, koristi se unutar Register, Login aktivnosti
Switch case služi da bi se ustvrdilo tko je pozvao i zašto async task ->
ako je params[0]=1 onda je to login
ako je params[0]=2 onda je to register
 */
public class ServiceAsyncTaskUserCredentials extends AsyncTask<String, Void, User> {

    @Override
    protected User doInBackground(String... params) {
        User one_user = new User("","",0,"","","","");
        String username=  params[1];
        String password= params[2];
        ResponseHandler<String> handler = new BasicResponseHandler();
        HttpClient httpClient = new DefaultHttpClient();
        switch (Integer.parseInt(params[0])){

            /*
            Login provjera
             */
            case 1:
                String result = null;
                String sql=WebServiceLinks.getUrl(0,0,username,password,null,null,null,null);
                HttpGet request = new HttpGet(sql);
                try {
                    result = httpClient.execute(request, handler);
                    httpClient.getConnectionManager().shutdown();
                    if (result != null) {
                        JSONArray arr = new JSONArray(result);
                        JSONObject juser= (JSONObject) arr.get(0);
                        if(juser.getString("ID_korisnika")=="Wrong_Credentials")return one_user;
                        one_user = createUser(one_user, juser);
                        return one_user;
                    }
                } catch (ClientProtocolException e) {}
                  catch (IOException e) {}
                  catch (JSONException e) {}
                return one_user;

            /*
            Register provjera
             */
            case 2:
                String ime=params[3];
                String prezime=params[4];
                String spol=params[5];
                String email=params[6];
                /*
                Uz pomoć statičke metode i klase unutar koje se nalaze svi relevantni linkovi
                koji se odnose na web servis, dohvaća se url.
                Prvi parametar predstavlja id grupe = 0 -> grupa UserCredentials
                Drugi parametar je specifični link koji se traži
                 */
                sql= WebServiceLinks.getUrl(0,1,username,password,email,spol,prezime,ime);
                request = new HttpGet(sql);

                try {
                    result = httpClient.execute(request, handler);
                    httpClient.getConnectionManager().shutdown();
                    if (result != null) {
                        JSONArray arr = new JSONArray(result);
                        JSONObject juser= (JSONObject) arr.get(0);
                        String msg=juser.getString("ID_korisnika");
                        String msgCmp="User_Exists";
                        if(msg.equals(msgCmp))return one_user;
                        one_user = createUser(one_user, juser);
                        return one_user;
                    }
                }

                catch (ClientProtocolException e) {}
                catch (IOException e) {}
                catch (JSONException e) {}
                one_user.setId(-1);
                return one_user;
        }
        one_user.setId(0);
        return one_user;
    }

    /*
    Funkcija za kreiranje nove instance korisnika
     */
    private User createUser(User one_user, JSONObject juser) throws JSONException {
        one_user=new User(
                juser.getString("Ime"),
                juser.getString("Prezime"),
                juser.getInt("ID_korisnika"),
                juser.getString("Korisnicko_ime"),
                juser.getString("Lozinka"),
                juser.getString("Email"),
                juser.getString("Spol")
        );
        return one_user;
    }


};
