package org.example.util;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PhotoUtil {

    public static String path(String filePath, int employeeId) throws IOException {

        File selectedFile = new File(filePath);

        long fileSize = selectedFile.length();

        if (fileSize > 2097152L) {
            throw new RuntimeException("File is too large to save to " + filePath);
        }

        String currentDirectory = System.getProperty("user.dir");

        String destDirectory = currentDirectory + File.separator + "employeePhotos" ;

        File employeePhotoDirectory = new File(destDirectory);

        if (!employeePhotoDirectory.exists()) {
           employeePhotoDirectory.mkdir();
        }

        File newFile = new File(employeePhotoDirectory.getAbsolutePath() + File.separator + "id_" + employeeId + "_" + selectedFile.getName());
               try (
                   FileInputStream fis = new FileInputStream(selectedFile);
                   FileOutputStream fos = new FileOutputStream(newFile);
               ){
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1 ) {
                        fos.write(buffer,0,bytesRead);
                    }
                    return newFile.getAbsolutePath();
               }
                catch (IOException ex) {
                    throw new IOException(ex);
               }
    }

    public static ImageIcon getDefault() {

        File defaultImage = new File("src/main/resources/icone/portraitIco.png");

        if (defaultImage.exists()) {

            ImageIcon imagebase = new ImageIcon(defaultImage.getAbsolutePath());
            Image scaleImage = imagebase.getImage().getScaledInstance(160,-1, Image.SCALE_SMOOTH);

            return new ImageIcon(scaleImage);
        }
         return null;
    }

    public static ImageIcon getIcone(String path, int size) {

        File iconFile = new File(path);

        if (iconFile.exists()) {
            ImageIcon imagebase = new ImageIcon(iconFile.getAbsolutePath());
            Image image = imagebase.getImage().getScaledInstance(size, -1, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        }
        return null;
    }

    public static boolean rmEmployeePicture(String picturePath) {

        String currentDirectory = System.getProperty("user.dir");

        String destDirectory = currentDirectory + File.separator + "employeePhotos" ;

        if (!picturePath.startsWith(destDirectory)) {
            return false;
        }

        File file = new File(picturePath);

        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

}
