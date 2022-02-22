package com.rakeshpvtltd.vectcomplaintbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class AdminLogin extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    TextView t1,t2;
    Button b1;
    EditText e1,e2;
    AwesomeValidation awesomeValidation;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])"+       // 1 number
                    "(?=.*[a-z])"+         // 1 lower case
                    "(?=.*[A-Z])"+          // at least 1 uper case
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        t1=(TextView) findViewById(R.id.gotoRegister);
        t2=(TextView)findViewById(R.id.forgotPassword);
        b1=(Button) findViewById(R.id.btnLogin);
        e1 =(EditText)findViewById(R.id.inputEmail);
        e2 = (EditText)findViewById(R.id.inputPassword);

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.inputEmail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.inputPassword,PASSWORD_PATTERN,R.string.invalid_password_);

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                if(awesomeValidation.validate())
//                {
//                    progressDialog.show();
//                    String email = e1.getText().toString();
//                    String password = e2.getText().toString();
//                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                    DatabaseReference databaseReference= firebaseDatabase.getReference("Admin");
//                    databaseReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            int childrenCount = (int) snapshot.getChildrenCount();
////                            int i=0;
//
//                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
////                                i=i+1;
//                                String post = String.valueOf(postSnapshot.child("email").getValue());
////                                Log.d("childe name", String.valueOf(postSnapshot));
//
//                                if(post.equals(email))
//                                {
////                                    Log.d("Get Data", post);
//                                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<AuthResult> task) {
//                                            if(task.isSuccessful())
//                                            {
//
//                                                if (firebaseAuth.getCurrentUser().isEmailVerified()){
//                                                    e1.setText("");
//                                                    e2.setText("");
//                                                    progressDialog.hide();
//                                                    Intent i=new Intent(AdminLogin.this, AdminPanel.class);
//                                                    startActivity(i);
//                                                    finish();
//                                                }
//                                                else
//                                                {
//                                                    progressDialog.hide();
//                                                    Toast.makeText(AdminLogin.this, "Please Verify Your Email", Toast.LENGTH_SHORT).show();
//                                                    e1.setText("");
//                                                    e2.setText("");
//                                                }
//
//
//                                            }
//                                            else
//                                            {
//                                                progressDialog.hide();
//                                                Toast.makeText(AdminLogin.this, "Email Id or Password is Wrong", Toast.LENGTH_SHORT).show();
//                                                e1.setText("");
//                                                e2.setText("");
//                                            }
//                                        }
//                                    });
//
//                                }
//                                else
//                                {
//                                    progressDialog.hide();
////                                    Toast.makeText(TeacherLogin.this, "User Not Found", Toast.LENGTH_SHORT).show();
//                                    e1.setText("");
//                                    e2.setText("");
//
//                                }
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            progressDialog.hide();
//                            e1.setText("");
//                            e2.setText("");
//                            Toast.makeText(AdminLogin.this, "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//
//                }
//                else
//                {
//                    Toast.makeText(AdminLogin.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
//                    e1.setText("");
//                    e2.setText("");
//                }
//            }
//        });

//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent i=new Intent(AdminLogin.this, AddNewAdmin.class);
//                startActivity(i);
//            }
//        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate())
                {
                    progressDialog.show();
                    String email = e1.getText().toString();
                    String password = e2.getText().toString();

                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                    e1.setText("");
                                    e2.setText("");
                                    progressDialog.hide();
                                    Intent i=new Intent(AdminLogin.this, AdminPanel.class);
                                    startActivity(i);
                                    finish();
                                }
                                else
                                {
                                    progressDialog.hide();
                                    Toast.makeText(AdminLogin.this, "Please Verify Your Email", Toast.LENGTH_SHORT).show();
                                    e1.setText("");
                                    e2.setText("");
                                }
                            }

                            else
                            {
                                e1.setText("");
                                e2.setText("");
                                progressDialog.hide();
                                Toast.makeText(AdminLogin.this, "Email Id or Password is Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

                else
                {
                    Toast.makeText(AdminLogin.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(AdminLogin.this, AdminForgotPassword.class);
                startActivity(i);
            }
        });
    }
    public void onBackPressed()
    {

        startActivity(new Intent(AdminLogin.this, HomeActivity.class));
        finishAffinity();
        finish();

    }
}