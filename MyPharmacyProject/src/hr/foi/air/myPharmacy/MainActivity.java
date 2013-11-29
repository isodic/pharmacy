package hr.foi.air.myPharmacy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Context context=this;

        //Users_Web_Service
        Button btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,LoginActivity.class);
                startActivity(i);
            }
        });

        //Kreiraj profil
        Button btnRacun=(Button)findViewById(R.id.btnCreateProfile);
        btnRacun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,RacunActivity.class);
                startActivity(i);
            }
        });
    }

}
