package com.example.steven.tamtam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.steven.tamtam.Models.User;
import com.example.steven.tamtam.apimanager.UserApiManager;
import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(btnLoginListener);
        btnRegister.setOnClickListener(btnRegisterListener);

    }

    public void finishActivity() {
        finish();
    }

    View.OnClickListener btnLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.buttonclick));

            User u = new User();
            u.setEmail(etEmail.getText().toString());
            u.setPassword(etPassword.getText().toString());
            u.setEmail("stevenschenk74@hotmail.com");
            u.setPassword("test123");
            try {
                if (UserApiManager.signIn(u, getBaseContext())) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finishActivity();
                } else {
                    Toast.makeText(getBaseContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    View.OnClickListener btnRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(intent);
        }
    };

}
