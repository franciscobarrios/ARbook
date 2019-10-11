package dev.appy.ar.android.arbook.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import dev.appy.ar.android.arbook.R;
import dev.appy.ar.android.arbook.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, 1500);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        setTransitionAnimations();
    }

    protected void setTransitionAnimations() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
