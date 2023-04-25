package com.lcsz.chatgpt.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author zhangdingfei
 * @date 2023/4/24 23:02
 */
@ConfigurationProperties(prefix = "chatgpt")
@Data
public class ChatgptProperties {

    private String apiHost = "https://api.openai.com/";

    private String apiKey;

    private List<String> apiKeys;

    private long timeout = 300;

    private String proxyHttpHost;

    private Integer proxyHttpPort;

    private String proxySocks5Host;

    private Integer proxySocks5Port;
}
