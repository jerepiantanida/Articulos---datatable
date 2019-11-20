/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.articulos.controller;

import com.analistas.articulos.entities.Producto;
import com.analistas.articulos.service.IProductoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Jere P
 */
@Controller
@RequestMapping("/productos")
@SessionAttributes({"producto"})
public class ProductoController {

    @Autowired
    IProductoService serv;

    @GetMapping("/list")
    public String home(Model m) {
        
     
        
        m.addAttribute("titulo", "Lista de Productos");
        m.addAttribute("productos", serv.findAll());

        return "list";
    }
    
    @GetMapping("/buscar")
    public String Buscar(@RequestParam String descripcion, Model m) {

        m.addAttribute("productos", serv.buscar(descripcion));

        return "list";
    }
    
    @GetMapping("/form")
    public String nuevo(Model model) {
    
        Producto producto = new Producto();

        model.addAttribute("producto", producto);
        
        return "form";
    }
    
    @PostMapping("/form")
    public String guardar(@Valid Producto producto, RedirectAttributes ra) {
        
        String msj = producto.getNumero() == 0 ? "Producto Creado Correctamente." : "Contacto Modificado Correctamente";
        
        serv.save(producto);
        ra.addFlashAttribute("success", msj);
        
        return "redirect:list";
    }
    
    @GetMapping("/form/{id}")
    public String editar(@PathVariable int id, Model m) {
        
        Producto producto = serv.findOne(id);
    
        m.addAttribute("producto", producto);
        
        return "form";
    }
    
    @GetMapping("/del/{id}")
    public String borrar(@PathVariable int id, RedirectAttributes ra) {
        
        serv.delete(id);
        ra.addFlashAttribute("delete", "Producto Borrado Correctamente.");
    
        return "redirect:/productos/list";
    }
    
    
}