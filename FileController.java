package com.fileservice.ws;

import ch.qos.logback.core.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("files")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


	@PostMapping
	public ResponseEntity uploadFile(@RequestParam("chunk") MultipartFile chunk, @RequestParam("id") String id, @RequestParam("fileName") String fileName) throws IOException {
		createDirIfNotExist();
		outputStream.write(chunk.getBytes());
		return new ResponseEntity(HttpStatus.OK);
	}

	private void createDirIfNotExist() {
		//create directory to save the files
		File directory = new File("./files");
		if (! directory.exists()){
			directory.mkdir();
		}
	}

	@PostMapping("uploadComplete")
	public ResponseEntity uploadComplete(@RequestParam("fileName") String fileName) {
		System.out.println(fileName);
		try(OutputStream out = new FileOutputStream(fileName)) {
			outputStream.writeTo(out);
			outputStream.reset();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	
}
