package com.renzovilchez.proyectoamgrupo06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.renzovilchez.proyectoamgrupo06.modelo.Producto;

public class ProductoActivity extends AppCompatActivity {

    EditText nombreProducto, descripcionProducto, precioProducto, stockProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        nombreProducto = findViewById(R.id.nombreProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        precioProducto = findViewById(R.id.precioProducto);
        stockProducto = findViewById(R.id.stockProducto);
    }

    public void onRegisterProducto(View view) {
        String nombre = nombreProducto.getText().toString();
        String descripcion = descripcionProducto.getText().toString();
        double precio = Double.parseDouble(precioProducto.getText().toString());
        int stock = Integer.parseInt(stockProducto.getText().toString());

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        long idProducto = dbHelper.insertarProducto(nombre, descripcion, precio, stock);

        if (idProducto != -1) {
            // Producto registrado exitosamente
            Toast.makeText(ProductoActivity.this, "Producto registrado", Toast.LENGTH_SHORT).show();
        } else {
            // Error al registrar producto
            Toast.makeText(ProductoActivity.this, "Error al registrar producto", Toast.LENGTH_SHORT).show();
 }
}
    public void onBuscarProducto(View view) {
        String nombre = nombreProducto.getText().toString();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el nombre del producto para buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Producto producto = dbHelper.buscarProductoPorNombre(nombre);

        if (producto != null) {
            // Rellena los campos con los datos del producto encontrado
            nombreProducto.setText(producto.nombre);
            descripcionProducto.setText(producto.descripcion);
            precioProducto.setText(String.valueOf(producto.precio));
            stockProducto.setText(String.valueOf(producto.stock));
            Toast.makeText(this, "Producto encontrado", Toast.LENGTH_SHORT).show();
        } else {
            // Producto no encontrado
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
}
    }
    public void irPrincipal(View view) {
        // Redirigir al apartado de inicio
        Intent intent;
        intent = new Intent(ProductoActivity.this, MainActivity.class);
        startActivity(intent);
    }
}