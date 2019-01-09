package com.yr.net.connect;

/**
 * The type Connector.
 *
 * @author dengbp
 * @ClassName IConnector
 * @Description 连接器接口
 * @date 2018 /12/14 上午10:40
 */
public abstract class Connector {

    /**
     * Description 创建连接
     *
     * @return void
     * @Author dengbp
     * @Date 上午10 :41 2018/12/14
     * @Param []
     */
    abstract void connect();

    /**
     * Description 断开连接
     *
     * @return void
     * @Author dengbp
     * @Date 上午10 :41 2018/12/14
     * @Param []
     */
    abstract void destroy();
}
