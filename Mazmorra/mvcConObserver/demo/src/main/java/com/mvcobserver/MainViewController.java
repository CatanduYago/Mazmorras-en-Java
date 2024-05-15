package com.mvcobserver;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controlador de la vista principal.
 * Implementa la interfaz Observer para recibir actualizaciones del modelo.
 */
public class MainViewController implements Observer {
    
    // Componentes de la vista
    @FXML
    private Label intLabel; // Etiqueta para mostrar el entero

    @FXML
    private Label stringLabel; // Etiqueta para mostrar la cadena

    @FXML
    private ImageView imageView; // Vista de imagen para mostrar la imagen seleccionada

    @FXML
    private Label colorLabel; // Etiqueta para mostrar el color seleccionado

    @FXML
    private ColorPicker colorPicker; // Selector de color para elegir un color

    @FXML
    private TextField stringField; // Campo de texto para ingresar la cadena

    @FXML
    private Button intButton; // Botón para incrementar el entero

    @FXML
    private ChoiceBox<String> choiceBox; // Caja de selección para elegir una imagen

    // Atributo del modelo
    private Model model;

    /**
     * Método invocado cuando el modelo cambia. Actualiza la vista con los nuevos datos del modelo.
     */
    @Override
    public void onChange() {
        imageView.setImage(new Image(App.class.getResource("imagenes/" + model.getImagen() + ".png").toExternalForm()));
        intLabel.setText("" + model.getEntero());
        stringLabel.setText(model.getCadena());
        colorLabel.setText(model.getColor().toString());
    }

    /**
     * Método inicializador del controlador.
     * Se llama automáticamente después de cargar el archivo FXML asociado.
     */
    @FXML
    public void initialize() {
        model = new Model();
        // Se suscribe a las actualizaciones del modelo
        model.suscribe(this);
        onChange();

        // Manejadores de eventos para los componentes de la vista

        // Actualiza el modelo con la cadena ingresada en el campo de texto
        stringField.setOnAction(event -> {
            model.setCadena(stringField.getText());
            System.out.println("Cadena actualizada a: " + stringField.getText());
        });

        // Incrementa el valor del entero en el modelo cuando se presiona el botón
        intButton.setOnAction(event -> {
            model.sumarUno();
            System.out.println("Valor actual: " + model.getEntero());
        });

        // Actualiza el modelo con el color seleccionado en el ColorPicker
        colorPicker.setOnAction(event -> {
            model.setColor(colorPicker.getValue());
            System.out.println("Color cambiado a: " + colorPicker.getValue().toString());
        });

        // Configura la ChoiceBox con opciones y maneja el cambio de selección
        choiceBox.setItems(FXCollections.observableArrayList("Portada", "Componentes", "Suscripción", "Notificación"));
        choiceBox.setValue("Portada");

        // Cambia la imagen en el modelo según la selección en el ChoiceBox
        choiceBox.setOnAction(event -> {
            model.setImagen(choiceBox.getValue());
            System.out.println("Imagen cambiada a: " + choiceBox.getValue());
        });
    }
}