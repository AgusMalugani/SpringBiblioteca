/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicio.EditorialServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 @RequestMapping("/editorial") 
public class EditorialControlador {
     
     @Autowired
     private EditorialServicio es;
     
     @GetMapping("/registrar")
   public String registrar(){
       
       return "editorial_form.html";
       
   }
   
   @PostMapping("/registro")
   public String registro(String nombre, ModelMap modelo){
         try {
             es.crearEditorial(nombre);
               modelo.put("exito", "La editorial fue cargador con exito!");
         } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
             return "editorial_form.html";
         }
         return "editorial_form.html";
   }
   
   
     
    
}
