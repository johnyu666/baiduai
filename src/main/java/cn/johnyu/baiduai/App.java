package cn.johnyu.baiduai;

import okhttp3.*;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class App {
    static String detectUrl="https://aip.baidubce.com/rest/2.0/face/v3/detect?access_token=";
    static String addUrl="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add?access_token=";
    static String searchUrl="https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=";

    static String access_token="";
    static {
        try {
            Properties properties=new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("access_token.properties"));
            access_token=properties.getProperty("access_token");
            detectUrl+=access_token;
            addUrl+=access_token;
            searchUrl+=access_token;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String toBase64(InputStream inputStream){
        String s= null;
        try {
            byte[] buf=new byte[inputStream.available()];
            inputStream.read(buf);
            s = new BASE64Encoder().encode(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  s;
    }


    public static String detectFaceWithUrl(String faceUrl){
        String rs="";
       OkHttpClient client = new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("image_type","URL")
                .add("image",faceUrl)
                .build();
        Request request=new Request.Builder()
                .url(detectUrl)
                .header("Content-Type","application/json")
                .post(body)
                .build();

        try {
            Response resp= client.newCall(request).execute();
            rs = resp.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static String detectFaceWithBase64(String Base64){
        String rs="";
        OkHttpClient client = new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("image_type","BASE64")
                .add("image",Base64)
                .build();
        Request request=new Request.Builder()
                .url(detectUrl)
                .header("Content-Type","application/json")
                .post(body)
                .build();

        try {
            Response resp= client.newCall(request).execute();
            rs = resp.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static String addFaceWithBase64(String Base64,String groupId,String userId,String userInfo){
        String rs="";
        OkHttpClient client = new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("image_type","BASE64")
                .add("image",Base64)
                .add("group_id",groupId)
                .add("user_id",userId)
                .add("user_info",userInfo)
                .build();
        Request request=new Request.Builder()
                .url(addUrl)
                .header("Content-Type","application/json")
                .post(body)
                .build();

        try {
            Response resp= client.newCall(request).execute();
            rs = resp.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static String searchFaceWithBase64(String Base64,String groupIdList){
        String rs="";
        OkHttpClient client = new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("image_type","BASE64")
                .add("image",Base64)
                .add("group_id_list", groupIdList)
                .build();
        Request request=new Request.Builder()
                .url(searchUrl)
                .header("Content-Type","application/json")
                .post(body)
                .build();

        try {
            Response resp= client.newCall(request).execute();
            rs = resp.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }




    public static void main(String[] args) throws Exception{
        String s=toBase64(Thread.currentThread().getContextClassLoader().getResourceAsStream("cage1.jpeg"));
        System.out.println(s);

    }
}
