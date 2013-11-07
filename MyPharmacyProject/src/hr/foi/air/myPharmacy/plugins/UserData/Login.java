package hr.foi.air.myPharmacy.plugins.UserData;

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

import hr.foi.air.myPharmacy.interfaces.ILogin;

/**
 * Created by stroj on 07.11.13..
 */
public class Login implements ILogin {

    public Boolean checkLogin(String username, String  password){
        if(checkData(getUserData(username,password))==false)return false;
        return true;
    };


    private String getUserData(String username, String password){
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(
                "http://hamby.co.nf/Data.php?username="
                        + username+"&password="+password);
        ResponseHandler<String> handler = new BasicResponseHandler();
        String jsonResult = "[]";
        try {
            jsonResult = client.execute(request, handler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.getConnectionManager().shutdown();
        return jsonResult;
    }

    private boolean checkData(String jsonArrayString){
        JSONArray oneUser= null;
        try {
            oneUser = new JSONArray(jsonArrayString);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        JSONObject user= null;
        try {
            user = oneUser.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        String id= null;
        try {
            id = user.getString("ID korisnika");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        if(id=="0")return false;
        else return true;
    }

    public String getPluginName(){
        return "PHP->mysql";
    };
}
