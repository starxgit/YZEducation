package com.star.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件处理的工具类 Created By ShaoXin On 2018-10-31
 */
public class FileUtil {

    /**
     * 上传文件的方法
     * @param request Http请求内容
     * @param uploadDir 上传目录
     * @return fileUrl 文件地址
     */
    
    // 文件上传路径
    public static final String BASE_PATH = "/home/static/";
    // 静态资源请求地址
    public static final String CDN_PATH = "http://cdn.fstechnology.cn/";
    
    public static String upLoadFile(HttpServletRequest request,String uploadDir) {
        String fileUrl = "error";
        // 组合要上传文件的路径
        String savePath = BASE_PATH+uploadDir;
        // 新建这个文件
        File file = new File(savePath);
        // 判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            // 如果不存在，创建目录
            file.mkdir();
        }
        try {
            // 使用Apache文件上传组件处理文件上传步骤：
            // 1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            // 3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                // 按照传统方式获取数据
                fileUrl = "没有文件上传";
            }
            // 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            @SuppressWarnings("unchecked")
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                // 如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    @SuppressWarnings("unused")
                    String name = item.getFieldName();
                    // 解决普通输入项的数据的中文乱码问题
                    @SuppressWarnings("unused")
                    String value = item.getString("UTF-8");
                } else {// 如果fileitem中封装的是上传文件
                        // 得到上传的文件名称，
                    String filename = item.getName();
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    // 获取文件类型
                    String fileType = filename.substring(filename.lastIndexOf(".") + 1);
                    // 文件重命名，附加当前时间戳
                    filename = MD5Util.getMd5(filename+System.currentTimeMillis());
                    // 组合后新的文件名
                    filename = filename + "." + fileType;
                    // 获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    // 创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "/"+ filename);
                    // 创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    // 判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    // 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        // 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
                        // + filename)当中
                        out.write(buffer, 0, len);
                    }
                    // 关闭输入流
                    in.close();
                    // 关闭输出流
                    out.close();
                    // 删除处理文件上传时生成的临时文件
                    item.delete();
                    fileUrl = CDN_PATH + uploadDir + filename;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileUrl = "上传异常";
        }
        return fileUrl;
    }
    
    /**
     * 删除文件的方法 
     */
    public static void deleteFile(){
        
    }
    
    /**
     * 断点下载文件的方法 
     */
    public static void downLoadFile(){
        
    }
    
}
