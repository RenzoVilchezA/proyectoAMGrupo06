package com.renzovilchez.proyectoamgrupo06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.renzovilchez.proyectoamgrupo06.modelo.Cliente;
import com.renzovilchez.proyectoamgrupo06.modelo.Producto;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "AppSumi.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Usuario (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, email TEXT NOT NULL UNIQUE, contrasenia TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Cliente (id_cliente INTEGER PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER NOT NULL, direccion TEXT, tipo_pago TEXT, numero_pago TEXT, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))");
        db.execSQL("CREATE TABLE Producto (id_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, descripcion TEXT, precio REAL NOT NULL, stock INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE Venta (id_venta INTEGER PRIMARY KEY AUTOINCREMENT, id_cliente INTEGER NOT NULL, fecha TEXT NOT NULL, total REAL NOT NULL, FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente))");
        db.execSQL("CREATE TABLE CarritoCompras (id_carrito INTEGER PRIMARY KEY AUTOINCREMENT, id_cliente INTEGER NOT NULL, id_producto INTEGER NOT NULL, cantidad INTEGER NOT NULL, FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente), FOREIGN KEY (id_producto) REFERENCES Producto(id_producto))");
        db.execSQL("CREATE TABLE DetalleVenta (id_detalle INTEGER PRIMARY KEY AUTOINCREMENT, id_venta INTEGER NOT NULL, id_producto INTEGER NOT NULL, cantidad INTEGER NOT NULL, precio REAL NOT NULL, FOREIGN KEY (id_venta) REFERENCES Venta(id_venta), FOREIGN KEY (id_producto) REFERENCES Producto(id_producto))");
    }

    // Manejar actualizaciones de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuario");
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        db.execSQL("DROP TABLE IF EXISTS Producto");
        db.execSQL("DROP TABLE IF EXISTS Venta");
        db.execSQL("DROP TABLE IF EXISTS CarritoCompras");
        db.execSQL("DROP TABLE IF EXISTS DetalleVenta");
        onCreate(db);
    }

    // Método para insertar un usuario
    public long insertarUsuario(String nombre, String email, String contrasenia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("email", email);
        values.put("contrasenia", contrasenia);

        long resultado = db.insert("Usuario", null, values);
        db.close();
        return resultado;
    }

    // Método para validar el login
    public boolean validarLogin(String usuario, String contrasenia) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"email", "contrasenia"};
        String selection = "email = ? AND contrasenia = ?";
        String[] selectionArgs = {usuario, contrasenia};

        Cursor cursor = db.query("Usuario", columns, selection, selectionArgs, null, null, null);
        boolean isValid = (cursor != null && cursor.moveToFirst());

        if (cursor != null) cursor.close();
        db.close();
        return isValid;
    }

    // Método para insertar un producto
    public long insertarProducto(String nombre, String descripcion, double precio, int stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
        values.put("precio", precio);
        values.put("stock", stock);

        long resultado = db.insert("Producto", null, values);
        db.close();
        return resultado;
    }

    // Método para buscar un producto por nombre
    public Producto buscarProductoPorNombre(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Producto producto = null;
        Cursor cursor = db.rawQuery("SELECT * FROM Producto WHERE nombre = ?", new String[]{nombre});

        if (cursor != null && cursor.moveToFirst()) {
            producto = new Producto(
                    cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("precio")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("stock"))
            );
        }
        if (cursor != null) cursor.close();
        db.close();
        return producto;
    }

    // Método para insertar un cliente (sin usar ID)
    public long insertarCliente(String direccion, String tipoPago, String numeroPago) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("direccion", direccion);
        values.put("tipo_pago", tipoPago);
        values.put("numero_pago", numeroPago);

        long resultado = db.insert("Cliente", null, values);
        db.close();
        return resultado;
    }


    // Método para buscar un cliente por dirección
    // Método para insertar un cliente con id_usuario
    public long insertarCliente(String idUsuario, String direccion, String tipoPago, String numeroPago) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_usuario", idUsuario); // Asocia el id_usuario
        values.put("direccion", direccion);
        values.put("tipo_pago", tipoPago);
        values.put("numero_pago", numeroPago);

        long resultado = db.insert("Cliente", null, values);
        db.close();
        return resultado;
    }

    public Cliente buscarClientePorDireccion(String direccion) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cliente cliente = null;

        // Consultar la base de datos para buscar un cliente con la dirección proporcionada
        Cursor cursor = db.rawQuery("SELECT * FROM Cliente WHERE direccion = ?", new String[]{direccion});

        // Si hay resultados, mapeamos el cursor a un objeto Cliente
        if (cursor != null && cursor.moveToFirst()) {
            cliente = new Cliente(
                    cursor.getString(cursor.getColumnIndexOrThrow("direccion")),
                    cursor.getString(cursor.getColumnIndexOrThrow("tipo_pago")),
                    cursor.getString(cursor.getColumnIndexOrThrow("numero_pago"))
            );
        }

        // Cerramos el cursor y la base de datos
        if (cursor != null) cursor.close();
        db.close();

        return cliente; // Si no se encontró el cliente, devuelve null
    }

}
