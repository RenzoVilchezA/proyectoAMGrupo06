package com.renzovilchez.proyectoamgrupo06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usuarioLogin, contraseniaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioLogin = findViewById(R.id.usuarioLogin);
        contraseniaLogin = findViewById(R.id.contraseniaLogin);
    }

    public void onLogin(View view) {
        String usuario = usuarioLogin.getText().toString();
        String contrasenia = contraseniaLogin.getText().toString();

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        if (dbHelper.validarLogin(usuario, contrasenia)) {
            // Login exitoso
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            // Error de login
            Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
    public void irRegistrarse(View view) {
        // Redirigir al apartado de registro
        Intent intent = new Intent(LoginActivity.this, RegistrarseActivity.class);
        startActivity(intent);
    }
}