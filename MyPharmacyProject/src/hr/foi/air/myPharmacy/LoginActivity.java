package hr.foi.air.myPharmacy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.foi.air.myPharmacy.interfaces.ILogin;
import hr.foi.air.myPharmacy.plugins.UserData.Login;

/**
 * Created by stroj on 06.11.13..
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Context context=this;

        //Logiranje
        Button btnLogin=(Button)findViewById(R.id.btnLoginConfirm);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ILogin provjera=new Login();
                EditText editTextusername=(EditText)findViewById(R.id.editTextUsername_Login);
                String username = editTextusername.getText().toString();

                EditText editTextPassword=(EditText)findViewById(R.id.editTextPassword_Login);
                String password=editTextPassword.getText().toString();
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                if(provjera.checkLogin(username,password)==false){
                    CharSequence text = "Krivi podaci za prijavu!";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
                else {
                    CharSequence text = "Uspješna prijava!";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }


}
