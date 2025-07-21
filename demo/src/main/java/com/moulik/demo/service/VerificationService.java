package com.moulik.demo.service;

import com.moulik.demo.repo.certirepo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.moulik.demo.model.certiRecord;

import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.util.Optional;

@Service
public class VerificationService {
    @Autowired
    certirepo CR;

    private String extractFromPDF(MultipartFile file) throws IOException
    {
        PDDocument doc = PDDocument.load(file.getInputStream());
        PDFTextStripper ST = new PDFTextStripper();
        String text = ST.getText(doc);
        doc.close();
        return text;
    }
    private String findValueByKeyword(String text, String keyword) {
        for (String line : text.split("\n")) {
            if (line.toLowerCase().contains(keyword.toLowerCase())) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    return parts[1].trim();
                }
            }
        }
        return null;
    }
    public String verifyDoc(MultipartFile file) throws IOException {
        try {
            String text = extractFromPDF(file);

            String certificateId = findValueByKeyword(text, "Certificate ID");
            String name = findValueByKeyword(text, "Name");
            String incomeStr = findValueByKeyword(text, "Income");
            if (certificateId == null || name == null || incomeStr == null) {
                return " Required fields not Found in Pdf";
            }
            if (incomeStr == null) return "Income field missing";

//             Strip non-numeric characters
            Long income = Long.parseLong(incomeStr.replaceAll("[^0-9]", ""));



            Optional<certiRecord> opRecord = CR.findByCertificateId(certificateId.trim());
            if (opRecord.isEmpty()) {
                return "Wrong Record ";
            }
            certiRecord record = opRecord.get();
            boolean nameMatch = record.getCertificateName().equalsIgnoreCase(name);
            boolean incomeMatch = record.getIncome().equals(income);
            if (nameMatch && incomeMatch) {
                return " Document Valid ";
            } else {
                return "Document Invalid ";
            }


        }
        catch (Exception e) {
            throw new RuntimeException("Failed to extract PDF", e);
        }
    }



}
