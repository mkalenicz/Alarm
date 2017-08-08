package com.kalenicz.maciej.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button testButton = (Button) findViewById(R.id.main_activity_test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startAlarmIntent = new Intent(MainActivity.this, AlertActivity.class);
                startActivity(startAlarmIntent);
            }
        });
    }

}
