package com;


import com.yr.net.connect.DeviceServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description 服务启动类
 *
 * @Author
 * @Date 下午12 :51 2018/12/14
 * @Param
 * @return
 */
@SpringBootApplication
public class BootApplication {
    /**
     * The Log.
     */
    static Logger log = LoggerFactory.getLogger(BootApplication.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootApplication.class, args);
        log.info("begin start server...");
        DeviceServer server = new DeviceServer(9001);
        server.run();
    }

}