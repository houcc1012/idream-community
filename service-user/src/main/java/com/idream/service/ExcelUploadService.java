package com.idream.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelUploadService {
    void uploadLifeExcel(MultipartFile file, String fileLocation, Integer startId, Integer endId);

    void uploadActivityLifeExcel(MultipartFile file, String fileLocation, Integer startId, Integer endId);

    void uploadUser(MultipartFile file, String fileLocation, Integer startId, Integer endId);

    void uploadUser(MultipartFile file, String fileLocation);
}
