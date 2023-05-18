package com.proyectointermodular.popup;

import com.proyectointermodular.App;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PopUp {
    public static boolean resultado;
    public static boolean delete;

    /**
     * PopUp generico que muestra un mensaje definido.
     *
     * @param msg Mensaje que se mostrara en el PopUp
     */
    public static void display(String msg) {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Aviso!");

        Label label1 = new Label(msg);

        Button button1 = new Button("Cerrar mensaje");
        button1.setOnAction(e -> popupwindow.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 600, 250);
        popupwindow.setScene(scene1);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.showAndWait();

    }

    /**
     * PopUp para afirmacion o negacion ante la introduccion de una nueva Futbolista o un nuevo Club
     * sino, regresara a la lista global dependiendo del tipo
     *
     * @param tipo Texto que informa si proviene desde Futbolista o Club
     */
    public static void add(String tipo) {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Aviso!");

        String msg = "¿Quieres añadir otro club?";
        if (tipo.equals("futbolista")) {
            msg = "¿Quieres añadir otra futbolista?";
        }

        Label label1 = new Label(msg);

        Button button1 = new Button("Si");
        Button button2 = new Button("No");

        button1.setOnAction(e -> {
            resultado = true;
            popupwindow.close();
        });
        button2.setOnAction(e -> {
            resultado = false;
            popupwindow.close();
        });

        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);

        layout.getChildren().addAll(label1, layout2);
        layout2.getChildren().addAll(button1, button2);

        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 600, 250);
        popupwindow.setScene(scene1);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.showAndWait();
    }

    /**
     * PopUp de confirmación de eliminación.
     */
    public static void delete() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Aviso!");

        String msg = "¿Esta seguro de que quiere eliminar?";

        Label label1 = new Label(msg);

        Button button1 = new Button("Si");
        Button button2 = new Button("No");

        button1.setOnAction(e -> {
            delete = true;
            popupwindow.close();
        });
        button2.setOnAction(e -> {
            delete = false;
            popupwindow.close();
        });

        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);

        layout.getChildren().addAll(label1, layout2);
        layout2.getChildren().addAll(button1, button2);

        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 600, 250);
        popupwindow.setScene(scene1);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.showAndWait();
    }

    /**
     * Ventana About de informacion sobre el programa.
     */
    public static void about() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.initStyle(StageStyle.UNIFIED);
        popupwindow.initStyle(StageStyle.UTILITY);
        popupwindow.setTitle("About");

        HBox layout = new HBox(10);
        VBox pizq = new VBox(10);
        VBox pder = new VBox();
        Image img = new Image("com/proyectointermodular/app/about.png");
        ImageView imgview = new ImageView(img);
        imgview.setFitWidth(200);
        imgview.setFitHeight(200);
        Image img1 = new Image("com/proyectointermodular/app/about1.png");
        ImageView img1view = new ImageView(img1);
        img1view.setFitWidth(200);
        img1view.setFitHeight(59);

        Label title = new Label("About App!\n\n");
        title.setFont(new Font(24));
        Label app = new Label("Aplicación creada por:\nAntonio De Llamas Oliva\n\n");
        Label info = new Label("Proyecto Intermodular para gestión y control\nde equipos y futbolistas de la liga femenina.\n\n");
        Label insti = new Label("Salesianas Maria Auxiliadora Nervión - Sevilla\n");
        Label curso = new Label("Curso escolar 2022/2023 - 1º CFGS DAM\n\n");
        Label espacio = new Label("\n\n");

        HBox rrss = new HBox(1);
        rrss.setAlignment(Pos.CENTER);
        //GitHub
        Image git = new Image("com/proyectointermodular/app/rrss/git.png");
        ImageView gitView = new ImageView(git);
        gitView.setFitHeight(50);
        gitView.setFitWidth(50);
        Hyperlink gitHub = new Hyperlink();
        gitHub.getStyleClass().add("image-link");
        gitHub.setGraphic(gitView);
        gitHub.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/alnazo"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        //Twitter
        Image twi = new Image("com/proyectointermodular/app/rrss/twitter.png");
        ImageView twiView = new ImageView(twi);
        twiView.setFitHeight(50);
        twiView.setFitWidth(50);
        Hyperlink twitter = new Hyperlink();
        twitter.getStyleClass().add("image-link");
        twitter.setGraphic(twiView);
        twitter.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://twitter.com/@Antonio3_96"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        //YouTube
        Image yt = new Image("com/proyectointermodular/app/rrss/yt.png");
        ImageView ytView = new ImageView(yt);
        ytView.setFitHeight(35);
        ytView.setFitWidth(50);
        Hyperlink youtube = new Hyperlink();
        youtube.getStyleClass().add("image-link");
        youtube.setGraphic(ytView);
        youtube.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/@Alnazo"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        //Twitch
        Image tw = new Image("com/proyectointermodular/app/rrss/twitch.png");
        ImageView twView = new ImageView(tw);
        twView.setFitHeight(50);
        twView.setFitWidth(40);
        Hyperlink twitch = new Hyperlink();
        twitch.getStyleClass().add("image-link");
        twitch.setGraphic(twView);
        twitch.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://twitch.tv/alnazo"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        //TikTok
        Image tt = new Image("com/proyectointermodular/app/rrss/tt.png");
        ImageView ttView = new ImageView(tt);
        ttView.setFitHeight(50);
        ttView.setFitWidth(50);
        Hyperlink tiktok = new Hyperlink();
        tiktok.getStyleClass().add("image-link");
        tiktok.setGraphic(ttView);
        tiktok.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.tiktok.com/@antonio3_96"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        rrss.getChildren().addAll(gitHub, twitter, youtube, twitch, tiktok);
        pizq.getChildren().addAll(img1view, imgview);
        pder.getChildren().addAll(title, app, info, insti, curso, espacio, rrss);

        layout.getChildren().addAll(pizq, pder);

        Scene scene1 = new Scene(layout, 650, 400);
        scene1.getStylesheets().add("com/proyectointermodular/css/stiles.css");
        popupwindow.setScene(scene1);
        popupwindow.setMinHeight(400);
        popupwindow.setMinWidth(650);
        popupwindow.setMaxHeight(400);
        popupwindow.setMaxWidth(650);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.showAndWait();
    }


}
