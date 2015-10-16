/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Folder.Loader;
import java.io.File;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import static util.Util.supportedSuffix;

/**
 *
 * @author Yoss
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        Loader lo = new Loader();    
        
        
        ObservableList<String> ol = FXCollections.observableArrayList();
        ListView<String> lw = new ListView<>(ol);
        
        TextArea tArea = new TextArea(
                lo.root.stream().map(File::getName).collect(joining("\n")));
                        //.reduce("", String::concat  ) );
        btn.setText("Go to hell");
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DirectoryChooser dc = new DirectoryChooser();
                dc.setTitle("Choose some bloody file");
                
                File f = dc.showDialog(primaryStage);
                if(f!=null) lo.addRoot(f);
                ol.setAll( 
                        lo.getContent()
                                .map(File::getName)
                                .sorted()
                                .filter(main::filterFiles)
                                .map((String s) -> s.substring(0,s.length()-4))
                                .collect(Collectors.toList()) 
                );
                
//                tArea.setText(
//                        lo.getContent()
//                        .map(File::getName)
//                        .sorted()
//                        .filter(main::filterFiles)
//                        .collect(joining("\n"))
//                );                
            }
        });
        
        VBox root = new VBox(btn,lw);
        VBox.setVgrow(lw, Priority.ALWAYS);
        
        Scene scene = new Scene(root, 600, 550);
          
        
        primaryStage.setTitle("They will run in fear");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public static boolean filterFiles(String s){
        return supportedSuffix.stream().anyMatch(s.toLowerCase()::endsWith);        
    }    
}
