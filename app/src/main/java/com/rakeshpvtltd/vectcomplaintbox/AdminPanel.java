package com.rakeshpvtltd.vectcomplaintbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminPanel extends AppCompatActivity {

    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        b1=(Button)findViewById(R.id.addnewadmin);
        b2=(Button)findViewById(R.id.checkcomplaint);
        b3 =(Button)findViewById(R.id.logout);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminPanel.this, AddNewAdmin.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminPanel.this, CheckComplaint.class);
                startActivity(i);
            }
        });

        b3
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                Toast.makeText(getApplicationContext(), "Logout successfully! ", Toast.LENGTH_LONG).show();
                Intent i=new Intent(AdminPanel.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(AdminPanel.this, HomeActivity.class));
        finishAffinity();
        finish();

    }
}