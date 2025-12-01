package com.aurxsiu.util.file;


import com.aurxsiu.util.file.exception.FileNotExistException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.rmi.RemoteException;


public class FileHelper {

    /**
     * 获取项目根目录
     * */
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

    public static String readFile(String path) throws Exception {
        File file = new File(path);

        StringBuilder stringBuffer = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String get;
            while((get = br.readLine())!=null){
                stringBuffer.append(get).append("\n");
            }
        }

       return stringBuffer.toString();
    }

    /**
     * 不做文件是否存在的检查
     * */
    public static void writeFile(String path,String str) throws IOException, InterruptedException {
        File file = new File(path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file,true)) {
            fileOutputStream.write(str.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread.sleep(10);
    }

    public static void createFile(String path) throws IOException {
        File file = new File(path);
        if(file.exists()){
            return;
        }
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                throw new RuntimeException("unexpected error");
            }
        }

        if (!file.createNewFile()) {
            throw new RemoteException("unexpected error");
        }
    }
}
