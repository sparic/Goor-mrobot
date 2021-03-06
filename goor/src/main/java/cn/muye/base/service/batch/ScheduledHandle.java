package cn.muye.base.service.batch;

import cn.mrobot.utils.TimeUtil;
import cn.muye.base.service.ScheduledHandleService;
import cn.muye.base.service.imp.ScheduledHandleServiceImp;
import org.apache.log4j.Logger;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledHandle {
    private static Logger logger = Logger.getLogger(ScheduledHandle.class);

    private final ScheduledExecutorService scheduledExecutor;

    public ScheduledHandle(ScheduledExecutorService scheduledExecutor){
        this.scheduledExecutor = scheduledExecutor;
        this.sendMessageScheduled();
        this.replyMessageScheduled();
        this.rosHealthCheckScheduled();
        this.downloadResourceScheduled();
        this.publishRosScheduled();
        this.executeTwentyThreeAtNightPerDay();
    }

    /**
     * 发送消息
     */
    public void sendMessageScheduled() {
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
//                    logger.info("schedule sendMessageScheduled start");
                    ScheduledHandleService service = new ScheduledHandleServiceImp();
                    service.sendMessage();
                } catch (Exception e) {
                    logger.error("schedule sendMessageScheduled exception", e);
                }
            }
        }, 5, 10, TimeUnit.SECONDS);
    }


    /**
     * 回执
     */
    public void replyMessageScheduled() {
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
//                    logger.info("schedule replyMessageScheduled start");
                    ScheduledHandleService service = new ScheduledHandleServiceImp();
                    service.receiveMessage();
                } catch (Exception e) {
                    logger.error("schedule replyMessageScheduled exception", e);
                }
            }
        }, 6, 10, TimeUnit.SECONDS);
    }


    /**
     * 下载资源
     */
    public void downloadResourceScheduled() {
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
//                    logger.info("schedule downloadResourceScheduled start");
                    ScheduledHandleService service = new ScheduledHandleServiceImp();
                    service.downloadResource();
                } catch (Exception e) {
                    logger.error("schedule downloadResourceScheduled exception", e);
                }
            }
        }, 3, 5, TimeUnit.SECONDS);
    }


    /**
     * publish ros
     */
    public void publishRosScheduled() {
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
//                    logger.info("schedule publishRosScheduled start");
                    ScheduledHandleService service = new ScheduledHandleServiceImp();
                    service.publishMessage();
                } catch (Exception e) {
                    logger.error("schedule publishRosScheduled exception", e);
                }
            }
        }, 15, 1, TimeUnit.SECONDS);
    }

    /**
     * ros重连
     */
    public void rosHealthCheckScheduled() {
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    ScheduledHandleService service = new ScheduledHandleServiceImp();
                    service.rosHealthCheck();
                    logger.info("schedule rosHealthCheckScheduled");
                } catch (Exception e) {
                    logger.error("schedule rosHealthCheckScheduled exception", e);
                }
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

    /**
     * 每天晚上23点执行一次
     * 每天定时安排任务进行执行
     */
    public void executeTwentyThreeAtNightPerDay() {
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay  = TimeUtil.getTimeMillis("23:00:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ScheduledHandleService service = new ScheduledHandleServiceImp();
                            service.executeTwentyThreeAtNightPerDay();
//                            logger.info("schedule sendMessageWithLock");
                        } catch (Exception e) {
                            logger.error("schedule sendMessageWithLock exception", e);
                        }
                    }
                },
                initDelay,
                oneDay,
                TimeUnit.MILLISECONDS);
    }

}
