package cn.mrobot.bean.misssion;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Project Name : djinn
 * User: Jelynn
 * Date: 2017/3/21
 * Time: 16:41
 * Describe: 子任务
 * Version:1.0
 */
public class MissionNode {

	private Long id;

	private String name;

	private String description; //描述

	private int repeatCount;

	private Long intervalTime;

	private String data;//任务详细/功能数据

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private Integer priority;//优先级

	private Long missionChainId;

	private Long featureItemId;

	private FeatureItem featureItem;

	private Long featureItemTypeId;

	private FeatureItemType featureItemType;

	public static class Builder {
		private String name;
		private String description;
		private int repeatCount;
		private Long intervalTime;
		private String data;
		private Date createTime;
		private Date updateTime;
		private Integer priority;//优先级
		private Long missionChainId;
		private Long featureItemId;
		private FeatureItem featureItem;
		private Long featureItemTypeId;
		private FeatureItemType featureItemType;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder repeatCount(int repeatCount) {
			this.repeatCount = repeatCount;
			return this;
		}

		public Builder intervalTime(Long intervalTime) {
			this.intervalTime = intervalTime;
			return this;
		}

		public Builder data(String data) {
			this.data = data;
			return this;
		}

		public Builder createTime(Date createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder updateTime(Date updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public Builder priority(Integer priority) {
			this.priority = priority;
			return this;
		}

		public Builder featureItemId(Long featureItemId) {
			this.featureItemId = featureItemId;
			return this;
		}

		public Builder missionChainId(Long missionChainId) {
			this.missionChainId = missionChainId;
			return this;
		}

		public Builder featureItem(FeatureItem featureItem) {
			this.featureItem = featureItem;
			return this;
		}

		public Builder featureItemTypeId(Long featureItemTypeId) {
			this.featureItemTypeId = featureItemTypeId;
			return this;
		}

		public Builder featureItemType(FeatureItemType featureItemType) {
			this.featureItemType = featureItemType;
			return this;
		}
		public MissionNode build() {
			return new MissionNode(this);
		}
	}

	public MissionNode() {
	}

	private MissionNode(Builder builder) {
		name = builder.name;
		description = builder.description;
		repeatCount = builder.repeatCount;
		intervalTime = builder.intervalTime;
		data = builder.data;
		createTime = builder.createTime;
		updateTime = builder.updateTime;
		priority = builder.priority;
		missionChainId = builder.missionChainId;
		featureItemId = builder.featureItemId;
		featureItem = builder.featureItem;
		featureItemTypeId = builder.featureItemTypeId;
		featureItemType = builder.featureItemType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Long getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(Long intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getMissionChainId() {
		return missionChainId;
	}

	public void setMissionChainId(Long missionChainId) {
		this.missionChainId = missionChainId;
	}

	public Long getFeatureItemId() {
		return featureItemId;
	}

	public void setFeatureItemId(Long featureItemId) {
		this.featureItemId = featureItemId;
	}

	public FeatureItem getFeatureItem() {
		return featureItem;
	}

	public void setFeatureItem(FeatureItem featureItem) {
		this.featureItem = featureItem;
	}

	public Long getFeatureItemTypeId() {
		return featureItemTypeId;
	}

	public void setFeatureItemTypeId(Long featureItemTypeId) {
		this.featureItemTypeId = featureItemTypeId;
	}

	public FeatureItemType getFeatureItemType() {
		return featureItemType;
	}

	public void setFeatureItemType(FeatureItemType featureItemType) {
		this.featureItemType = featureItemType;
	}
}
