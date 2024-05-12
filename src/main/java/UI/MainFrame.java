package UI;

import base.GameResolver;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

public class MainFrame extends Application {
    
    @Getter private Group root;

    public static void initialize(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        root = new Group();
        var viewStrategy = GameResolver.getViewStrategy();
        var size = viewStrategy.getWindowSize();
        var scene = new Scene(root);
        var stage = new Stage();
        stage.setScene(scene);
        
        var width = size.getX();
        var height = size.getY();
        stage.setTitle("Ooga booga");
        stage.setWidth(width);
        stage.setHeight(height);
        
        stage.setResizable(false);
        stage.show();
        viewStrategy.setupViews(root);
    }
}
