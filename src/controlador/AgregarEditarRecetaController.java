/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Receta;

/**
 * FXML Controller class
 *
 * @author cinthia
 */
public class AgregarEditarRecetaController implements Initializable {
    private Receta instance;
    private Main main;
    private Stage stage;
    
    @FXML
    TextField nombre_receta;
    @FXML
    TextArea ingredientes;
    @FXML
    TextArea preparacion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void setMain(Main main, Stage secondaryStage) {
        this.main = main;
        this.stage = secondaryStage;
    }
    
    public void save() {
        if (instance == null) {
            Receta nueva = new Receta(nombre_receta.getText(),ingredientes.getText(),preparacion.getText());
            main.rc.agregarReceta(nueva);            
        } else {
            instance.actualizar(nombre_receta.getText(),ingredientes.getText(),preparacion.getText());
            main.rc.refrescarTabla();
            main.rc.mostrarDetalle(instance);
        }
        this.stage.close();
    }
    
    public void setInstance(Receta editar) {
        this.instance = editar;
        nombre_receta.setText(instance.get_nombre());
        ingredientes.setText(instance.get_ingredientes());
        preparacion.setText(instance.get_preparacion());
    }
}
