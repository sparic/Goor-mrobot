package cn.muye.base.controller;

import cn.mrobot.bean.constant.TopicConstants;
import cn.mrobot.bean.enums.DeviceType;
import cn.mrobot.bean.enums.MessageStatusType;
import cn.mrobot.bean.enums.MessageType;
import cn.muye.base.bean.*;
import cn.muye.base.service.MessageSendService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

@Controller
public class ExampleController {
    private Logger logger = Logger.getLogger(ExampleController.class);

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping(value = "test1", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult test1(@RequestParam("aa")String aa) {
        logger.info("sssssssssssssssssss======" + messageSendService);
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setTopicName("/enva_test");
        commonInfo.setTopicType(TopicConstants.TOPIC_TYPE_STRING);
        commonInfo.setLocalFileName("test.apk");
        commonInfo.setLocalPath("E:/TEMP/TEST/aaa/ccc/test");
        commonInfo.setRemoteFileUrl("http://myee7.com/push_test/105/app/Gaea/Gaea_1.1.0_11069_20160816.apk");
        commonInfo.setMD5("e3d9ef05786e10c1fdd4e55633c12c99");
        commonInfo.setPublishMessage(JSON.toJSONString(commonInfo));
        String text = JSON.toJSONString(commonInfo);
        byte[] b = text.getBytes();
        MessageInfo info = new MessageInfo(MessageType.EXECUTOR_RESOURCE, text, b);
        info.setMessageStatusType(MessageStatusType.FILE_NOT_DOWNLOADED);
        info.setReceiptWebSocket(true);
        info.setWebSocketId("user-9");
        info.setSendDeviceType(DeviceType.GOOR_SERVER);
        info.setReceiverDeviceType(DeviceType.GOOR);
        info.setMessageKind(0);
        info.setMessageType(MessageType.EXECUTOR_UPGRADE);
        info.setMessageStatusType(MessageStatusType.INIT);
        info.setSendTime(new Date());
        info.setUpdateTime(new Date());
        info.setSendCount(0);
//        info.setMessageKind(1);

        messageSendService.sendMessage("goor-server", info);
//        messageSendService.sendNoStatusMessage("cookyPlus1301_test1", info);
        return AjaxResult.success();
    }

    @RequestMapping(value = "test2", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult test2(@RequestParam("aa")String aa) {
        logger.info("sssssssssssssssssss======" + messageSendService);
        String text = JSON.toJSONString(new MessageInfo(MessageType.REPLY, null, null));
        byte[] b = text.getBytes();
        MessageInfo info = new MessageInfo(MessageType.EXECUTOR_LOG, text, b);
        info.setMessageStatusType(MessageStatusType.FILE_NOT_DOWNLOADED);
        messageSendService.sendNoStatusMessage("cookyPlus1301_test1", info);
        return AjaxResult.success();
    }

    @RequestMapping(value = "test3", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult test3(@RequestParam("aa")String aa) {
        logger.info("sssssssssssssssssss======" + messageSendService);
        String text = JSON.toJSONString(new MessageInfo(MessageType.REPLY, null, null));
        byte[] b = text.getBytes();
        MessageInfo info = new MessageInfo(MessageType.EXECUTOR_RESOURCE, text, b);
        messageSendService.sendNoStatusMessage("cookyPlus1301_test1", info);
        return AjaxResult.success();
    }

    @RequestMapping(value = "test4", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult test4(@RequestParam("aa")String aa) {
        return AjaxResult.success(aa);
    }

    @RequestMapping(value = "testSendStationInfo", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult abcd(@RequestParam("aa")String aa) {
        logger.info("sssssssssssssssssss======" + messageSendService);

        //TODO 现在是假数据，到时候得应用发送机器主板编号，从数据库查该机器人可见的站点信息，返回给应用
        //TODO X86 agent告知应用机器人调度状态信息接口。——通过app_sub发
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TopicConstants.SUB_NAME, TopicConstants.STATUS_DISPATCH);
        JSONObject messageObject = new JSONObject();
        messageObject.put(TopicConstants.DATA, JSON.toJSONString(jsonObject));


        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setTopicName(TopicConstants.APP_SUB);
        commonInfo.setTopicType(TopicConstants.TOPIC_TYPE_STRING);
        commonInfo.setPublishMessage(messageObject.toJSONString());
        String text = JSON.toJSONString(commonInfo);
        byte[] b = text.getBytes();
        //用EXECUTOR_COMMAND就会发topic
        MessageInfo info = new MessageInfo(MessageType.EXECUTOR_COMMAND, text, b);
        messageSendService.sendNoStatusMessage("cookyPlus1301chay", info);

        return AjaxResult.success();
    }

    @RequestMapping(value = "testRabbitMQ", method= RequestMethod.GET)
    @ResponseBody
    public void testRabbitMQ(HttpServletRequest request) {
//        rabbitTemplate.setReplyTimeout(20000);
//        Message response= (Message) rabbitTemplate.sendAndReceive(new Message(str.getBytes(),new MessageProperties()));
//        UserBean bean = new UserBean();
//        bean.setName("enva");
//        bean.setAge(32);
//        bean.setNum(1000000000L);
//        rabbitTemplate.setExchange("exch");
//        rabbitTemplate.setRoutingKey("key");
        Object response= rabbitTemplate.convertSendAndReceive("directExchange","direct.common","sssddddddddddddd");
//        rabbitTemplate.convertAndSend("directExchange","direct.command","sssddddddddddddd");
//        rabbitTemplate.convertAndSend("direct.command","sssddddddddddddd");
//        Object response= rabbitTemplate.convertSendAndReceive("fanoutExchange","","sssddddddddddddd");
//        rabbitTemplate.convertAndSend("enva_send_only","");
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        rabbitTemplate.convertAndSend("spring-boot-exchange", "spring-boot-routingKey", bean, correlationId);
        System.out.println("response="+ response);
    }

    //http://localhost:8080/ws
    @MessageMapping("/welcome")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @SendTo("/topic/getResponse")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public Response say(Message message) throws Exception {
        Thread.sleep(1000);
        return new Response("Welcome, " + message.getName() + "!");
    }

    @MessageMapping("/chat")
    //在springmvc 中可以直接获得principal,principal 中包含当前用户的信息
    public void handleChat(Principal principal, Message message) {
//      public void handleChat(Message message) {

        /**
         * 此处是一段硬编码。如果发送人是wyf 则发送给 wisely 如果发送人是wisely 就发送给 wyf。
         * 通过当前用户,然后查找消息,如果查找到未读消息,则发送给当前用户。
         */
//        if (principal.getName().equals("admin")) {
//            //通过convertAndSendToUser 向用户发送信息,
//            // 第一个参数是接收消息的用户,第二个参数是浏览器订阅的地址,第三个参数是消息本身
//
//            messagingTemplate.convertAndSendToUser("abel",
//                    "/queue/notifications", principal.getName() + "-send:"
//                            + message.getName());
//            messagingTemplate.convertAndSendToUser("admin",
//                    "/queue/notifications", principal.getName() + "-send:"
//                            + message.getName());
//        } else {
//            messagingTemplate.convertAndSendToUser("admin",
//                    "/queue/notifications", principal.getName() + "-send:"
//                            + message.getName());
//            messagingTemplate.convertAndSendToUser("abel",
//                    "/queue/notifications", principal.getName() + "-send:"
//                            + message.getName());
//        }
        //通过convertAndSendToUser 向用户发送信息,
        // 第一个参数是接收消息的用户,第二个参数是浏览器订阅的地址,第三个参数是消息本身

        messagingTemplate.convertAndSendToUser("abel",
                "/queue/notifications", "abel" + "-send:"
                        + message.getName());
        messagingTemplate.convertAndSendToUser("admin",
                "/queue/notifications", "admin" + "-send:"
                        + message.getName());
    }

}
