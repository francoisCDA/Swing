package org.example.util;

import java.io.*;

public class SavePhoto {

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

}
