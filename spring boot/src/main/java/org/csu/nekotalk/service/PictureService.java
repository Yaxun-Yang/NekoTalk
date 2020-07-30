package org.csu.nekotalk.service;

import com.qiniu.cdn.CdnManager;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Base64;

public class PictureService {

    //密钥
    static String accessKey = "";
    static String secretKey = "";
    static Auth auth = Auth.create(accessKey, secretKey);
    //存储空间名
    static String bucketName = "nekotalk";
    final static public String domain="http://qdeujgrtl.bkt.clouddn.com/";

    public static void refresh(String url) throws QiniuException {
        String[] urls = {url};
        CdnManager c = new CdnManager(auth);
        c.refreshUrls(urls);
    }

    //上传
    public static void uploadImage(File file, String key)  {

        try {

            String base64File = null;
            InputStream in = null;
            try {
                in = new FileInputStream(file);
                byte[] bytes = new byte[in.available()];
                base64File = Base64.getEncoder().encodeToString(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            BASE64Decoder decoder = new BASE64Decoder();
            // Base64解码,对字节数组字符串进行Base64解码并生成文件
            byte[] byt = decoder.decodeBuffer(base64File);
            for (int i = 0, len = byt.length; i < len; ++i)
            {
                // 调整异常数据
                if (byt[i] < 0) {
                    byt[i] += 256;
                }
            }
            OutputStream out = null;
            InputStream input = new ByteArrayInputStream(byt);

            // 生成指定格式的文件
            out = new FileOutputStream("tempFile");
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = input.read(buff)) != -1) {
                out.write(buff, 0, len);

                // 创建上传对象，Zone*代表地区
                Configuration configuration = new Configuration(Region.autoRegion());
                UploadManager uploadManager = new UploadManager(configuration);

                File afile= new File("tempFile");
                //允许上传覆盖
                uploadManager.put(file, key, auth.uploadToken(bucketName, key, 3600, new StringMap().put("insertOnly", 0)));
            }
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            e.printStackTrace();
            System.out.println("error " + r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                System.out.println("error " + e1.error());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
