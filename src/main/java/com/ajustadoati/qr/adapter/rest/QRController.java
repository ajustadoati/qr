package com.ajustadoati.qr.adapter.rest;

import com.ajustadoati.qr.application.service.QRService;
import com.google.zxing.WriterException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class QRController {

  private final QRService qrService;

  public QRController(QRService qrService) {
    this.qrService = qrService;
  }

  @GetMapping("/{numberId}/qr")
  public ResponseEntity<ByteArrayResource> generateQR(@PathVariable String numberId)
    throws WriterException, IOException {
    String url = "https://qr.ajustadoati.com/api/users/" + numberId+"/qr";
    return qrService.generateQRCode(url);
  }
}