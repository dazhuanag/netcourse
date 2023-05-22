package com.company.project.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class UploadUtils {

    private static String realBasePath;

    @Value("${file.uploadFolder}")
    public void setRealBasePath(String realBasePath) {
        this.realBasePath = realBasePath;
    }

    private static String accessPath;

    @Value("${file.accessPath}")
    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public static String uploadFile(MultipartFile multipartFile){

        try {
            String realfilename = multipartFile.getOriginalFilename();

            String imgSuffix = realfilename.substring(realfilename.lastIndexOf("."));

            String newFilename = UUID.randomUUID() +imgSuffix;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String datePath = dateFormat.format(new Date());
            //上传到哪个路径下
            File targetPath = new File(realBasePath , datePath);
            if (!targetPath.exists()){
                targetPath.mkdirs();
            }
            File targetFilename = new File(targetPath, newFilename);
            multipartFile.transferTo(targetFilename);
//            return String.valueOf(targetFilename);//返回文件路径
            return accessPath + datePath + "/" + newFilename;//资源映射路径
        } catch (IOException e) {
            e.printStackTrace();
            return "失败！";
        }
    }

}
