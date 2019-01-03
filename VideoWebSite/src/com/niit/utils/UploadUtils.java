/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niit.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 *
 * @author Vector
 */
public class UploadUtils {

    public static String uploadImage(HttpServletRequest request) throws IOException {
        String showPath = "";
        // 将当前上下文初始化给CommonsMultipartResolver 
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 检查form中是否有enctype＝"multipart／form－data" 
        if (resolver.isMultipart(request)) {
            // 强制转化request 
            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            // 从表单获取input名称 
            Iterator<String> iterable = req.getFileNames();
            // 存在文件
            if (iterable.hasNext()) {
                String inputName = iterable.next();
                // 获得文件 
                MultipartFile mf = req.getFile(inputName);
                byte[] mfs = mf.getBytes();
                // 定义文件名 
                String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                // 获得后缀名 
                String oriFileName = mf.getOriginalFilename();
                String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
                // 上传图片到本地 
                String realPath = req.getServletContext().getRealPath("") + "resources/image/" + fileName + suffix;
                mf.transferTo(new File(realPath));

                showPath = "http://"+req.getServerName()+":"+request.getServerPort() +req.getContextPath()+ "/resources/image/" + fileName + suffix;//回显路径
            }
        }
        return showPath;
    }
    public static String uploadVideo(HttpServletRequest request) throws IOException {
        String showPath = "";
        // 将当前上下文初始化给CommonsMultipartResolver 
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 检查form中是否有enctype＝"multipart／form－data" 
        if (resolver.isMultipart(request)) {
            // 强制转化request 
            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            // 从表单获取input名称 
            Iterator<String> iterable = req.getFileNames();
            // 存在文件
            if (iterable.hasNext()) {
                String inputName = iterable.next();
                // 获得文件 
                MultipartFile mf = req.getFile(inputName);
                byte[] mfs = mf.getBytes();
                // 定义文件名 
                String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                // 获得后缀名 
                String oriFileName = mf.getOriginalFilename();
                String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
                // 上传图片到本地 
                String realPath = req.getServletContext().getRealPath("") + "resources/video/" + fileName + suffix;
                mf.transferTo(new File(realPath));
                System.out.println(realPath);
                showPath ="http://"+req.getServerName()+":"+request.getServerPort() +req.getContextPath()+ "/resources/video/" + fileName + suffix;//回显路径
                System.out.println(showPath);
            }
        }
        return showPath;
    }
}
//request.getServletContext().getRealPath("/")  获取项目所在服务器的全路径，如：D:\Program Files\apache-tomcat-7.0.25\webapps\TestSytem\
//request.getServletPath()    获取客户端请求的路径名，如：/object/delObject
//request.getServerName()    获取服务器地址，如：localhost
//request.getServerPort()    获取服务器端口，如8080
//request.getContextPath()    获取项目名称，如：TestSytem
//request.getLocalAddr()    获取本地地址，如：127.0.0.1
//request.getLocalName()    获取本地IP映射名，如：localhost
//request.getLocalPort()    获取本地端口，如：8090
//request.getRealPath("/")    获取项目所在服务器的全路径，如：D:\Program Files\apache-tomcat-7.0.25\webapps\TestSytem\
//request.getRemoteAddr()    获取远程主机地址，如：127.0.0.1
//request.getRemoteHost()    获取远程主机，如：127.0.0.1
//request.getRemotePort()    获取远程客户端端口，如：3623
//request.getRequestedSessionId()    获取会话session的ID，如：823A6BACAC64FB114235CBFE85A46CAA
//request.getRequestURI()    获取包含项目名称的请求路径，如：/TestSytem/object/delObject
//request.getRequestURL().toString()    获取请求的全路径，如：http://localhost:8090/TestSytem/object/delObject

//				request.getHeader("User-Agent");    //就是取得客户端的系统版本     
//				request.getRemoteAddr();    //取得客户端的IP     
//				request.getRemoteHost()     //取得客户端的主机名     
//				request.getRemotePort();    //取得客户端的端口     
//				request.getRemoteUser();    //取得客户端的用户     
//				request.getLocalAddr();    //取得服务器IP     
//				request.getLocalPort();    //取得服务器端口
//                // 获取图片的宽高 
//                BufferedImage bufferedImage = ImageIO.read(new FileInputStream(new File(realPath)));
//                int width = bufferedImage.getWidth();
//                int height = bufferedImage.getHeight();
//                // 获取文件大小
//                long size = mf.getSize();
//                System.out.println("上传");
//                String path = req.getServletContext().getContextPath();
//                System.out.println(path);
//                //C:\Users\Vector\Documents\NetBeansProjects\SemesterProject\build\web\resources\image
//                String myPath = req.getServletContext().getRealPath("")+"resources\\image";
//                System.out.println(myPath);
