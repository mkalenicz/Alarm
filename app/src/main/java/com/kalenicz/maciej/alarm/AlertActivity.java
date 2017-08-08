package com.kalenicz.maciej.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class AlertActivity extends AppCompatActivity {

    private Button stopButton;
    private Vibrator vibrator;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        View mainView = findViewById(R.id.alert_view);
        animation = setupAnimation();
        startAlarm(mainView);

        stopButton = (Button) findViewById(R.id.stop_button);

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAlarm();
            }
        });
    }

    private void startAlarm(View mainView) {
        startVibration();
        mainView.startAnimation(animation);
    }

    private void stopAlarm() {
        vibrator.cancel();
        animation.cancel();
        finish();
    }

    private void startVibration() {
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, 100));
        } else {
            long[] pattern = {0, 1000, 100};
            vibrator.vibrate(pattern, 0);
        }
    }

    @NonNull
    private Animation setupAnimation() {
        final Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }
}
