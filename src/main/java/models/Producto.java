package models;

import java.io.Serializable;

public class Producto implements Serializable {

    //Atributos
    private int id;
    private String marca;
    private String modelo;
    private String descripcion;
    private float precio;
    private int relevancia;

    //Constructor
    public Producto(int id, String marca, String modelo, String descripcion, float precio, int relevancia) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.relevancia = relevancia;
    }

    // Constructor copia
    public Producto(Producto otroProducto) {
        this.id = otroProducto.id;
        this.marca = otroProducto.marca;
        this.modelo = otroProducto.modelo;
        this.descripcion = otroProducto.descripcion;
        this.precio = otroProducto.precio;
        this.relevancia = otroProducto.relevancia;
    }

    //Contructor vacío
    public Producto() {
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(int relevancia) {
        this.relevancia = relevancia;
    }

    //Otros métodos
    @Override
    public String toString() {
        return "- " + marca + " " + modelo +
                " (" + descripcion + ") - Precio: " + precio + "\n";
    }

    //Metodo que calcula la relevancia
    public void calculaRelevancia() {

    }

    public String pintaProductoADetalle() {
        if (relevancia >= 9) {
            return "\n|---------------------------------------------------------------------|" + "\n" +
                    "                         **** Id: " + id + " ****\n" +
                    "|---------------------------------------------------------------------|" + "\n" +
                    " Marca:  " + marca + "\n" +
                    " Modelo: " + modelo + "\n" +
                    " Descripción: " + descripcion + "\n" + "\n" +
                    "                                               Precio: " + precio + "€" + "\n" +
                    "|---------------------------------------------------------------------|";
        }
        return
                "\n|---------------------------------------------------------------------|" + "\n" +
                        "                            Id: " + id + "\n" +
                        "|---------------------------------------------------------------------|" + "\n" +
                        " Marca:  " + marca + "\n" +
                        " Modelo: " + modelo + "\n" +
                        " Descripción: " + descripcion + "\n" + "\n" +
                        "                                               Precio: " + precio + "€" + "\n" +
                        "|---------------------------------------------------------------------|";
    }

}
