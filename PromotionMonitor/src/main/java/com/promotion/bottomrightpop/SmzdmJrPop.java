
package com.promotion.bottomrightpop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.promotion.pojo.SmzdmJr;
import com.promotion.pojo.SmzdmJrExample;
import com.promotion.service.SmzdmService;

//注解@EnableAsync，并给方法添加注解@Async。可以让两个定时任务并行执行
//@EnableAsync
@Component
public class SmzdmJrPop {

    @Autowired
    SmzdmService smzdmService;

    private final static String POP_TITLE = "什么值得买-金融频道";

    // @Async
    @Scheduled(cron = "0 */1 * * * ? ")
    public void pop() {

        BottomRightPop smzdmJrPop = new BottomRightPop();

        SmzdmJrExample smzdmJrExample = new SmzdmJrExample();
        smzdmJrExample.createCriteria().andStatusEqualTo("0");

        List<SmzdmJr> smzdmJrList = smzdmService.selectByExample(smzdmJrExample);

        if (!smzdmJrList.isEmpty()) {
            for (SmzdmJr smzdmJr : smzdmJrList) {
                smzdmJrPop.show(POP_TITLE, smzdmJr.getTitle());

                // 已经弹出窗口的就把status标记为1：已读
                smzdmJr.setStatus("1");
                smzdmService.updateByPrimaryKey(smzdmJr);
            }
        }
    }

}
