import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/6/14
 * </pre>
 * <p>
 *     fastdfs测试
 * </p>
 */
public class FastDFSTest {
    static Logger logger = LoggerFactory.getLogger(FastDFSTest.class);

    public static void main(String[] args) throws Exception {
       /* String local_filename = "D://downloads/d8d91ecf7adecb6beadff3d4d7d63ac8.png";
        //上传
        String fileId = FastDFSUtils.uploadFile(local_filename);
        //下载
        byte[] bytes = FastDFSUtils.downloadFile(fileId);
        String fileName = "D://downloads/test1.jpg";
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(bytes);
        out.flush();
        out.close();
        //删除
        FastDFSUtils.deleteFile(fileId);
        logger.info("end");*/
    }
}
