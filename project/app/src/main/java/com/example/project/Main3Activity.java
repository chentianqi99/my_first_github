package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private EditText userName, userPassword, comfirmedPassword, userEmail;
    private Button register;
    private TextView userLogin;
    private MyDBHandler db;
    private CheckBox employee, patient;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db= new MyDBHandler(this);

        userName= (EditText)findViewById(R.id.etuserName);
        userPassword= (EditText)findViewById(R.id.et2Password);
        comfirmedPassword= (EditText)findViewById(R.id.et2comfirmPassword);
        userEmail= (EditText)findViewById(R.id.etEmail);
        register= (Button)findViewById(R.id.btn2SignUp);
        userLogin= (TextView)findViewById(R.id.tvUserLogin);

        employee=(CheckBox)findViewById(R.id.cbEmployee);
        patient=(CheckBox)findViewById(R.id.cbPatient);

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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= userName.getText().toString();
                String email= userEmail.getText().toString();
                String password1= userPassword.getText().toString();
                String password2= comfirmedPassword.getText().toString();


                if(name.isEmpty() && email.isEmpty() && password1.isEmpty() && password2.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter more details.", Toast.LENGTH_SHORT).show();

                }else{
                    if(password1.equals(password2)){
                        boolean checkUsername= db.checkUsername(name);

                        if(checkUsername== true){
                            boolean insert= db.insert(name, email, password1, id);

                            if(insert== true){
                                Toast.makeText(getApplicationContext(), "You have registered successfully as a "+ id + ".", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Main3Activity.this, MainActivity.class));
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, MainActivity.class));

            }
        });


    }

}
