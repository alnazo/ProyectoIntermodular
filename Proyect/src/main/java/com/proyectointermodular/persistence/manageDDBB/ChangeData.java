package com.proyectointermodular.persistence.manageDDBB;

import com.proyectointermodular.App;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.popup.PopUp;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ChangeData {

    private String usuario = "";
    private String password = "";
    private String host = "";
    private String puerto = ""; //1-65535
    private String bd = "";

    /**
     * Inicio de los ajustes para el cambio de Bases de Datos.
     */
    public void inicio() {
        try {
            Properties prop = new Properties();
            prop.load(App.class.getResource("ddbb/config.properties").openStream());

            usuario = prop.getProperty("jdbc.mysql.user");
            password = prop.getProperty("jdbc.mysql.passwd");
            host = prop.getProperty("jdbc.mysql.url.host");
            puerto = prop.getProperty("jdbc.mysql.url.port");
            bd = prop.getProperty("jdbc.mysql.url.schema");

            popup();

            prop.setProperty("jdbc.mysql.user", usuario);
            prop.setProperty("jdbc.mysql.passwd", password);
            prop.setProperty("jdbc.mysql.url.host", host);
            prop.setProperty("jdbc.mysql.url.port", puerto);
            prop.setProperty("jdbc.mysql.url.schema", bd);

            OutputStream output = new FileOutputStream(App.class.getResource("ddbb/config.properties").getFile());
            almacenarProperties(prop, output);

            if (MySQLConnector.testConnect()) {
                PopUp.display("La conexión a la Base de Datos es correcta.\nSi no ha importado las tablas acceda a 'Help > Cargar Esquema BD'");
            } else {
                PopUp.display("La conexión a la Base de Datos no se ha podido realizar correctamente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            PopUp.display("[WARNING] Existió un error a la hora de acceder al fichero de configuración.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
        } catch (SQLException e){
            e.printStackTrace();
            PopUp.display("[WARNING] Existió un error a la hora de conectar a la base de datos.");
        }
    }

    /**
     * Metodo de almacenar las {@link Properties} al formato de .properties
     *
     * @param prop   Parametros de {@link Properties} cargadas en memoria.
     * @param output Archivo donde se almacenara la información.
     * @throws IOException Excepcion de I/O del fichero.
     */
    private static void almacenarProperties(Properties prop, OutputStream output) throws IOException {
        for (String key : prop.stringPropertyNames()) {
            String value = prop.getProperty(key);
            output.write((key + "=" + value + System.lineSeparator()).getBytes());
        }
        output.flush();
    }

    /**
     * PopUp para la peticion de datos para la conectividad a la Base de Datos
     */
    private void popup() {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Editar conexión a BBDD");

        Label Luser = new Label("Usuario");
        Label Lpass = new Label("Contraseña");
        Label Lhost = new Label("Host");
        Label Lport = new Label("Puerto");
        Label Lbd = new Label("Base de Dato");
        TextField Tusuario = new TextField();
        TextField Tpassword = new TextField();
        TextField Thost = new TextField();
        TextField Tpuerto = new TextField();
        TextField Tbd = new TextField();

        Tusuario.setText(usuario);
        Tpassword.setText(password);
        Thost.setText(host);
        Tpuerto.setText(puerto);
        Tbd.setText(bd);

        Button send = new Button("Guardar");

        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);
        VBox v1 = new VBox(10);
        VBox v2 = new VBox(10);
        VBox v3 = new VBox(10);
        VBox v4 = new VBox(10);
        VBox v5 = new VBox(10);

        layout.getChildren().addAll(layout2, send);
        layout2.getChildren().addAll(v1, v2, v3, v4, v5);
        v1.getChildren().addAll(Luser, Tusuario);
        v2.getChildren().addAll(Lpass, Tpassword);
        v3.getChildren().addAll(Lhost, Thost);
        v4.getChildren().addAll(Lport, Tpuerto);
        v5.getChildren().addAll(Lbd, Tbd);

        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);
        v1.setAlignment(Pos.CENTER);
        v2.setAlignment(Pos.CENTER);
        v3.setAlignment(Pos.CENTER);
        v4.setAlignment(Pos.CENTER);

        send.setOnAction(event -> {
            String s_user = Tusuario.getText();
            String s_pass = Tpassword.getText();
            String s_host = Thost.getText();
            String s_port = Tpuerto.getText();
            String s_bd = Tbd.getText();
            this.usuario = s_user;
            this.password = s_pass;
            this.host = s_host;
            this.puerto = s_port;
            this.bd = s_bd;
            popupwindow.close();
        });

        Scene scene1 = new Scene(layout, 800, 250);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

}
