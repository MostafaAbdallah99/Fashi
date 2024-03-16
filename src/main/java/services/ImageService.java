package services;

import jakarta.servlet.ServletContext;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImageService {

    private final ServletContext servletContext;

    public ImageService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String saveImage(InputStream inputStream, String fileName) throws IOException {
        String newFileName = generateUniqueFileName(fileName.replace(" ", "_"));
        String realPath = servletContext.getRealPath("/img/");
        String fullPath = realPath + newFileName;
        Path filePath = Paths.get(fullPath);
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        try {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return "img/" + newFileName;
        } catch (IOException e) {
            Files.deleteIfExists(filePath);
            throw e;
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        return baseName + "_" + timestamp + fileExtension;
    }
}