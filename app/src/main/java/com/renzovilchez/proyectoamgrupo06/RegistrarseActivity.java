package com.renzovilchez.proyectoamgrupo06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarseActivity extends AppCompatActivity {

    EditText nombreRegistrarse, emailRegistrarse, contraseniaRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        nombreRegistrarse = findViewById(R.id.nombreRegistrarse);
        emailRegistrarse = findViewById(R.id.emailRegistrarse);
        contraseniaRegistrarse = findViewById(R.id.contraseniaRegistrarse);
    }

    public void onRegister(View view) {
        String nombre = nombreRegistrarse.getText().toString();
        String email = emailRegistrarse.getText().toString();
        String contrasenia = contraseniaRegistrarse.getText().toString();

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        long id = dbHelper.insertarUsuario(nombre, email, contrasenia);

        if (id != -1) {
            // Registro exitoso
            Toast.makeText(RegistrarseActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistrarseActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            // Error en el registro
            Toast.makeText(RegistrarseActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
 }
}
}