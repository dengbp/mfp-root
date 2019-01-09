package com.yr.net.handle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Device server handler.
 *
 * @author dengbp
 * @ClassName DiscardServerHandler
 * @Description 实现接收服务
 * @date 2018 /12/24 上午10:06
 */
public class DeviceServerHandler extends ChannelHandlerAdapter {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(DeviceServerHandler.class);

    /**
     * 接收到SOCKET的时候会调用此方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf) msg;
            byte[] receiveMsgBytes = new byte[in.readableBytes()];
            in.readBytes(receiveMsgBytes);
            String message = Hex.encodeHexString(receiveMsgBytes);
            String commandType = message.substring(0, 2);
            if (StringUtils.equals("24", commandType)) {
                logger.info("GPS数据包:{}", message);
            } else {
                message = org.apache.commons.codec.binary.StringUtils.newStringUtf8(receiveMsgBytes);
                logger.info("其它数据包:{}", message);
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("server message!", CharsetUtil.UTF_8));

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    /**
     * 有新的连接加入的时候
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("新增Channel ,ChannelId = " + ctx.channel().id());
    }

    /**
     * 有连接断开被移除的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("移除Channel ,ChannelId = " + ctx.channel().id());
    }

    /**
     * 发生异常的时候调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }
}