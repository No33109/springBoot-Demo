package com.example.demo.v1.service;

import com.example.demo.utils.RedisUtil;
import com.example.demo.v1.dao.CenterDao;
import com.example.demo.v1.domain.Center;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CenterService {

    @Resource
    private CenterDao centerDao;

    @Resource
    private RedisUtil redisUtil;


    public void save(){
        Center center = new Center();
        center.setName("zhangsan");
        centerDao.save(center);
    }

    public List<Center> findAll(){
        List<Center> centers = (List<Center>)redisUtil.get("demo_centers");
        if(CollectionUtils.isEmpty(centers)){
            List<Center> all = centerDao.findAll();
            redisUtil.set("demo_centers",all, (long) (60 * 60 * 24));
            return all;
        }
        return centers;
    }
}
