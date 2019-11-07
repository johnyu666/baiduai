package cn.johnyu.baiduai;

import org.junit.Test;

public class AppTest {
    @Test
    public void testDetectFaceWithUrl(){
        String rs=App.detectFaceWithUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572672288501&di=eb1e8e3f41aad3e19608642d4ed4cdd8&imgtype=0&src=http%3A%2F%2Fm.360buyimg.com%2Fpop%2Fjfs%2Ft24973%2F327%2F220378424%2F35193%2F3e050ea0%2F5b694f84N1160431a.jpg");
        System.out.println(rs);
    }

    @Test
    public void testDetectFaceWithBase64(){
        String base64=App.toBase64(Thread.currentThread().getContextClassLoader().getResourceAsStream("cage2.jpeg"));
        String rs=App.detectFaceWithBase64(base64);
        System.out.println(rs);
    }

    @Test
    public void testAddFaceWithBase64(){
        String base64=App.toBase64(Thread.currentThread().getContextClassLoader().getResourceAsStream("chen.jpeg"));
        String rs=App.addFaceWithBase64(base64,"star","chen","陈道明");
        System.out.println(rs);
    }

    @Test
    public void testSearchWithBase64(){
        String base64=App.toBase64(Thread.currentThread().getContextClassLoader().getResourceAsStream("chen2.jpeg"));
        String rs=App.searchFaceWithBase64(base64,"star");
        System.out.println(rs);
    }
}
