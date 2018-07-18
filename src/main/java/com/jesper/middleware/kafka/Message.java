package com.jesper.middleware.kafka;

import lombok.Data;

import java.util.Date;

/**
 * Created by jiangyunxiong on 2018/7/13.
 */
@Data
public class Message {

    private Long id;

    private String msg; //消息

    private Date sendTime; //时间戳
}
