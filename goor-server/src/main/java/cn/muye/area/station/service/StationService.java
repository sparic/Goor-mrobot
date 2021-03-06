package cn.muye.area.station.service;


import cn.mrobot.bean.area.station.Station;
import cn.mrobot.utils.WhereRequest;

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
public interface StationService {

	long save(Station station);

	void update(Station station );

	Station findById(long id, long storeId);

	List<Station> list(WhereRequest whereRequest, long storeId);

	List<Station> listByName(String name);

	void delete(Station station);
}

