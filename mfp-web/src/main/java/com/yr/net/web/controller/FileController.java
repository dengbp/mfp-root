package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.entity.Attachment;
import com.yr.net.entity.Customer;
import com.yr.net.service.UserService;
import com.yr.net.service.impl.AttachmentService;
import com.yr.net.util.RegexUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.imageio.ImageIO;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/28
 * </pre>
 * <p>
 *     文件上传控制器
 * </p>
 */
@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${user.pic.relative.path}")
    private String userPicPath;//   pic/user/
    @Value("${user.video.relative.path}")
    private String userVideoPath;//   video/user/
    @Value("${domain.name}")
    private String domainName;//http://www.goddesses.net.cn/
    @Value("${file.upload.absolute.path}")
    private String absolutePath;//  /usr/local/tools/apache-tomcat-8.5.30/webapps/
    @Resource
    UserService userService;
    @Resource
    AttachmentService attachmentService;

    /**
     * 单文件上传
     *
     * @param file file
     * @param userId 用户id
     * @param purpose 1：相册;2：视频;3:头像;4:背景图
     * @return 上传结果
     */
    @RequestMapping(value = "/file/single/upload")
    @ResponseBody
    public AjaxResponse upload(@RequestParam("file") MultipartFile file,String userId,String purpose) {
        if (file.isEmpty()) {
            return AjaxResponse.fail().setMsg("文件为空");
        }
        if(purpose == null){
            return AjaxResponse.fail().setMsg("purpose参数不能为空");
        }
        if(userId == null){
            return AjaxResponse.fail().setMsg("userId参数不能为空");
        }
        // 获取文件名
        String originName = file.getOriginalFilename();
        logger.info("上传的文件名为：{}",originName);
        // 获取文件的后缀名
        String suffixName = originName.substring(originName.lastIndexOf("."));
        logger.info("上传的后缀名为：{}" ,suffixName);
        boolean isImages = false;
        boolean isVideo = false;
        String imagesPath = null;
        String videoPath = null;
        String url = null;
        String fileName = System.nanoTime()+"";
        File dest = null;
        String filePath = null;
        Integer width = null;
        Integer height = null;
        Long size = file.getSize();
        Integer fileType = null;//文件类型1:图片；2:视频
        if (RegexUtils.checkImages(originName)){
            fileType = 1;
            if(attachmentService.isMaxLimit(new Long(userId),fileType)){
                return AjaxResponse.fail().setMsg("上传文件已达到上限");
            }
            BufferedImage sourceImg = null;
            try {
                sourceImg = ImageIO.read(file.getInputStream());
                width = sourceImg.getWidth();
                height  = sourceImg.getHeight();
                imagesPath = absolutePath.concat(userPicPath).concat(userId).concat("/");
                filePath = imagesPath.concat(fileName).concat(suffixName);
                url = domainName.concat(userPicPath).concat(userId).concat("/").concat(fileName).concat(suffixName);
                isImages = true;
            } catch (IOException e) {
                logger.error("上传文件失败",e);
            }
        }
        if (RegexUtils.checkVideo(originName)){
            fileType = 2;
            if(attachmentService.isMaxLimit(new Long(userId),fileType)){
                return AjaxResponse.fail().setMsg("上传文件已达到上限");
            }
            videoPath = absolutePath.concat(userVideoPath).concat(userId).concat("/");
            filePath = videoPath.concat(fileName).concat(suffixName);
            url = domainName.concat(userVideoPath).concat(userId).concat("/").concat(fileName).concat(suffixName);
            isVideo = true;
        }
        if (!isImages && !isVideo){
            logger.error("上传文件格式有误");
            return AjaxResponse.fail().setMsg("上传文件格式有误");
        }
        dest = new File(filePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            Attachment attachment = this.createAttachment(new Long(userId), width,height,size,fileType,suffixName,url);
            userService.setUserMultimedia(attachment,new Integer(purpose).intValue());
            return AjaxResponse.success().setMsg("上传成功").setResult(url);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResponse.fail().setMsg("上传失败");
    }


    /**
     * 附件对象赋值
     * @param userId 用户id
     * @param width 图片宽度
     * @param height 图片高度
     * @param size 文件大小
     * @param url 文件路径
     * @return 附件对象信息
     */
    private Attachment createAttachment(Long userId, Integer width,Integer height,Long size,Integer fileType,String suffix,String url){
        Attachment attachment = new Attachment();
        attachment.setBusinessId(userId);
        attachment.setUploadUserId(userId);
        attachment.setBusinessType(0);
        attachment.setCreateTime(new Date());
        attachment.setUploadTime(new Date());
        attachment.setUrl(url);
        attachment.setOrginSize(size==null?0L:size);
        attachment.setStoreSize(size==null?0L:size);
        attachment.setImageHeight(height==null?0:height);
        attachment.setImageWith(width==null?0:width);
        attachment.setFileType(fileType==null?0:fileType);
        attachment.setSuffix(suffix);
        return attachment;
    }

    /**
     * 多文件上传
     * @param request request
     * @return 上传结果
     */
    @RequestMapping(value = "/file/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    logger.error(e.getMessage());
                    stream = null;
                    return AjaxResponse.fail().setMsg("上传失败");
                }
            } else {
                return AjaxResponse.fail().setMsg("上传文件为空");
            }
        }
        return AjaxResponse.success().setMsg("上传成功");
    }
}
