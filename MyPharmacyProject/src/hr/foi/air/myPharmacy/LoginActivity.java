package hr.foi.air.myPharmacy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.foi.air.myPharmacy.interfaces.IUsers;
import hr.foi.air.myPharmacy.plugins.UserData.Users_Web_Service;

/*
remarks ->AV
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Context context=this;

        //Logiranje
        Button btnRacun=(Button)findViewById(R.id.btnLoginConfirm);
        btnRacun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IUsers provjera=new Users_Web_Service();
                EditText editTextusername=(EditText)findViewById(R.id.editTextUsername_Login);
                String username = editTextusername.getText().toString();

                EditText editTextPassword=(EditText)findViewById(R.id.editTextPassword_Login);
                String password=editTextPassword.getText().toString();
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                if(provjera.checkLogin(username,password).getId()==0){
                    CharSequence text = getString(R.string.WrongLogin);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    CharSequence text = getString(R.string.CorrectLogin);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Intent i=new Intent(context,ViewProfileActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
