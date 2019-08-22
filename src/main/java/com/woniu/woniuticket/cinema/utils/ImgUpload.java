package com.woniu.woniuticket.cinema.utils;

import com.woniu.woniuticket.cinema.execption.FileUploadException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImgUpload {

    //图片上传根目录
    private static final String UPLOAD_FOLDER="/static/img/";

    /**
     * 图片上传
     * @param multipartFile
     * @param path
     * @return 返回文件重命名后的名称
     */
    public static String singleFileUpload(MultipartFile multipartFile,String path){
        //获取文件名称
        String fileName=multipartFile.getOriginalFilename();
        //获取文件后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //文件重命名
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String newFileName = sdf.format(new Date())+suffix;
        try {
            //获取当前项目的类路径
            String classPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
            System.out.println("当前类路径："+classPath);
            File file=new File(classPath+UPLOAD_FOLDER+path);
            //判断文件夹是否存在，不存在则创建文件夹
            if(!file.exists()){
                file.mkdirs();
            }
            //文件归档
            multipartFile.transferTo(new File(file.getAbsolutePath()+ File.separator+newFileName));
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            //上传失败，返回到错误页面
            throw new FileUploadException("图片上传失败");
        }
    }
}
