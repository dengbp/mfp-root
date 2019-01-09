package com.yr.net.connect;

import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type App connector.
 *
 * @author dengbp
 * @ClassName AppConnector
 * @Description 小程序连接器A
 * @date 2018 /12/14 上午10:37
 */
public class AppConnector extends Connector{
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(AppConnector.class);

    /**
     * Description 连接器
     * @Author 
     * @Date 上午11:00 2018/12/14
     * @Param []
     * @return void
     **/
    @Override
    public void connect() {
        logger.info("begin connect from client...");
    }

    /**
     * Description doto
     * @Author 
     * @Date 上午11:00 2018/12/14
     * @Param []
     * @return void
     **/
    @Override
    public void destroy() {

    }
}
