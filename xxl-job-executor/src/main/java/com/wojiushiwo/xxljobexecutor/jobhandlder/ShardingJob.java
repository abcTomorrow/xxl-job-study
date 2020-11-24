package com.wojiushiwo.xxljobexecutor.jobhandlder;

import com.wojiushiwo.xxljobexecutor.jobhandlder.dto.User;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author myk
 * @create 2020/11/24 下午2:53
 */
@Component
public class ShardingJob {

    private List<User> userList;

    @PostConstruct
    public void init() {

        userList = LongStream.rangeClosed(1, 10)
                .mapToObj(index -> new User(index, "wojiushiwo" + index))
                .collect(Collectors.toList());

    }

    @XxlJob(value = "shardingJobHandler")
    public ReturnT<String> execute(String param) {

        //获取分片参数
        ShardingUtil.ShardingVO shardingVo = ShardingUtil.getShardingVo();
        XxlJobLogger.log("分片参数，当前分片序号={},总分片数={}", shardingVo.getIndex(), shardingVo.getTotal());

        for (int i = 0; i < userList.size(); i++) {
            //将数据均匀地分布到各分片上执行
            if (i % shardingVo.getTotal() == shardingVo.getIndex()) {
                XxlJobLogger.log("第 {} 片, 命中分片开始处理{}", shardingVo.getIndex(), userList.get(i).toString());
            } else {
                XxlJobLogger.log("{},忽略", shardingVo.getIndex());
            }

        }
        return ReturnT.SUCCESS;
    }


}
