package cn.mrobot.bean.area.point;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

/**
 * Created by Chay on 2017/6/7.
 * 导航目标点
 */
@Table(name = "A_MAP_POINT")
public class MapPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 点名：唯一标识符
     */
	@JSONField(name = "point_name")
	@Column(name = "POINT_NAME")
    private String pointName;
    /**
     * 点别名：显示名称
     */
	@JSONField(name = "point_alias")
	@Column(name = "POINT_ALIAS")
    private String pointAlias;
    /**
     * 地图场景名
     */
	@JSONField(name = "scene_name")
	@Column(name = "SCENE_NAME")
    private String sceneName;
    /**
     * 地图名
     */
	@JSONField(name = "map_name")
	@Column(name = "MAP_NAME")
    private String mapName;
    /**
     * 坐标x
     */
	@JSONField(name = "x")
	@Column(name = "X")
    private double x;
    /**
     * 坐标y
     */
	@JSONField(name = "y")
	@Column(name = "Y")
    private double y;
    /**
     * 坐标旋转角度
     */
	@JSONField(name = "th")
	@Column(name = "TH")
    private double th;

    /**
     * 点类型索引
     * */
	@JSONField(name = "point_type")
	@Column(name = "MAP_POINT_TYPE_ID")
    private int mapPointTypeId;

	/**
	 * 点类型:不存库
	 * */
	@Transient
	@JSONField(name = "map_point_type")
	private String mapPointType;

	@Column(name = "POINT_LEVEL")
	@JSONField(name = "point_level")
	private int pointLevel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointAlias() {
        return pointAlias;
    }

    public void setPointAlias(String pointAlias) {
        this.pointAlias = pointAlias;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getTh() {
        return th;
    }

    public void setTh(double th) {
        this.th = th;
    }

    public int getMapPointTypeId() {
        return mapPointTypeId;
    }

    public void setMapPointTypeId(int mapPointTypeId) {
        this.mapPointTypeId = mapPointTypeId;
    }

	public String getMapPointType() {
		return mapPointType;
	}

	public void setMapPointType(String mapPointType) {
		this.mapPointType = mapPointType;
	}

	public int getPointLevel() {
		return pointLevel;
	}

	public void setPointLevel(int pointLevel) {
		this.pointLevel = pointLevel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MapPoint mapPoint = (MapPoint) o;

		return mapPoint.getPointName().equals(pointName) && mapPoint.getSceneName().equals(sceneName);

	}

	@Override
	public int hashCode() {
		int result = pointName != null ? pointName.hashCode() : 0;
		result = 31 * result + (sceneName != null ? sceneName.hashCode() : 0);
		return result;
	}
}
