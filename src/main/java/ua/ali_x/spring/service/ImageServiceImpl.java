package ua.ali_x.spring.service;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.controller.admin.UserController;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] getImage(String name) {
        try {
            Path currentRelativePath = Paths.get("images");
            String uploadPath = currentRelativePath.toAbsolutePath().toString();
            String fileName = name + ".png";
            String filePath = uploadPath + File.separator + fileName;
            FileInputStream fin = null;
            fin = new FileInputStream(filePath);
            return IOUtils.toByteArray(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void saveImage(String filename, MultipartFile file) {
        final Logger logger = LoggerFactory
                .getLogger(UserController.class);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                Path currentRelativePath = Paths.get("images");
                String uploadPath = currentRelativePath.toAbsolutePath().toString();
                File dir = new File(uploadPath);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + filename + ".png");
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                System.out.println("You successfully uploaded file=" + serverFile.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("You failed to upload " + filename + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + filename
                    + " because the file was empty.");
        }
    }
}
