package priv.cweiy.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.cweiy.pro.service.TestService;
import us.codecraft.webmagic.Spider;

import java.util.Date;

/**
 * @author yichen
 * @version V1.0
 * @ClassName: TestController
 * @Description: (用一句话描述该文件做什么)
 * @Date 2019/7/24 14:20
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;
    @GetMapping("/query")
    public void query(){
        long startTime ,endTime;
        System.out.println("========小爬虫【启动】喽！=========");
        startTime = System.currentTimeMillis();
        //入口
        Spider create = Spider.create(testService);
        //定义入口地址
        create.addUrl("http://www.cnblogs.com/cate/java/").thread(5).run();
        endTime =  System.currentTimeMillis();
        System.out.println("========小爬虫【结束】喽！=========");
        System.out.println("用时为："+(endTime-startTime)/1000+"s");
    }
}
