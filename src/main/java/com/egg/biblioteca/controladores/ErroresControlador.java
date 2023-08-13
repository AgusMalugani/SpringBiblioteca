/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.biblioteca.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ErroresControlador implements ErrorController {
    
    @RequestMapping( value =  "/error", method={ RequestMethod.GET , RequestMethod.POST} )
    // ES lo mismo pero de otra forma, le damos la orden, que entre todo /error, sea metodo get o post, todo recurso /error
    // va a ingresar a este metodo.
    public ModelAndView RenderErrorPage(HttpServletRequest httpRequest){
        ModelAndView errorPage = new ModelAndView("error"); // le pasamos el html error por parametro, y trabaja parecido a model map, la diferencia es
        // q nos retorna directamente el modelo y la vista, 
        
        String errorMsg= ""; 
        int httpErrorCode = getErrorCode(httpRequest); // el httpRequest lo pasamos para que nos traiga el error
        // el codigo y con ese codigo vamos a poder hacer nuestro switch
        
        switch (httpErrorCode) {
		case 400: {
			errorMsg = "El recurso solicitado no existe.";
			break;
		}
		case 403: {
			errorMsg = "No tiene permisos para acceder al recurso.";
			break;
		}
		case 401: {
			errorMsg = "No se encuentra autorizado.";
			break;
		}
		case 404: {
			errorMsg = "El recurso solicitado no fue encontrado.";
			break;
		}
		case 500: {
			errorMsg = "Ocurrió un error interno.";
			break;
		}
		}

		errorPage.addObject("codigo", httpErrorCode); // le mandamos el numero
		errorPage.addObject("mensaje", errorMsg); // el string que queremos msotrar, y todo esto lo va renderizar
		return errorPage;
	}
        
    
    
    private int getErrorCode(HttpServletRequest httpRequest){
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
        
        // me tranforma el codigo del error en un numero entero
        
    }
    
    public String getErrorPath(){
        return "/error.html";
    }
    
}
