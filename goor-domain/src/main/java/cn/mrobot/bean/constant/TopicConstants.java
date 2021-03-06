package cn.mrobot.bean.constant;

/**
 * Created with IntelliJ IDEA.
 * Project Name : goor-common
 * User: Jelynn
 * Date: 2017/6/14
 * Time: 16:29
 * Describe:
 * Version:1.0
 */
public class TopicConstants {

	public static final String AGENT_PREFIX = "agent_local"; //需要agent本地处理的topic

	public static final String DATA = "data";

	public static final String TOPIC_TYPE_STRING = "std_msgs/String";//publish的数据类型
	public static final String TOPIC_TYPE_UINT8_ARRAY = "std_msgs/UInt8MultiArray";
	//订阅topic
	public static final String DEMO = "/demo";

	//
	public static final String PUB_NAME = "pub_name";
	public static final String SUB_NAME = "sub_name";

	//工控topic
	public static final String APP_PUB = "/app_pub";
	public static final String APP_SUB = "/app_sub";
	public static final String AGENT_PUB = "/agent_pub";
	public static final String AGENT_SUB = "/agent_sub";

	//ros topic pub/sub  name
	public static final String CHARGING_STATUS_INQUIRY = "charging_status_inquiry";
	public static final String MOTION_PLANNER_MOTION_STATUS = "motion_planner_motion_status";

	public static final String STATION_LIST_GET = "station_list_get";//站信息查询，根据机器人主板编号
	public static final String ROBOT_CODE = "robot_code";//机器人主板编号
	public static final String STATION_INFO = "station_info";//机器人主板编号

	public static final String STATUS_DISPATCH = "status_dispatch";//调度任务状态

	//订阅topic
	public static final String ODOM = "/odom";//TODO test
	public static final String TOPIC_NAV_MSGS = "nav_msgs/Odometry";//TODO test

	//维持心跳topic
	public static final String CHECK_HEART_TOPIC = "/checkHeartTopic";
	public static final String CHECK_HEART_MESSAGE = "{\"data\": \"heart\"}";
	public static final Long CHECK_HEART_TOPIC_MAX = 30000L;//30秒


	//导航返回的 code 标识
	private static int MOTION_MOVING = 1;
	private static int MOTION_RECEIVED_A_CANCEL_REQUEST = 1;
	private static int MOTION_GOAL_REACHED = 3; //导航到达目标点
	private static int MOTION_ABORTED_THIS_NAVIGATION = 4;
	private static int MOTION_SET_GOAL_FAILED = 5;
	private static int MOTION_CANCEL_THIS_NAVIGATION = 6;
	private static int MOTION_ROBOT_STUCK_IN_THE_PLACE = 10;


	//导航点相关
	public static final String POINT_LOAD = "point_load";
	public static final String SCENE_NAME = "scene_name";
	public static final String MAP_NAME = "map_name";
	public static final String POINTS = "points";


	//agent定义的topic
	public static final String AGENT_LOCAL_MAP_UPLOAD = "agent_local_map_upload";
	public static final String ZIP_FILE_SUFFIX = ".zip";
}
