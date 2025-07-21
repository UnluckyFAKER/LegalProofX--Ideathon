package com.moulik.demo.service;

import com.moulik.demo.model.certiRecord;
import com.moulik.demo.repo.certirepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasicService {
    @Autowired
    private certirepo CR;
    public void saveRecord(certiRecord records){
    CR.save(records);
}
    public  Optional<certiRecord> GetRecord(String id ){

         return CR.findByCertificateId(id.trim());
//        The call to id.trim() removes hidden spaces (like leading/trailing whitespace)
//        from the input string — without trimming, "CERT004 " (with a space) does not match
//    "CERT004" in the database, so the query fails.
    }
    public  Optional<certiRecord> GetRecordByName(String Name ) {

        return CR.findByCertificateName(Name.trim());
    }

    public List<certiRecord> GetAllRecords(){
    return CR.findAll();
    }



}
