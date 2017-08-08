package com.kalenicz.maciej.alarm;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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

import static android.media.AudioManager.STREAM_ALARM;

public class AlertActivity extends AppCompatActivity {

    private Button stopButton;
    private Vibrator vibrator;
    private Animation animation;
    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ringtone = setupRingtone();

        ringtone.play();

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

    @NonNull
    private Ringtone setupRingtone() {
        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarm);
        RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_ALARM, path);
        Ringtone ringtone = RingtoneManager.getRingtone(this, path);
        ringtone.setStreamType(STREAM_ALARM);
        return ringtone;
    }

    private void startAlarm(View mainView) {
        startVibration();
        mainView.startAnimation(animation);
    }

    private void stopAlarm() {
        vibrator.cancel();
        animation.cancel();
        ringtone.stop();
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
