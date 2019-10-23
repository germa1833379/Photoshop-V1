import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Menu fichiers = new Menu("Fichiers");
        Menu chargerImage = new Menu("Charger une image");
        MenuItem i1 = new MenuItem("Image #1");
        MenuItem i2 = new MenuItem("Image #2");
        MenuItem i3 = new MenuItem("Image #3");

        i1.setOnAction(event -> {});
        i2.setOnAction(event -> {});
        i3.setOnAction(event -> {});

        chargerImage.getItems().addAll(i1,i2,i3);
        fichiers.getItems().add(chargerImage);
        Menu actions = new Menu("Actions");
        MenuItem reint = new MenuItem("Réintialiser");

        actions.getItems().add(reint);
        MenuBar menu = new MenuBar(fichiers,actions);
        BorderPane menuHaut = new BorderPane();
        menuHaut.setTop(menu);

        ContextMenu cm = new ContextMenu(fichiers,actions);

        Group root = new Group(menuHaut);
        Scene photoshop = new Scene(root);
        photoshop.setOnContextMenuRequested(event -> cm.show(root,event.getScreenX(),event.getScreenY()));
        reint.setOnAction(event->{primaryStage.setScene(photoshop);});


        primaryStage.setMaxHeight(1080);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(photoshop);//TODO <3
        primaryStage.show();
    }
}
