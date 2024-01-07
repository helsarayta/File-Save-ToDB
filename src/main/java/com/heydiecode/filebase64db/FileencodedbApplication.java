package com.heydiecode.filebase64db;

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
			byte[] encodeFile = Base64.getEncoder().encode(fileBytes);

			File saveFile  = new File();
			saveFile.setFileName("TEST 1");
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
