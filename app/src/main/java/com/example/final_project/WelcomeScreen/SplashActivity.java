package com.example.final_project.WelcomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.final_project.MainActivity;
import com.example.final_project.R;
import com.example.final_project.ServiceActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseAuth=FirebaseAuth.getInstance();



        // start main screen after 2seconds
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = sharedPreferences.getBoolean("firstTime", true);
                if (isFirstTime) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    //start main screen
                    startActivity(new Intent(SplashActivity.this, OnBoarding.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();//finish this activity

                } else {
                    checkUser();
                    //finish this activity
                }



            }
        }, 2000);//2000 means 2seconds
    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            //start main screen
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();//finish this activity
        } else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
            ref.child(firebaseUser.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            String userType = "" + snapshot.child("userType").getValue();
                            if (userType.equals("patient")) {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            } else if (userType.equals("Doctor")) {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

        }
    }
}