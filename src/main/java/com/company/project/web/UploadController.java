package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.utils.UploadUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件服务对外接口
 */
@RestController
public class UploadController {

    /**
     * 上传文件接口
     * @param file 二进制文件
     * @return url
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file){
        String path = UploadUtils.uploadFile(file);
        return ResultGenerator.genSuccessResult(path);
    }


}
