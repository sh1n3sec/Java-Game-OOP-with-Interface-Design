package javafxapplication01;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.ImageCursor;
import javafx.scene.image.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXApplication01 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root, 1350, 810);

        Image image = new Image("/imagens/icon4.png");
        scene.setCursor(new ImageCursor(image));
        primaryStage.getIcons().add(new Image("/imagens/icon2.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
        primaryStage.setTitle("Champions League");

          
    }

    public static void main(String[] args) {
        launch(args);
        
    }

    private Object getFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
