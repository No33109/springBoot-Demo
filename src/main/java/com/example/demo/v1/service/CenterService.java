package com.example.demo.v1.service;

import com.example.demo.utils.RedisUtil;
import com.example.demo.v1.dao.CenterDao;
import com.example.demo.v1.domain.Center;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
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


    public Integer getMaxNumberLenInString(String s){
       int num = 0;
       String[] ss = s.replaceAll("[^0-9]",",").split(",");
       int maxSize = 0;
        for (String value : ss) {
            if (value.length() > maxSize) {
                maxSize = value.length();
                num = value.length();
            }
        }
        return num;
    }


    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
//        CenterService centerService = new CenterService();
//        int num = centerService.getMaxNumberLenInString("李先生15818470854");
//        System.out.println(num);


        String aa = "https://byron-atlas-resources.oss-cn-shenzhen.aliyuncs.com/workplace/file/2019年08月14日/ATLAS 杭州 PFC D01 TOKEN TOKEN（双方盖章）.pdf";
//        System.out.println(aa.substring(91));
//        aa.replace(" ","%20");
        String s = new File(aa).toURI().toASCIIString();
        System.out.println(s);
        String[] https = s.split("https:/");
        System.out.println("https://"+https[1]);


        String cc = "https://aaaa/aa/https://11121.jpg";
        String[] https1 = cc.split("https");
        System.out.println("https"+https1[1]);
    }
}
