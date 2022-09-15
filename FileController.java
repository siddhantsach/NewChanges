package com.fileservice.ws;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("files")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	private Object chunk = new Object();

	@PostMapping
	public ResponseEntity uploadFile(@RequestParam("chunk") byte[] chunk, @RequestParam("id") String id,@RequestParam("fileName") String fileName) {
		System.out.println(id);

		try {
			return new ResponseEntity(new JSONObject().put("success", "true").toString(),HttpStatus.OK);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("uploadComplete")
	public ResponseEntity uploadComplete(@RequestParam("fileName") String fileName) {
		System.out.println(fileName);
		return new ResponseEntity(HttpStatus.OK);
	}
	
//	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") List<MultipartFile> file, String fileName){
//
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path("/files/")
//				.path(fileName)
//				.toUriString();
//
//		FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri, , file.getSize());
//			return new ResponseEntity(fileResponse,HttpStatus.OK);
//	}
	
//	@GetMapping("/{fileName:.+}")
//	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
//
//		Resource resource = fileStorageService.loadFileAsResource(fileName);
//		String contentType = null;
//
//		try {
//			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//		}catch(IOException ex) {
//			System.out.println("Could not determine fileType");
//		}
//
//		if(contentType==null) {
//			contentType = "application/octet-stream";
//		}
//
//		return ResponseEntity.ok()
//				.contentType(MediaType.parseMediaType(contentType))
//				.body(resource);
//	}
	
}
