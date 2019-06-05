package com.dyin.utils;


import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class UploadFile {

    /**
     * 保存文件 成功返回 文件名 反之 保存失败
     * @param file
     * @return
     */
    public static String store(MultipartFile file) {
       String path2 = null;
            //将文件转换为字节
        byte[] bytes = new byte[0];
        UUID uid = UUID.randomUUID();
        try {
            bytes = file.getBytes();

        //获取项目根目录
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            System.out.println(path);
            //根据时间给文件命名


            path2 = uid+".mp4";
            //创建文件
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File(path+"static/mp4/"+path2)));
            //将字节写进文件中
            stream.write(bytes);
            stream.close();

            getTempPath(path+"static/mp4s/"+uid+".jpg",path+"static/mp4/"+path2);

            System.out.println(path+"static/mp4/"+path2);

        } catch (Exception e) {
            return "保存失败";
        }
        System.out.println(path2);
        return uid.toString();
    }

    /**
     *
     * @param fileOut 保存路径
     * @param fileIn    MP4文件路径
     * @return
     */
    public static Boolean getTempPath(String fileOut,String fileIn) {
        Boolean flag1 = false;
        String tempPath = fileOut;//保存的目标路径
        File targetFile = new File(tempPath);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        try {
            File file2 = new File(fileIn);
            if (file2.exists()) {
                System.out.println("文件存在");
                FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file2);
                ff.start();
                int ftp = ff.getLengthInFrames();
                int flag = 0;
                Frame frame = null;
                while (flag <= ftp) {
                    //获取帧
                    frame = ff.grabImage();
                    //过滤前3帧，避免出现全黑图片
                    if ((flag > 5) && (frame != null)) {
                        break;
                    }
                    flag++;
                }
                ImageIO.write(FrameToBufferedImage(frame), "jpg", targetFile);
                ff.close();
                ff.stop();
            }else{
                flag1 = false;
            }
        } catch (Exception e) {
            System.out.println("获取预览图失败");

        }
        return flag1;
    }
    private static RenderedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }
}
