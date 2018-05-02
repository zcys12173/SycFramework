package com.syc.framework.network.config;

/**
 * Created by shiyucheng on 2018/1/2.
 * Http配置文件
 */

public class HttpConfig {
    public String baseUrl;//请求地址
    public long timeout;//链接超时
    public boolean debug;//是否是调试状态
    public boolean environment;//运行环境(测试？生产？)
}
