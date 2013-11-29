package hr.foi.air.myPharmacy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by stroj on 07.11.13..
 */
public class ViewProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        final Context context=this;

        Button btnReminder_view_profile=(Button)findViewById(R.id.btnReminder_view_profile);
        btnReminder_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,ReminderActivity.class);
                startActivity(i);
            }
        });
    }
}
