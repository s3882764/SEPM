package com.spem.application.controller;

import java.io.IOException;
import java.util.List;

import com.spem.application.pojo.UploadResponse;
import com.spem.application.service.TeamCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spem.application.pojo.Reader;
import com.spem.application.service.ReaderService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/reader")
public class ReaderController {


    @Autowired
    private ReaderService lReaderService;

    @Autowired
    private TeamCreationService teamCreationService;


    @PostMapping("/upload")
    public UploadResponse readerFile(@RequestParam("file") MultipartFile file) throws IOException {
        UploadResponse response = new UploadResponse();
		response.setStatus(400);
        if (lReaderService.isCSV(file)) {
            System.out.println("file   " + file.getContentType());
            List<Reader> red = lReaderService.readCSV(file.getInputStream());
            if (red.isEmpty()) {
                response.setMessage("Empty data");
                return response;
            }
            for (Reader r : red) {
                System.out.println("r----" + r);
            }
            response.setMessage("Upload Successfully");
            response.setStatus(200);
            return response;
        } else if (lReaderService.isExcel(file)) {
            System.out.println("file   " + file.getContentType());
            List<Reader> red = lReaderService.readExcel(file.getInputStream());
            if (red.isEmpty()) {
                response.setMessage("No data found");
                return response;
            }
            for (Reader r : red) {
                System.out.println("r----" + r);
            }
            response.setMessage("Uploaded successfully");
            response.setStatus(200);
            return response;
        }
        response.setMessage("Upload the correct format, please upload the correct file format");
        return response;
    }

    @GetMapping("/teams")
    public List<Reader> formTeam(){
        return teamCreationService.createTeam();
    }
}
