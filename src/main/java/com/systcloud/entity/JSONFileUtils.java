package com.systcloud.entity;


import org.apache.commons.io.IOUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class JSONFileUtils {
    public static String readFile(String filepath) throws IOException {
        FileInputStream fileIn= new FileInputStream(filepath);
        return IOUtils.toString(fileIn);

    }
    public static void writeFile(String data,String filepath) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filepath);
        IOUtils.write(data,fileOut);
    }
}
