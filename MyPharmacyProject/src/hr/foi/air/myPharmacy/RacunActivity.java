package hr.foi.air.myPharmacy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import hr.foi.air.myPharmacy.interfaces.IUsers;
import hr.foi.air.myPharmacy.plugins.UserData.Users_Web_Service;
import hr.foi.air.myPharmacy.types.User;

/*
remarks -> AV
 */
public class RacunActivity extends Activity {

    /*
    Kreiraju se listeneri za register button i za checkboxove
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racun);
        final Context context=this;
        final int duration = Toast.LENGTH_SHORT;

        /*
        Kako bi uvijek jedan checkbox ostao upaljen(za spol)
         */
        final CheckBox chkZensko =(CheckBox)findViewById(R.id.chkZensko_racun);
        final CheckBox chkMusko =(CheckBox)findViewById(R.id.chkMusko_racun);

        chkZensko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkZensko.isChecked()){
                    chkMusko.setChecked(false);
                }
                else
                    chkMusko.setChecked(true);
            }
        });

        chkMusko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkMusko.isChecked()){
                    chkZensko.setChecked(false);
                }
                else
                    chkZensko.setChecked(true);
            }
        });

        /*
        Ako se klikne na register, prvo se dohvate podaci sa forme
        Pa se napravi validacija.
        Ako je validacija ispravna, posalji podatke funkciji register koja komunicira sa web servisom
         */
        Button btnRacun=(Button)findViewById(R.id.btnRegisterConfirm);
        btnRacun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                /*
                Dohvacanje podataka sa forme
                 */
                String txtUsername=((EditText)findViewById(R.id.editTxtUsername_racun)).getText().toString();
                String txtPassword=((EditText)findViewById(R.id.editTextPassword_racun)).getText().toString();
                String txtIme=((EditText)findViewById(R.id.editTxtName_racun)).getText().toString();
                String txtSurname=((EditText)findViewById(R.id.editTxtSurname_racun)).getText().toString();
                String txtEmail=((EditText)findViewById(R.id.editTextEmail_racun)).getText().toString();
                EditText editTextPasswordConfirm=(EditText)findViewById(R.id.editTextPasswordConfirm_racun);
                CheckBox chkSpol=(CheckBox)findViewById(R.id.chkMusko_racun);
                String txtSpol;

                if(chkSpol.isChecked())txtSpol="M";
                else txtSpol="Z";

                User one_user=new User(
                        txtIme,
                        txtSurname,
                        0,
                        txtUsername,
                        txtPassword,
                        txtEmail,
                        txtSpol
                );

                /*
                Validacija usernamea
                 */
                if(one_user.getUsername()==""||one_user.getUsername().length()<4){
                    CharSequence text;
                    if(one_user.getUsername()=="") text = getString(R.string.usernameRequired);//
                    else text = getString(R.string.usernameMinLength);//
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }

                String pass_confirm=editTextPasswordConfirm.getText().toString();
                /*
                Validacija passworda
                 */
                if(one_user.getPassword().length()<6||(!one_user.getPassword().equals(pass_confirm))){
                    CharSequence text;
                    if(one_user.getPassword().length()<6) text = getString(R.string.passwordMinLength);//
                    else text = getString(R.string.passwordMissmatch);//
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                if(one_user.getEmail().length()<6||(!one_user.getEmail().contains("@"))){
                    CharSequence text;
                    if(one_user.getEmail().length()<6)text = getString(R.string.emailRequired);//
                    else text = getString(R.string.emailFormatWrong);//
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }

                /*
                Ako je prosla validacija, salju se podaci funkciji registerUser, koja vraca rezultat komuniciranja sa web servisom
                Ako je rezultat==0, username već postoji u bazi
                Ako je rezultat==1, registracija je uspješno izvršena
                Ako je rezultat==-1, dogodio se error(offline, pogreske itd.)
                 */
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                IUsers provjera=new Users_Web_Service();
                int rezultat=provjera.registerUser(one_user);
                if(rezultat==0){
                    CharSequence text =getString(R.string.usernameExists);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if(rezultat==1){
                    CharSequence text = getString(R.string.registrationSuccessful);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Intent i=new Intent(context,LoginActivity.class);
                    startActivity(i);
                }
                else {
                    CharSequence text = getString(R.string.error);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
