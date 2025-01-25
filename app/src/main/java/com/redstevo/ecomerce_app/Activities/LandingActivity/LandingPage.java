package com.redstevo.ecomerce_app.Activities.LandingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.redstevo.ecomerce_app.Activities.Login.LoginActivity;
import com.redstevo.ecomerce_app.Activities.Registration.RegistrationActivity;
import com.redstevo.ecomerce_app.R;

import java.util.Timer;
import java.util.TimerTask;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        RelativeLayout landingImage = findViewById(R.id.image_container);
        TextView landingTitle = findViewById(R.id.title_text);
        TextView landingMessage = findViewById(R.id.subtitle_text);
        Button getStartedBtn = findViewById(R.id.get_started_button);

        getStartedBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });


        Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.top_down_movement);
        Animation slideRight = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_left);

        landingImage.setAnimation(slideDown);
        landingTitle.setAnimation(slideRight);
        landingMessage.setAnimation(slideRight);
        getStartedBtn.setAnimation(slideUp);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                redirectLoginActivity();
            }
        }, 3000);

    }

    protected void redirectLoginActivity(){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
