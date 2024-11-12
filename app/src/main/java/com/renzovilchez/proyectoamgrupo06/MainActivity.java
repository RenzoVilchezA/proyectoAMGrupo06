package com.renzovilchez.proyectoamgrupo06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botón Registrarse
        Button registrarseButton = findViewById(R.id.registrarse);
        registrarseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrarseActivity.class);
                startActivity(intent);
            }
        });

        // Botón Login
        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Botón Producto
        Button productoButton = findViewById(R.id.producto);
        productoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductoActivity.class);
                startActivity(intent);
            }
        });

        // Botón Cliente
        Button clienteButton = findViewById(R.id.cliente);
        clienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
                startActivity(intent);
            }
   });
}
}