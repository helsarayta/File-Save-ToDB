package com.heydiecode.filebase64db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@SpringBootApplication
@RestController
@RequestMapping("upload/")
@Slf4j
public class FileencodedbApplication {

	@Autowired
	private FileRepositories fileRepositories;

	public static void main(String[] args) {
		SpringApplication.run(FileencodedbApplication.class, args);
	}

	@PostMapping("fileUpload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
			log.info("content => {}", file.getContentType());
			log.info("original => {}", file.getOriginalFilename());
			log.info("inputstream => {}", file.getInputStream());
			log.info("name => {}", file.getName());
			log.info("resource => {}", file.getResource());

			byte[] encodeFile = Base64.getEncoder().encode(fileBytes);

			File saveFile  = new File();
			saveFile.setFileName(file.getOriginalFilename());
			saveFile.setFileEncode(encodeFile);

			fileRepositories.save(saveFile);
		} catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "upload file success";
	}

	@GetMapping("view/{id}")
	public byte[] getFile(@PathVariable("id") Integer id) {
		File file = fileRepositories.findById(id).get();
		byte[] decode = Base64.getDecoder().decode(file.getFileEncode());

		return decode;
	}

}
