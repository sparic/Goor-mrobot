DROP TABLE IF EXISTS APP_CONFIG;
DROP TABLE IF EXISTS OFFLINE_MESSAGE;
DROP TABLE IF EXISTS RECEIVE_MESSAGE;


CREATE TABLE IF NOT EXISTS APP_CONFIG
(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  MPUSH_PUBLICKEY VARCHAR(256) NOT NULL COMMENT 'publickey',
  MPUSH_ALLOCSERVER VARCHAR(256) NOT NULL COMMENT 'allocserver',
  MPUSH_PUSHSERVER VARCHAR(256) NOT NULL COMMENT 'pushserver',
  MPUSH_DEVICEID VARCHAR(256) NOT NULL COMMENT 'deviceid',
  MPUSH_OSNAME VARCHAR(256) NOT NULL COMMENT 'osname',
  MPUSH_OSVERSION VARCHAR(256) NOT NULL COMMENT 'osversion',
  MPUSH_CLIENTVERSION VARCHAR(256) NOT NULL ,
  MPUSH_USERID VARCHAR(256) NOT NULL COMMENT 'userid',
  MPUSH_TAGS VARCHAR(256) NOT NULL COMMENT 'tags',
  MPUSH_SESSIONSTORAGEDIR VARCHAR(256) NOT NULL COMMENT 'sessionstoragedir',
  ROS_PATH VARCHAR(256) NOT NULL COMMENT 'ros_path'
);

CREATE TABLE IF NOT EXISTS OFFLINE_MESSAGE
(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  SENDER_ID VARCHAR(128) NOT NULL COMMENT '发送者ID',
  SEND_DEVICE_TYPE VARCHAR(128) NOT NULL COMMENT '发送的设备类型',
  RECEIVER_ID VARCHAR(128) NOT NULL COMMENT '接收者ID',
  RECEIVER_DEVICE_TYPE VARCHAR(128) NOT NULL COMMENT '接收的设备类型',
  WEBSOCKET_ID VARCHAR(128) COMMENT 'webSocketId，暂定回执时使用',
  RECEIPT_WEBSOCKET BIT NOT NULL COMMENT '是否给webSocket发送消息，暂定回执时使用',
  FINISH BIT NOT NULL COMMENT '是否完成消息处理',
  MESSAGE_KIND SMALLINT NOT NULL DEFAULT 0 COMMENT '消息种类，默认为0，0：文本消息，1：二进制消息',
  MESSAGE_TYPE VARCHAR(256) NOT NULL COMMENT '消息类型',
  FAIL_RESEND BIT NOT NULL COMMENT '是否是否需要失败重新发送',
  SESSION_ID INTEGER NULL COMMENT 'sessionId,暂时不使用',
  MESSAGE_STATUS_TYPE SMALLINT NOT NULL DEFAULT 0 COMMENT '消息状态默认为0，1：未下载，2：下载完成，3：发送ros消息完成',
  VERSION VARCHAR(256) COMMENT '版本号',
  RELY_MESSAGE VARCHAR(256) COMMENT '回执消息',
  MESSAGE_TEXT TEXT COMMENT '文本消息',
  MESSAGE_BINARY BLOB COMMENT '二进制消息',
  SEND_COUNT INTEGER NOT NULL DEFAULT 0 COMMENT '发送次数',
  SEND_TIME DATETIME NOT NULL COMMENT '发送时间',
  UPDATE_TIME DATETIME NOT NULL COMMENT '更新时间',
  SUCCESS BIT NOT NULL COMMENT '是否发送成功'
);

CREATE TABLE IF NOT EXISTS RECEIVE_MESSAGE
(
  ID BIGINT NOT NULL,
  SENDER_ID VARCHAR(128) NOT NULL COMMENT '发送者ID',
  SEND_DEVICE_TYPE VARCHAR(128) NOT NULL COMMENT '发送的设备类型',
  RECEIVER_ID VARCHAR(128) NOT NULL COMMENT '接收者ID',
  RECEIVER_DEVICE_TYPE VARCHAR(128) NOT NULL COMMENT '接收的设备类型',
  WEBSOCKET_ID VARCHAR(128) COMMENT 'webSocketId，暂定回执时使用',
  RECEIPT_WEBSOCKET BIT NOT NULL COMMENT '是否给webSocket发送消息，暂定回执时使用',
  FINISH BIT NOT NULL COMMENT '是否完成消息处理',
  MESSAGE_KIND SMALLINT NOT NULL DEFAULT 0 COMMENT '消息种类，默认为0，0：文本消息，1：二进制消息',
  MESSAGE_TYPE VARCHAR(256) NOT NULL COMMENT '消息类型',
  FAIL_RESEND BIT NOT NULL COMMENT '是否是否需要失败重新发送',
  SESSION_ID INTEGER NULL COMMENT 'sessionId,暂时不使用',
  MESSAGE_STATUS_TYPE SMALLINT NOT NULL DEFAULT 0 COMMENT '消息状态默认为0，1：未下载，2：下载完成，3：发送ros消息完成',
  VERSION VARCHAR(256) COMMENT '版本号',
  RELY_MESSAGE VARCHAR(256) COMMENT '回执消息',
  MESSAGE_TEXT TEXT COMMENT '文本消息',
  MESSAGE_BINARY BLOB COMMENT '二进制消息',
  SEND_COUNT INTEGER NOT NULL DEFAULT 0 COMMENT '发送次数',
  SEND_TIME DATETIME NOT NULL COMMENT '发送时间',
  UPDATE_TIME DATETIME NOT NULL COMMENT '更新时间',
  SUCCESS BIT NOT NULL COMMENT '是否发送成功',
  PRIMARY KEY (ID, SENDER_ID)
);
