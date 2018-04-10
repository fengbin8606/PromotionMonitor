
package com.promotion.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.promotion.pojo.SmzdmJr;
import com.promotion.service.SmzdmService;
import com.promotion.util.MD5Util;

//@EnableAsync
@Component
public class Smzdm {

    @Autowired
    SmzdmService smzdmService;

    // 什么值得买金融频道地址
    private static String url = "http://jr.smzdm.com/";

    // 爬取方法 定时爬取
    // @Async
    @Scheduled(cron = "0 */1 * * * ? ")
    public void crawl() {

        SmzdmJr smzdmJr = new SmzdmJr();

        try {

            Document doc = Jsoup.connect(url).get();

            // System.out.println(doc.toString());

            Elements elements = doc.getElementsByClass("feed-row-wide");

            for (Element li : elements) {
                // <h5> 的标题
                Elements titles = li.getElementsByClass("feed-block-title");

                // 从标题中取出网址和标题内容
                smzdmJr.setTitle(titles.get(0).getElementsByTag("a").text());
                smzdmJr.setUrl(titles.get(0).getElementsByTag("a").attr("href"));

                // System.out.println(titles.get(0).getElementsByTag("a").text());
                // System.out.println(titles.get(0).getElementsByTag("a").attr("href"));

                // 获取描述内容
                String descripe = li.getElementsByClass("feed-block-descripe").get(0).text();
                // System.out.println(descripe);

                smzdmJr.setDescripe(descripe);

                String descripeMD5 = MD5Util.MD5(descripe);

                smzdmJr.setDescripemd5(descripeMD5);

                smzdmJr.setStatus("0");

                smzdmService.insert(smzdmJr, descripeMD5);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // public static void main(String[] args) {
    // Smzdm.crawl();
    // }

}
