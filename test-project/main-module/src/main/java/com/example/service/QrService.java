package com.example.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class QrService {
	public ResponseEntity<byte[]> generate(String url){
		// QR 정보
		int width = 200;
		int height = 200;

		try {
			// QR Code - BitMatrix: qr code 정보 생성
			BitMatrix encode = new MultiFormatWriter()
					.encode(url, BarcodeFormat.QR_CODE, width, height);

			// QR Code - Image 생성. : 1회성으로 생성해야 하기 때문에
			// stream으로 Generate(1회성이 아니면 File로 작성 가능.)

			//output Stream
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			//Bitmatrix, file.format, outputStream
			MatrixToImageWriter.writeToStream(encode, "PNG", out);

			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_PNG)
					.body(out.toByteArray());

		}catch (WriterException | IOException e){
			log.warn("QR Code OutputStream 도중 Excpetion 발생, {}", e.getMessage());
		}
		return null;
	}
}
