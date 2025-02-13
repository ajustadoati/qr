package com.ajustadoati.qr.adapter.rest;


import com.ajustadoati.qr.adapter.rest.dto.request.CreateUserRequest;
import com.ajustadoati.qr.application.service.QRService;
import com.ajustadoati.qr.application.service.UserService;
import com.ajustadoati.qr.domain.Bank;
import com.ajustadoati.qr.domain.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/users")
@Slf4j
public class UserController {

  private final UserService userService;
  private final QRService qrService;

  public UserController(UserService userService, QRService qrService) {
    this.userService = userService;
    this.qrService = qrService;
  }

  @GetMapping("/{numberId}/qr2")
  public ResponseEntity<byte[]> getUserQRCode(@PathVariable String numberId) {
    var user = userService.getUserByNumberId(numberId);

    String datos =
      "ID: " + numberId + "\nNombre: Juan Pérez\nBanco: Banesco\nCuenta: 1234567890\nMonto: 50 USD";

    String data = "text: ID: 14447876 | Nombre: Juan Pérez | Banco: Banesco | Cuenta: 1234567890 | Monto: 50 USD";
    System.out.println("Generando QR con datos: " + data);

    byte[] qr = qrService.generateQRCode(data, 300, 300);
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "image/png");
    return ResponseEntity.ok().headers(headers).body(qr);

  }

  @GetMapping("/{numberId}")
  public String showPayment(@PathVariable String numberId, Model model) {
    var user = userService.getUserByNumberId(numberId);

    // Generar URL para obtener el QR
    String qrUrl = "/api/users/" + numberId + "/qr";

    model.addAttribute("pago", user);
    model.addAttribute("qrUrl", qrUrl); // Pasamos la URL del QR a la vista

    return "pago";
  }


  @GetMapping("/new")
  public String showUserForm(Model model) {
    User user = new User();
    List<Bank> banks = new ArrayList<>();
    user.setBanks(banks);
    user.getBanks().add(new Bank());
    model.addAttribute("user", user);
    return "user-form";
  }

  @PostMapping("/save")
  public String saveUser(@ModelAttribute("user") CreateUserRequest user, Model model) {
    System.out.println("Registering user: " + user);

    var userResponse = userService.createUser(user);

    model.addAttribute("pago", user);
    return "pago";
  }

  @GetMapping("/consulta")
  public String showConsultaForm() {
    return "consulta"; // Muestra la página de consulta
  }

  @PostMapping("/consulta")
  public String consultarUsuario(@ModelAttribute("numberId") String numberId, Model model) {
    var user = userService.getUserByNumberId(numberId);

    if (user == null) {
      model.addAttribute("error", "No se encontró un usuario con esa cédula.");
      return "consulta"; // Vuelve a la página de consulta con un mensaje de error
    }

    // Redirige a la página de detalles del pago
    return "redirect:/api/users/" + numberId;
  }
}
