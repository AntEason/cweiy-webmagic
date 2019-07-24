package priv.cweiy.pro.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import priv.cweiy.pro.entity.JavaBokeModel;
import priv.cweiy.pro.repository.TestRepository;
import priv.cweiy.pro.utils.SnowFlake;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yichen
 * @version V1.0
 * @ClassName: TestService
 * @Description: (用一句话描述该文件做什么)
 * @Date 2019/7/24 14:23
 */
@Service
public class TestService implements PageProcessor {
    @Resource
    TestRepository testRepository;
    //标题和链接获取
    private static String  TITLEQUERY="div.post_item_body h3 a.titlelnk";
    //作者
    private static String AUTHORQUERY="div.post_item_foot a.lightblue ";
    //简介
    private static String SUMMARYQUERY="div.post_item_body p.post_item_summary";
    //初始化带爬取网页地址
    private static List<String> urls(){
        List<String> listUrl =new ArrayList<String>();

        for (int i = 2; i <=200; i++) {

            //listUrl.add("http://www.cnblogs.com/cate/java/"+i);
            listUrl.add("http://www.cnblogs.com/cate/java/"+i);
        }
        listUrl.toArray(new String[listUrl.size()]);
        return listUrl;
    }
    /**
     *
     * jsoup根据 html 字符串和语法获取内容;
     * @date   2017年8月31日
     * @param htmlText
     * @return
     */
    private static String seletDocumentText(String htmlText,String Query){
        Document doc = Jsoup.parse(htmlText);
        String select = doc.select(Query).text();
        return select;
    }
    /**
     *
     * jsoup根据 html 字符串和语法获取链接地址;

     * @date   2017年8月31日
     * @param htmlText
     * @return
     */
    private static String seletDocumentLink(String htmlText,String Query){
        Document doc = Jsoup.parse(htmlText);
        String select = doc.select(Query).attr("href");
        return select;
    }
    private static final SnowFlake snowFlake = new SnowFlake(1, 1);
    @Override
    public void process(Page page) {
        page.addTargetRequests(urls());
        //div[@class='post_item']//div[@class='post_item_body']//h3//a[@class='titlelnk']/text()'
        // 定义如何抽取页面信息，并保存下来
        List<String> htmls = page.getHtml().xpath("//div[@class='post_item']/html()").all();
        for (String html : htmls) {
            JavaBokeModel javaBoke = new JavaBokeModel();
            //标题和链接
            String title = seletDocumentText(html, TITLEQUERY);
            String linke = seletDocumentLink(html, TITLEQUERY);
            //作者和作者主页
            String author = seletDocumentText(html, AUTHORQUERY);
            String authorUrl = seletDocumentLink(html, AUTHORQUERY);
            //简介
            String summary = seletDocumentText(html, SUMMARYQUERY);
            javaBoke.setId(snowFlake.nextId());
            javaBoke.setTitle(title);
            javaBoke.setAuthor(author);
            javaBoke.setAuthorUrl(authorUrl);
            javaBoke.setLinke(linke);
            javaBoke.setSummary(summary);
            testRepository.save(javaBoke);
        }
    }
    @Override
    public Site getSite() {
        //抓去网站的相关配置包括：编码、重试次数、抓取间隔
        return Site.me().setSleepTime(1000).setRetryTimes(10);
    }
}
