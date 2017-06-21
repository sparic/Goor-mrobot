package cn.muye.service.mapper.config;

import cn.muye.mapper.config.AppConfigMapper;
import cn.muye.model.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by enva on 2017/5/11.
 */

@Service
@Transactional
public class AppConfigService {

    @Autowired
    private AppConfigMapper appConfigMapper;

    public AppConfig get(Integer id){
        return appConfigMapper.get(id);
    }

}
