import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static void resetSlider(Slider s1,Slider s2,Slider s3,Slider s4,ColorAdjust colorAdjust){
        s1.setValue(0);
        s2.setValue(0);
        s3.setValue(0);
        s4.setValue(0);
        colorAdjust.setSaturation(0);
        colorAdjust.setHue(0);
        colorAdjust.setBrightness(0);
        colorAdjust.setContrast(0);
    }
    public static void resetImage(ImageView iv){
        try{
            iv.setImage(new Image(new FileInputStream("image 1.jpg")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            //Images
            Image im1 = new Image(new FileInputStream("image 1.jpg"));
            Image im2 = new Image(new FileInputStream("image 2.jpg"));
            Image im3 = new Image(new FileInputStream("image 3.jpg"));
            ImageView iv = new ImageView(im1);
            iv.setFitWidth(500);
            iv.setPreserveRatio(true);

            //Sliders + Label Slider
            float min=-1;
            float max=1;
            float def=0;
            float inc = 0.05f;
            Slider lumi = new Slider(min,max,def);
            Slider cont = new Slider(min,max,def);
            Slider teinte = new Slider(min,max,def);
            Slider sat = new Slider(min,max,def);
            lumi.setBlockIncrement(inc);
            cont.setBlockIncrement(inc);
            teinte.setBlockIncrement(inc);
            sat.setBlockIncrement(inc);

            Label lumiLabel = new Label("Luminosité");
            Label contLabel = new Label("Contraste");
            Label teinteLabel = new Label("Teinte");
            Label satLabel = new Label("Saturation");

            VBox allSliders = new VBox(lumiLabel,lumi,contLabel,cont,teinteLabel,teinte,satLabel,sat);
            allSliders.setAlignment(Pos.CENTER);
            //Slider events
            ColorAdjust colorAdjust = new ColorAdjust();
            iv.setEffect(colorAdjust);
            lumi.setOnMouseReleased(event -> {
                colorAdjust.setBrightness(lumi.valueProperty().getValue());
            });
            cont.setOnMouseReleased(event -> {
                colorAdjust.setContrast(cont.valueProperty().getValue());
            });
            teinte.setOnMouseReleased(event -> {
                colorAdjust.setHue(teinte.valueProperty().getValue());
            });
            sat.setOnMouseReleased(event -> {
                colorAdjust.setSaturation(sat.valueProperty().getValue());
            });
            //HBOX CENTRE

            HBox center = new HBox(iv,allSliders);
            center.setAlignment(Pos.CENTER);

            //Menus
            Menu fichiers = new Menu("Fichiers");
            Menu chargerImage = new Menu("Charger une image");
            MenuItem i1 = new MenuItem("Image #1");
            MenuItem i2 = new MenuItem("Image #2");
            MenuItem i3 = new MenuItem("Image #3");

            chargerImage.getItems().addAll(i1, i2, i3);
            fichiers.getItems().add(chargerImage);
            Menu actions = new Menu("Actions");
            MenuItem reint = new MenuItem("Réintialiser");

            //LE BACKGROUD GRIS EST SPAGHETTI, JSP COMMENT METTRE SETBACKGROUND BORDERPLANE BOTTOM SEULEMENT.
            Label infoEnBas = new Label("Bienvenue sur le meilleur photoshop                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            infoEnBas.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            infoEnBas.setTextFill(Color.BLACK);

            actions.getItems().add(reint);
            MenuBar menu = new MenuBar(fichiers, actions);
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menu);
            borderPane.setCenter(center);
            borderPane.setBottom(infoEnBas);

            //Context Menu
            ContextMenu cm = new ContextMenu(fichiers, actions);

            Scene photoshop = new Scene(borderPane);
            photoshop.setOnContextMenuRequested(event -> cm.show(photoshop.getWindow(), event.getScreenX(), event.getScreenY()));

            //ACTIONS MENU
            i1.setOnAction(event -> {iv.setImage(im1);
            infoEnBas.setText("Image #1 Chargé                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            });
            i2.setOnAction(event -> {iv.setImage(im2);
                infoEnBas.setText("Image #2 Chargé                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            });
            i3.setOnAction(event -> {iv.setImage(im3);
                infoEnBas.setText("Image #3 Chargé                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            });
            reint.setOnAction(event -> {
                resetImage(iv);
                resetSlider(sat,teinte,lumi,cont,colorAdjust);
                infoEnBas.setText("Reinitialisé                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
            });

            //stage settings
            primaryStage.setMaxHeight(1400);
            primaryStage.setMaxWidth(2500);
            primaryStage.setMinHeight(600);
            primaryStage.setMinWidth(600);
            primaryStage.setMaximized(true);
            primaryStage.setScene(photoshop);//TODO <3
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
