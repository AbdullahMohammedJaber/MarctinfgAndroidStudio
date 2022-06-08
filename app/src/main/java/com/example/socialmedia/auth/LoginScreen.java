package com.example.socialmedia.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia.R;
import com.example.socialmedia.screen.HomeLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import static com.example.socialmedia.auth.SignupScreen.uId;

public class LoginScreen extends AppCompatActivity {
    TextView sign;
    FirebaseAuth user;
    EditText username;
    EditText password;
    TextView login;
    String email;
    String pass;
    public static SharedPreferences sharedPref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        sign = findViewById(R.id.sup);
        user = FirebaseAuth.getInstance();
        FirebaseApp.getInstance();
        username = findViewById(R.id.usrusr);
        password = findViewById(R.id.passwrd);
        login = findViewById(R.id.logiin);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LoginScreen.this,SignupScreen.class);
                LoginScreen.this.startActivity(i);


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email =  username.getText().toString();
                pass =   password.getText().toString();
             if ( !TextUtils.isEmpty(email)||! TextUtils.isEmpty(pass))
             {
                 user.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             uId = task.getResult().getUser().getUid();
                             Toast.makeText(LoginScreen.this,"The Login Successfully",Toast.LENGTH_LONG).show();
                             sharedPref = LoginScreen.this.getPreferences(Context.MODE_PRIVATE);
                             SharedPreferences.Editor editor = sharedPref.edit();
                             editor.putString("USER_ID",uId);
                             editor.apply();
                             editor.commit();
                             Intent i = new Intent(LoginScreen.this, HomeLayout.class);
                             LoginScreen.this.startActivity(i);
                             LoginScreen.this.finish();
                         }
                         else{
                             Toast.makeText(LoginScreen.this,"The Login Error",Toast.LENGTH_LONG).show();

                         }
                     }
                 });
             }
             else {
                 Toast.makeText(LoginScreen.this,"The Credential Null",Toast.LENGTH_LONG).show();

             }
            }
        });
    }
}
