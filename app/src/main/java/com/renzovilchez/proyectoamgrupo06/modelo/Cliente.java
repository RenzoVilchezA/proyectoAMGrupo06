package com.renzovilchez.proyectoamgrupo06.modelo;

public class Cliente {
    private String direccion;
    private String tipoPago;
    private String numeroPago;

    // Constructor
    public Cliente(String direccion, String tipoPago, String numeroPago) {
        this.direccion = direccion;
        this.tipoPago = tipoPago;
        this.numeroPago = numeroPago;
    }

    // Getters y Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(String numeroPago) {
        this.numeroPago = numeroPago;
    }
}
