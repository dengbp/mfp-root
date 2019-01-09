package com.yr.net.connect;


import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Device connector.
 *
 * @author dengbp
 * @ClassName DeviceConnector
 * @Description 设备连接器
 * @date 2018 /12/14 上午10:37
 */
public class DeviceConnector extends Connector{
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(DeviceConnector.class);


    @Override
    void connect() {
        logger.info("begin connect from client...");
    }


    @Override
    void destroy() {

    }
}
