package com.mvcobserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase principal que inicia la aplicación JavaFX.
 */
public class App extends Application {

    private static Scene scene;

    /**
     * El método start() inicia la aplicación JavaFX, cargando la vista principal desde el archivo FXML.
     * @param stage El objeto Stage principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Añadir un titulo en la parte superior de la aplicación
        stage.setTitle("Ejemplo sencillo de MVC con observadores");
        // Configurar la ventana para que no pueda cambiar de tamaño
        stage.setResizable(false);
        // Cargar la vista principal, el controlador se inicializa automáticamente
        scene = new Scene(loadFXML("mainView"));
        // Agregar estilos desde un archivo CSS externo
        scene.getStylesheets().add(App.class.getResource("estilos/estilos.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia la vista actual de la aplicación.
     * @param fxml El nombre del archivo FXML de la nueva vista.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga el archivo FXML y devuelve el nodo raíz.
     * @param fxml El nombre del archivo FXML a cargar.
     * @return El nodo raíz cargado desde el archivo FXML.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * El método main() es el punto de entrada de la aplicación.
     * @param args Los argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        launch();
    }

}
