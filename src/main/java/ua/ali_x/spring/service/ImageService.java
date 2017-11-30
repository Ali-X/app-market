package ua.ali_x.spring.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    byte[] getImage(String name);
    void saveImage(String filename, MultipartFile file);

}
