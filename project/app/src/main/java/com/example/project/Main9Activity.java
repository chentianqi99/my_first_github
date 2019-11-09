package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main9Activity extends AppCompatActivity {


    private EditText username;
    private Button delete, viewAll;
    private TextView goBack;
    private MyDBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);


        username= (EditText)findViewById(R.id.etUsernameDelete);
        delete= (Button)findViewById(R.id.btnDelete);
        viewAll= (Button)findViewById(R.id.btnVeiwAll);
        goBack= (TextView)findViewById(R.id.tvGoback);
        db= new MyDBHandler(this);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= username.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter more details. ", Toast.LENGTH_SHORT).show();
                }

                boolean findUser= db.findUser(name);
                if(findUser== true){

                    Integer delete= db.deleteUser(name);
                    if(delete> 0){
                        Toast.makeText(getApplicationContext(), "User " +name+ " has been removed.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Deletion fails.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "The user you want to delete does not exist.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor= db.showAll();
                if(cursor.getCount()== 0){
                    Toast.makeText(getApplicationContext(), "No item to show at this time.", Toast.LENGTH_SHORT).show();
                }else{
                    StringBuffer buffer= new StringBuffer();
                    while(cursor.moveToNext()){
                        buffer.append("username:"+ cursor.getString(0)+ "\n");
                        buffer.append("password: "+ cursor.getString(1)+ "\n");
                        buffer.append("email: "+ cursor.getString(2)+ "\n");
                        buffer.append("identity: "+ cursor.getString(3)+ "\n\n");
                    }

                    show("User", buffer.toString());
                }

            }
        });


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main9Activity.this, Main4Activity.class));
            }
        });

    }

    private void show(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

