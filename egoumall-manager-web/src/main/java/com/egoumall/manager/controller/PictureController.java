package com.egoumall.manager.controller;

import com.egoumall.common.utils.FastDFSClient;
import com.egoumall.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: egoumall->PictureController
 * @description: 图片上传控制器
 * @author: cg
 * @create: 2020-01-26 19:33
 **/

@Controller
public class PictureController {

    /**
     * 获取spring-mvc配置文件中加载的resources.properties中的图片服务器的地址
     */
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    /**
     * 图片上传 返回string类型是为了解决上传图片时浏览器兼容问题
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "pic/upload", produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String uploadPicture(MultipartFile uploadFile) {
        try {
            //把图片上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/fdfs_client.conf");
            //获得扩展名
            String filename = uploadFile.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf('.') + 1);
            //得到图片的地址和文件名
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), suffix);
            //补充成完整的url
            url = IMAGE_SERVER_URL + url;
            //封装到map中
            Map result = new HashMap();
            result.put("error", 0);
            result.put("url", url);
            return JsonUtils.objectToJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map result = new HashMap();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return JsonUtils.objectToJson(result);
        }
    }

}
