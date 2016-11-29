/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cinthia
 */
public class Receta {
    private int id;
    private String nombre;
    private String ingredientes;
    private String preparacion;
    
    public Receta(int id, String nombre, String ingredientes, String preparacion) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
    }
    
    public Receta(String nombre, String ingredientes, String preparacion) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        
        String sql = "INSERT INTO recetas(nombre,ingredientes,preparacion) value (?,?,?);";
        Conexion conector = new Conexion();
        try {
            PreparedStatement instruccion = conector.get_connection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            instruccion.setString(1, nombre);
            instruccion.setString(2, ingredientes);
            instruccion.setString(3, preparacion);
            instruccion.executeUpdate();
            
            ResultSet rs = instruccion.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                this.id = id;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        conector.close();
    }
    
    public void actualizar(String nuevo_nombre, String nuevos_ingredientes, String nueva_preparacion) {
        this.nombre = nuevo_nombre;
        this.ingredientes = nuevos_ingredientes;
        this.preparacion = nueva_preparacion;
        
        String sql = "UPDATE recetas set nombre = ?, ingredientes = ?, preparacion = ? WHERE id = ?;";
        
        Conexion conector = new Conexion();
        
        try {
            PreparedStatement instruccion = conector.get_connection().prepareStatement(sql);
            instruccion.setString(1, nuevo_nombre);
            instruccion.setString(2, nuevos_ingredientes);
            instruccion.setString(3,nueva_preparacion);
            instruccion.setInt(4,id);
            instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conector.close();
    }
    
    public void eliminar() {
        String sql = "DELETE FROM recetas WHERE id = ?";
        Conexion conector = new Conexion();
        try {
            PreparedStatement instruccion = conector.get_connection().prepareStatement(sql);
            instruccion.setInt(1,id);
            instruccion.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        conector.close();
    }
      
    public String get_nombre() {
        return this.nombre;
    }
    
    public String get_ingredientes() {
        return this.ingredientes;
    }
    
    public String get_preparacion() {
        return this.preparacion;
    }
    
    public StringProperty tituloProperty() {
        final StringProperty titulo = new SimpleStringProperty(nombre);
        return titulo;
    }
}
