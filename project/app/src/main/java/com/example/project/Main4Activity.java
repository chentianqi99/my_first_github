package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {
    Button buttonService;
    Button buttonAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        buttonService = (Button) findViewById(R.id.button);
        buttonAccount = (Button) findViewById(R.id.button2);

        buttonService.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Main4Activity.this, Main5Activity.class));
            }
        });
        buttonAccount.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                startActivity(new Intent(Main4Activity.this, Main9Activity.class));
            }
        });
    }
}
