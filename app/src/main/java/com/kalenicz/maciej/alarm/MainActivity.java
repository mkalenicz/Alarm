package com.kalenicz.maciej.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.media.AudioManager.STREAM_ALARM;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Intent startAlarmIntent;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        startAlarmIntent = new Intent(this, AlertActivity.class);
        pendingIntent = PendingIntent.getActivity(this, 0, startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        getMillisFromTimePicker();
    }

    private long getMillisFromTimePicker() {
        int hour = getHour();
        int minutes = getMinutes();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+1);
        return calendar.getTimeInMillis();
    }

    private int getHour() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? timePicker.getHour() : timePicker.getCurrentHour();
    }

    private int getMinutes() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? timePicker.getMinute() : timePicker.getCurrentMinute();
    }

    public void setAlarm(View mainView) {
        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                getMillisFromTimePicker(),
                pendingIntent
        );
    }

    public void runAlarm(View view) {
        startActivity(startAlarmIntent);
    }
}