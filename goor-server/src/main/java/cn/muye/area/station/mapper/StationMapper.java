package cn.muye.area.station.mapper;


import cn.mrobot.bean.area.station.Station;
import cn.muye.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project Name : goor-server
 * User: Chay
 * Date: 2017/6/17
 * Time: 11:36
 * Describe:
 * Version:1.0
 */
public interface StationMapper  extends MyMapper<Station> {

//	long save(Station station);
//
//	void update(Station station );
//
//	Station findById(long id);
//
	List<Station> list(@Param("name") Object name);
}

