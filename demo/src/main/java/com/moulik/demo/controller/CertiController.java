package com.moulik.demo.controller;

import com.moulik.demo.model.certiRecord;
import com.moulik.demo.service.BasicService;
import com.moulik.demo.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ver")
public class CertiController {
    @Autowired
    BasicService basicService;
    @Autowired
    VerificationService vS;
    public CertiController(VerificationService vS) {
        this.vS = vS;
    }


    @PostMapping("/createrecord")
    public String  CreateRecord(@RequestBody certiRecord cr){
        basicService.saveRecord(cr);
        return "Done";

    }
    // only Create UI for that
    @GetMapping("/getrecord/id/{id}")
    public ResponseEntity<certiRecord> getRecord(@PathVariable("id") String id) {
        return basicService.GetRecord(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/getrecordbyName/id/{name}")
    public ResponseEntity<certiRecord> getRecordbyNAme(@PathVariable("name") String Name) {
        return basicService.GetRecordByName(Name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/getallrecord")
    public List<certiRecord> getAllRecord(){
        return basicService.GetAllRecords();

    }
    // only Create UI for that
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String verifyPDF(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty or not received.";
        }
        try {
            String result = vS.verifyDoc(file);
            return result;
        } catch (IOException e) {
            return "Failed to verify PDF: " + e.getMessage();
        }
    }



}
