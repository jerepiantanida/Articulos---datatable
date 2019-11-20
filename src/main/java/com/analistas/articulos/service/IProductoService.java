/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.articulos.service;

import com.analistas.articulos.entities.Producto;
import java.util.List;

/**
 *
 * @author Jere P
 */
public interface IProductoService {
    
    //Métodos a implementar
    public List<Producto> findAll();
    
    public Producto findOne(int id);
    
    public void save(Producto producto);
    
    public void delete(int id);
    
    public List<Producto> buscar(String texto);
}