/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Receta;
import modelo.Recetario;

/**
 * FXML Controller class
 *
 * @author cinthia
 */
public class RecetarioController implements Initializable {

    private Main main;
    
    @FXML
    Label nombre;
    @FXML
    TextArea ingredientes;
    @FXML
    TextArea preparacion;
    
    @FXML
    TableView <Receta> recetario;
    @FXML
    TableColumn <Receta,String> titulo;
    
    ObservableList<Receta> listaRecetas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        titulo.setCellValueFactory(new PropertyValueFactory<Receta,String>("titulo"));
        Recetario existentes = new Recetario();
        listaRecetas = FXCollections.observableArrayList(existentes.get_recetas());
        refrescarTabla();
        
        recetario.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                mostrarDetalle((Receta)newValue);
            }
        });
    }    

    public void setMain(Main main) {
        this.main = main;
    }
    
    public void refrescarTabla() {
        recetario.setItems(null);
        recetario.layout();
        recetario.setItems(listaRecetas);
    }
    
    public void abrirAgregarReceta() {
        this.main.AgregarEditarReceta(null);
    }
    
    public void abrirEditarReceta() {
        Receta seleccionada = getSeleccionada();
        if (seleccionada != null) {
            this.main.AgregarEditarReceta(seleccionada);
        }
    }
    
    public void agregarReceta(Receta nueva) {
        listaRecetas.add(nueva);
        this.refrescarTabla();
    }
    
    public void mostrarDetalle(Receta seleccionada) {
        if (seleccionada != null) {
            nombre.setText(seleccionada.get_nombre());
            ingredientes.setText(seleccionada.get_ingredientes());
            preparacion.setText(seleccionada.get_preparacion());
        } else {
            nombre.setText("");
            ingredientes.setText("");
            preparacion.setText("");
        }
    }
    
    public Receta getSeleccionada() {
        return recetario.getSelectionModel().getSelectedItem();
    }
     
    public void eliminarReceta() {
        Receta seleccionada = this.getSeleccionada();
        if (seleccionada != null) {
            seleccionada.eliminar();
            listaRecetas.remove(seleccionada);
        }
    }
}
