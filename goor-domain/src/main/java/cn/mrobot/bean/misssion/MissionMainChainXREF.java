package cn.mrobot.bean.misssion;

/**
 * Created with IntelliJ IDEA.
 * Project Name : goor-common
 * User: Jelynn
 * Date: 2017/6/13
 * Time: 9:37
 * Describe:
 * Version:1.0
 */
public class MissionMainChainXREF {

	private Long id;

	private MissionMain missionMain;

	private Long missionMainId;

	private MissionChain missionChain;

	private Long missionChainId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MissionMain getMissionMain() {
		return missionMain;
	}

	public void setMissionMain(MissionMain missionMain) {
		this.missionMain = missionMain;
	}

	public Long getMissionMainId() {
		return missionMainId;
	}

	public void setMissionMainId(Long missionMainId) {
		this.missionMainId = missionMainId;
	}

	public MissionChain getMissionChain() {
		return missionChain;
	}

	public void setMissionChain(MissionChain missionChain) {
		this.missionChain = missionChain;
	}

	public Long getMissionChainId() {
		return missionChainId;
	}

	public void setMissionChainId(Long missionChainId) {
		this.missionChainId = missionChainId;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MissionMainChainXREF that = (MissionMainChainXREF) o;

		if (missionMainId != null && missionChainId != null){
			return missionChainId.equals(that.getMissionChainId()) && missionMainId.equals(that.getMissionMainId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (missionMainId != null ? missionMainId.hashCode() : 0);
		result = 31 * result + (missionChainId != null ? missionChainId.hashCode() : 0);
		return result;
	}
}
