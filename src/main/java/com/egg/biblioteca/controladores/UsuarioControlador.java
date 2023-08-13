/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.controladores;

import com.egg.biblioteca.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio us;

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("usuario", us.getOne(id));

        return "usuario_modificar.html";
    }

}
