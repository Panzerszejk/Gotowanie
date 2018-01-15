package com.example.konrad.gotowanie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.konrad.gotowanie.JSON.RegisterCheckJSON;
import com.example.konrad.gotowanie.JSON.RegisterJSON;
import com.example.konrad.gotowanie.R;

public class Register extends AppCompatActivity {

    private EditText loginField,passwordField,confirmField,emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        loginField = findViewById(R.id.Login);
        passwordField = findViewById(R.id.Haslo);
        emailField = findViewById(R.id.Email);
        confirmField = findViewById(R.id.Confirm);

    }

    public void registerClick(View view){
        String username = loginField.getText().toString();
        String password = passwordField.getText().toString();
        String email = emailField.getText().toString();
        String confirm= confirmField.getText().toString();

        if(username.length()<6||username.length()>20) {
            Toast toast = Toast.makeText(this, "Login powinien zawierać od 6 do 20 znaków", Toast.LENGTH_LONG);
            toast.show();
            Log.d("Register","Dlugosc loginu");
        }else{
            if(password.length()<6||password.length()>20) {
                Toast toast = Toast.makeText(this, "Haslo powininno zawierać od 6 do 20 znaków", Toast.LENGTH_LONG);
                toast.show();
                Log.d("Register","Dlugosc hasla");
            }else{
                if(email.isEmpty()){
                    Toast toast = Toast.makeText(this, "Nie wpisano adresu e-mail", Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("Register",Integer.toString(username.length()));
                }else{
                    if(password.equals(confirm)) {
                        new RegisterCheckJSON(this).execute(username, password, email);
                    }else {
                        Toast toast = Toast.makeText(this, "Hasło i potwierdzenie hasła nie zgadzają się", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        }
    }

}
