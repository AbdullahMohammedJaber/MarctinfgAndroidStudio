package com.example.socialmedia.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class SignupScreen extends AppCompatActivity {
    public static  String uId = "";
    public static SharedPreferences sharedPref ;
    FirebaseAuth user;
    EditText username;
    EditText password;
    EditText email;
    EditText phone;
    String emailEd;
    String phoneEd;
    String passEd;
    String userEd;
    TextView create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        user = FirebaseAuth.getInstance();
        FirebaseApp.getInstance();
        username = findViewById(R.id.usernameSign);
        password = findViewById(R.id.passwordSign);
        email = findViewById(R.id.EmailSign);
        phone = findViewById(R.id.phoneSign);
        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEd = email.getText().toString();
                phoneEd = phone.getText().toString();
                passEd = password.getText().toString();
                userEd = username.getText().toString();
                if ( !TextUtils.isEmpty(emailEd)||! TextUtils.isEmpty(phoneEd)||!TextUtils.isEmpty(passEd)||!TextUtils.isEmpty(userEd))
                {
                    user.createUserWithEmailAndPassword(emailEd,passEd).addOnCompleteListener(SignupScreen.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                uId = Objects.requireNonNull(task.getResult().getUser()).getUid();

                                HashMap<String, String> toMap = new HashMap<>();
                                toMap.put("id",uId);
                                toMap.put("phone",phoneEd);
                                toMap.put("password",passEd);
                               toMap.put("email",emailEd);

                                toMap.put("name",userEd);

                                FirebaseFirestore.getInstance().collection("User").document(Objects.requireNonNull(task.getResult().getUser()).getUid())
                                        .set(toMap);
                                sharedPref = SignupScreen.this.getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("USER_ID",uId);
                                editor.apply();
                                editor.commit();
                                Toast.makeText(SignupScreen.this,"The Sign up Successfully",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(SignupScreen.this, HomeLayout.class);
                                SignupScreen.this.startActivity(i);
                                SignupScreen.this.finish();
                            }
                            else{
                                Toast.makeText(SignupScreen.this,"The Sign up Error",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(SignupScreen.this,"The Credential Null",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
