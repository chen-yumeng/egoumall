package com.egoumall.fast;

import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * @program: egoumall->FastDFSTest
 * @description: FastDFS图片服务器测试
 * @author: cg
 * @create: 2020-01-26 18:18
 **/

public class FastDFSTest {

    @Test
    public void testUpload() throws Exception {
        //创建一个配置文件。文件名任意。内容就是tracker服务器的地址。
        //使用全局对象加载配置文件。
        ClientGlobal.init("E:\\workspace\\idea-workspace\\egoumall\\egoumall-manager-web\\src\\main\\resources\\conf\\fdfs_client.conf");
        //创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackClient获得一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //创建一个StrorageServer的引用，可以是null
        StorageServer storageServer = null;
        //创建一个StorageClient，参数需要TrackerServer和StrorageServer
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //使用StorageClient上传文件。
        String[] strings = storageClient.upload_file("C:\\Users\\chenyumeng\\Desktop\\易班\\yibanxiong.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }

}
