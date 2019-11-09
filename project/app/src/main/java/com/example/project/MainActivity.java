package com.example.project;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity  {

    private EditText username;
    private EditText password_1;
    private Button login_1;
    private Button signup_1;
    private CheckBox employee;
    private CheckBox patient;
    private MyDBHandler db;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.etUsername);
        password_1 = (EditText)findViewById(R.id.etPassword);
        login_1= (Button)findViewById(R.id.btnLogin);
        signup_1= (Button)findViewById(R.id.btnSignUp);
        employee= (CheckBox)findViewById(R.id.cbEmployee2);
        patient= (CheckBox)findViewById(R.id.cbPatient2);
        db= new MyDBHandler(this);

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(employee.isChecked()){
                    id= "employee";
                }
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(patient.isChecked()){
                    id= "patient";
                }
            }
        });

        login_1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                String name= username.getText().toString();
                String password= password_1.getText().toString();

                if(name.equals("Admin") && password.equals("5T5ptQ")){
                    if(id== "employee" || id== "patient"){
                        Toast.makeText(getApplicationContext(), "This is an admin account. Any identity should not be chosen.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Welcome Admin, you are logged in as an admin.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Main4Activity.class));
                    }
                }

                boolean findUser= db.findUser(name, password, id);
                if(findUser== true){
                    Toast.makeText(getApplicationContext(), "Welcome "+ name+ ", you are logged in as "+ id+ ".", Toast.LENGTH_SHORT).show();
                    if(id== "employee"){
                        startActivity(new Intent(MainActivity.this, Main10Activity.class));
                    }else if(id== "patient"){
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Wrong username or password.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signup_1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
            }
        });

    }




}

