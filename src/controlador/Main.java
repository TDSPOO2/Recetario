/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Receta;

/**
 *
 * @author cinthia
 */
public class Main extends Application {
    private Stage primaryStage;
    public RecetarioController rc;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Recetario();
    }
    
    public void Recetario() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/vista/Recetario.fxml"));
            Parent root = loader.load();
            rc = loader.getController();
            rc.setMain(this);
            Scene scene = new Scene(root); 
            primaryStage.setScene(scene);
            primaryStage.setTitle("Recetario");
            primaryStage.show();            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void AgregarEditarReceta(Receta editar) {
        try {
            FXMLLoader loader2 = new FXMLLoader(Main.class.getResource("/vista/AgregarEditarReceta.fxml"));
            Parent root = loader2.load();
            Scene scene = new Scene(root); 
            Stage secondaryStage = new Stage();
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.setScene(scene);
            AgregarEditarRecetaController aerc = loader2.getController();
            aerc.setMain(this,secondaryStage);
            if (editar != null) {
                aerc.setInstance(editar);
                secondaryStage.setTitle("Editar receta");            
            } else {
                secondaryStage.setTitle("Agregar receta");            
            }
            secondaryStage.show();       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
