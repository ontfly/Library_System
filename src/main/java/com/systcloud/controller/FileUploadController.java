package com.systcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


public class FileUploadController {
    @RequestMapping("/fileupload")
    //MultipartFile的形参名称必须与上传表单的名称一致
    public String fileupload(MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            file.transferTo(new File("c://"));
            return "uploadSSucess";
        }

        return "uploadFailure";
    }
}
