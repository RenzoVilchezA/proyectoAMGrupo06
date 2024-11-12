package com.renzovilchez.proyectoamgrupo06;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.renzovilchez.proyectoamgrupo06.modelo.Cliente;

public class ClienteActivity extends AppCompatActivity {

    EditText direccionCliente, tipoPagoCliente, numeroPagoCliente;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        direccionCliente = findViewById(R.id.direccionCliente);
        tipoPagoCliente = findViewById(R.id.tipoPagoCliente);
        numeroPagoCliente = findViewById(R.id.numeroPagoCliente);

        dbHelper = new DatabaseHelper(this);
    }

    // Método para registrar un cliente con id_usuario
    public void onRegisterCliente(View view) {
        String direccion = direccionCliente.getText().toString().trim();
        String tipoPago = tipoPagoCliente.getText().toString().trim();
        String numeroPago = numeroPagoCliente.getText().toString().trim();

        if (direccion.isEmpty() || tipoPago.isEmpty() || numeroPago.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Suponiendo que ya tienes un id_usuario válido, lo pasas a la función
        String idUsuario = "1";  // Aquí deberías obtener el id del usuario registrado

        // Usamos el método para insertar el cliente
        long resultado = dbHelper.insertarCliente(idUsuario, direccion, tipoPago, numeroPago);

        if (resultado != -1) {
            Toast.makeText(this, "Cliente registrado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al registrar cliente", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para buscar un cliente por dirección
    public void onBuscarCliente(View view) {
        String direccion = direccionCliente.getText().toString().trim();

        if (direccion.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese la dirección del cliente para buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cliente cliente = dbHelper.buscarClientePorDireccion(direccion);

        if (cliente != null) {
            // Rellena los campos con los datos del cliente encontrado
            tipoPagoCliente.setText(cliente.getTipoPago());
            numeroPagoCliente.setText(cliente.getNumeroPago());
            Toast.makeText(this, "Cliente encontrado", Toast.LENGTH_SHORT).show();
        } else {
            // Cliente no encontrado
            Toast.makeText(this, "Cliente no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

}
