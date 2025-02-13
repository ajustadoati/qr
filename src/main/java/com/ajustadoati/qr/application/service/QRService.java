package com.ajustadoati.qr.application.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRService {

  public byte[] generateQRCode(String text, int width, int height) {
    try {
      if (text == null || text.isEmpty()) {
        throw new IllegalArgumentException("El texto del QR no puede estar vacío.");
      }

      QRCodeWriter writer = new QRCodeWriter();
      BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);
      return outputStream.toByteArray();
    } catch (WriterException | IOException e) {
      throw new RuntimeException("Error al generar QR", e);
    }
  }

  public ResponseEntity<ByteArrayResource> generateQRCode(String text)
    throws WriterException, IOException {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
    ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qrcode.png\"")
      .contentType(MediaType.IMAGE_PNG)
      .body(resource);
  }
}
