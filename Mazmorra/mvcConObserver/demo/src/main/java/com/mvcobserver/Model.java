package com.mvcobserver;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * La clase Model representa el modelo de datos en el patrón MVC (Modelo-Vista-Controlador).
 * Contiene los datos y lógica de la aplicación.
 */
public class Model {
    private ArrayList<Observer> observers;
    private String imagen;
    private String cadena;
    private int entero;
    private Color color;

    /**
     * Constructor de la clase Model. Inicializa los datos por defecto.
     */
    public Model() {
        this.observers = new ArrayList<>();
        this.imagen = "Portada";
        this.cadena = "Cadena de ejemplo";
        this.entero = 0;
        this.color = Color.WHITE;
    }

    /**
     * Suscribe un observador al modelo.
     * @param observer El observador a suscribir.
     */
    public void suscribe(Observer observer){
        observers.add(observer);
    }

    /**
     * Desuscribe un observador del modelo.
     * @param observer El observador a desuscribir.
     */
    public void unsuscribe(Observer observer){
        observers.remove(observer);
    }

    /**
     * Notifica a todos los observadores registrados que los datos del modelo han cambiado.
     */
    public void notifyObservers(){
        observers.forEach(x -> x.onChange());
    }

    /**
     * Obtiene la imagen actual del modelo.
     * @return El nombre de la imagen.
     */
    public String getImagen() {
        return this.imagen;
    }

    /**
     * Establece la imagen del modelo y notifica a los observadores.
     * @param imagen El nombre de la nueva imagen.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
        notifyObservers();
    }

    /**
     * Obtiene la cadena de texto actual del modelo.
     * @return La cadena de texto.
     */
    public String getCadena() {
        return this.cadena;
    }

    /**
     * Establece la cadena de texto del modelo y notifica a los observadores.
     * @param cadena La nueva cadena de texto.
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
        notifyObservers();
    }

    /**
     * Obtiene el entero actual del modelo.
     * @return El entero.
     */
    public int getEntero() {
        return this.entero;
    }

    /**
     * Incrementa el valor del entero en uno y notifica a los observadores.
     */
    public void sumarUno(){
        this.entero++;
        notifyObservers();
    }

    /**
     * Obtiene el color actual del modelo.
     * @return El color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Establece el color del modelo y notifica a los observadores.
     * @param color El nuevo color.
     */
    public void setColor(Color color) {
        this.color = color;
        notifyObservers();
    }
}
