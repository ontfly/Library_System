package com.systcloud.controller;

import com.systcloud.entity.JSONFileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.systcloud.entity.Resource;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.net.URLEncoder;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
public class FileController {
    @RequestMapping("/booksUpload")
    public String booksUpLoad(MultipartFile[] files, HttpServletRequest request) throws Exception {
        //设置文件上传的路径
        String path = request.getServletContext().getRealPath("/")+"library/";
        ObjectMapper mapper = new ObjectMapper();
        if(files!=null && files.length>0){
            for(MultipartFile file:files){
                //获取上传文件的名称
                String filename=file.getOriginalFilename();
                ArrayList<Resource> list = new ArrayList<Resource>();
                //读取json文件中的文件名称
                String json = JSONFileUtils.readFile(path+"/booksname.json");
                if(json.length()!= 0){
                    list=mapper.readValue(json, new TypeReference<List<Resource>>() {
                    });
                    for(Resource resource:list){
                        if(filename.equals(resource.getName())){
                            String[] split = filename.split("\\.");
                            filename = split[0]+"(1)."+split[1];
                        }

                    }

                }
                String filePath = path+filename;
                file.transferTo(new File(filePath));
                list.add(new Resource(filename));
              //  String paths = request.getServletContext().getRealPath("/")+"library/booksname.json";

                json = mapper.writeValueAsString(list);
                JSONFileUtils.writeFile(json,path+"/booksname.json");
            }
            request.setAttribute("msg","上传成功");
            return "redirect:/web/booksload.jsp";

        }
        request.setAttribute("msg","上传失败");
        return "redirect:/web/booksload.jsp";

    }

    @ResponseBody
    @RequestMapping(value="/getbooksName", produces="text/html;charset=utf-8")
    public String getbooksName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletContext().getRealPath("/")+"library/booksname.json";
        return JSONFileUtils.readFile(path);
    }


    //将浏览器的编码后的文件名称转换为原本的文件名称
    public  String getFileName(HttpServletRequest request,String file) throws UnsupportedEncodingException {
        file = URLEncoder.encode(file,"UTF-8");
        return file;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request,String filename) throws IOException {
        //指定文件下载的路径
        String path = request.getServletContext().getRealPath("/library/");
        filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");
        //创建该文件的对象
        File file = new File(path+File.separator+filename);
        //设置响应头
        HttpHeaders headers = new HttpHeaders();
        filename=this.getFileName(request,filename);
        //通知浏览器以下载的方式打开浏览器
        headers.setContentDispositionFormData("attachment",filename);
        //定义以流的形式下载返回问你件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //使用Spring MVC框架的ResponseEntity对象封装返回下载的数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);

    }
}
