package com.Spring.SpringBoot.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class S3Fileupload {

    @PostMapping("/supload")
    public ResponseEntity UploadFile(@RequestParam("image") MultipartFile file) {
        String fileName=file.getOriginalFilename();

        System.out.println("filename:" + fileName);


       /* try {
           // S3Utils.uploadFile(fileName,file.getInputStream());
            ResponseEntity.status(HttpStatus.OK);
        } catch (IOException e) {
           e.printStackTrace();
        }
        return new ResponseEntity("Can't upload file" ,HttpStatus.BAD_REQUEST);*/
        return null;
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
        return file;
    }
}
