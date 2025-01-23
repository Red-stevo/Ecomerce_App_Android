package com.redstevo.ecomerce_app.Activities.LandingActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.redstevo.ecomerce_app.R;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.landing_page);

        RelativeLayout landingImage = findViewById(R.id.image_container);
        TextView landingTitle = findViewById(R.id.title_text);
        TextView landingMessage = findViewById(R.id.subtitle_text);
        Button getStartedBtn = findViewById(R.id.get_started_button);


        Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.top_down_movement);
        Animation slideRight = AnimationUtils.loadAnimation(this, R.anim.slide_right);

        landingImage.setAnimation(slideDown);
        landingTitle.setAnimation(slideRight);
        landingMessage.setAnimation(slideRight);

    }
}
