package com.renzovilchez.proyectoamgrupo06.modelo;

public class Producto {
    public String nombre;
    public String descripcion;
    public double precio;
    public int stock;

    public Producto() {}

    public Producto(String nombre, String descripcion, double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
}
