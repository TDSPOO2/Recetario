/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cinthia
 */
public class Recetario {
    private ArrayList<Receta> recetas;
    
    public Recetario() {
        this.recetas = new ArrayList<Receta>();
        String sql = "SELECT id, nombre, ingredientes, preparacion FROM recetas";
        Conexion conector = new Conexion();
        
        try {
            PreparedStatement instruccion = conector.get_connection().prepareStatement(sql);
            ResultSet resultado = instruccion.executeQuery();
            while(resultado.next()) {
                Receta existente = new Receta(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("ingredientes"),
                        resultado.getString("preparacion")
                );
                recetas.add(existente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conector.close();
    }
    
    public ArrayList<Receta> get_recetas() {
        return this.recetas;
    }
}
