package com.wojiushiwo.xxljobexecutor.config.xxljob;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author myk
 * @create 2020/11/22 下午7:41
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {

    @NestedConfigurationProperty
    private XxlAdminProperties admin = new XxlAdminProperties();

    @NestedConfigurationProperty
    private XxlExecutorProperties executor = new XxlExecutorProperties();


    /**
     * xxl 管控台相关属性
     */
    @Data
    public class XxlAdminProperties {
        /**
         * 调度中心部署跟地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。
         * 执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调"；为空则关闭自动注册；
         */
        private String addresses;
    }

    /**
     * xxl 调度中心配置
     */
    @Data
    public class XxlExecutorProperties {
        /**
         * 执行器AppName [选填]：执行器心跳注册分组依据；为空则关闭自动注册
         */
        private String appName;

        /**
         * 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP
         * ，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"
         */
        private String ip;

        /**
         * 执行器端口号 [选填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；
         */
        private Integer port;

        /**
         * 执行器通讯TOKEN [选填]：非空时启用；
         */
        private String accessToken;

        /**
         * 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；
         */
        private String logPath;

        /**
         * 执行器日志保存天数 [选填] ：值大于3时生效，启用执行器Log文件定期清理功能，否则不生效；
         */
        private Integer logRetentionDays = 30;
    }
}
