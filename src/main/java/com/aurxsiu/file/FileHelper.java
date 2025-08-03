package com.aurxsiu.file;


import com.aurxsiu.file.exception.FileNotExistException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileHelper {
    public static String getRootPath(){
        return System.getProperty("user.dir");
    }

    /**
     * 要求sourceFolder是创建好了的,否则抛出aur.FileNotExistException
     * */
    public static void copyFolder(File sourceFolder, File targetFolder) throws IOException, FileNotExistException {
        if (sourceFolder.isDirectory()) {
            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }
            String[] files = sourceFolder.list();
            if (files != null) {
                for (String file : files) {
                    File srcFile = new File(sourceFolder, file);
                    File tgtFile = new File(targetFolder, file);
                    copyFolder(srcFile, tgtFile);
                }
            }else {
                throw new FileNotExistException();
            }
        } else {
            Files.copy(sourceFolder.toPath(), targetFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
