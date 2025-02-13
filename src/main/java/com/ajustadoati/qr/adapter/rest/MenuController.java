package com.ajustadoati.qr.adapter.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

  @GetMapping("/")
  public String mostrarMenu() {
    return "menu"; // Muestra el menú principal
  }

  @GetMapping("/registro")
  public String mostrarRegistro() {
    return "registro"; // Carga la página de registro (debe existir registro.html)
  }

  @GetMapping("/consulta")
  public String mostrarConsulta() {
    return "consulta"; // Carga la página de consulta de pagos (consulta.html)
  }
}
