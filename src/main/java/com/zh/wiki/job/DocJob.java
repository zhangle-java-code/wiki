package com.zh.wiki.job;

import com.zh.wiki.service.DocService;
import com.zh.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
// ? quartz 框架的定时任务
// ? Cron表达式
// ? 线程池

@Component
public class DocJob {

    private static final Logger LOG = LoggerFactory.getLogger(DocJob.class);

    @Resource
    private DocService docService;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 每30秒更新电子书信息
     */
    // !自定义cron 表达式跑批
    // 只有等上一次执行完成，下一次才会在下一个时间点执行，错过就错过

    @Scheduled(cron = "0 0/10 * * * ?")
    public void cron() {
        // !增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("cron/定时任务/更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        docService.updateEbookInfo();
        LOG.info("cron/定时任务/更新电子书下的文档数据结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }

}
