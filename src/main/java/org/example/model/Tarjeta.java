package org.example.model;

public class Tarjeta {
    private int idTarjeta;
    private String texto;
    private String cita;
    private String categoria;

    public Tarjeta(int idTarjeta, String texto, String cita, String categoria) {
        this.idTarjeta = idTarjeta;
        this.texto = texto;
        this.cita = cita;
        this.categoria = categoria;
    }

    public Tarjeta() {
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}