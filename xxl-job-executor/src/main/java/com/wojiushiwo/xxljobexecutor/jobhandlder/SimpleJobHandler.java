package com.wojiushiwo.xxljobexecutor.jobhandlder;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author myk
 * @create 2020/11/24 下午2:03
 */
@Component
public class SimpleJobHandler {

    @XxlJob(value ="simpleJobHandler" )
    public ReturnT<String> execute(String param) throws InterruptedException {

        IntStream.rangeClosed(1,20).forEach(index->{
            XxlJobLogger.log("simpleJobHandler>>"+index);
        });
        //官方文档说 如果任务超时 是采用interrupt机制打断子线程的，因此需要将InterruptedException 向上抛出
        //不能catch，否则任务超时后 任务还会被正常执行完
        Thread.sleep(ThreadLocalRandom.current().nextInt(10000));

        //任务超时后 这句日志不会被打印出来
        XxlJobLogger.log("执行完毕");

        return ReturnT.SUCCESS;
    }

}
