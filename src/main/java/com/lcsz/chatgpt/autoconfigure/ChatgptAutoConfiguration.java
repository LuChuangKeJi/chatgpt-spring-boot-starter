package com.lcsz.chatgpt.autoconfigure;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.util.Proxys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangdingfei
 * @date 2023/4/24 23:00
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = {"chatgpt.enabled"}, havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(ChatgptProperties.class)
public class ChatgptAutoConfiguration {

    @Autowired
    private ChatgptProperties chatgptProperties;

    @Bean
    public ChatGPT chatGPT() {
        ChatGPT chatGPT = ChatGPT.builder()
                .proxy(getProxy())
                .timeout(chatgptProperties.getTimeout())
                .apiHost(chatgptProperties.getApiHost())
                .apiKeyList(getApiKeys())
                .build().init();

        return chatGPT;
    }

    @Bean
    public ChatGPTStream chatGPTStream() {

        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .proxy(getProxy())
                .timeout(chatgptProperties.getTimeout())
                .apiHost(chatgptProperties.getApiHost())
                .apiKeyList(getApiKeys())
                .build().init();

        return chatGPTStream;
    }

    private Proxy getProxy() {
        Proxy proxy;

        if (StringUtils.hasLength(chatgptProperties.getProxyHttpHost()) && null != chatgptProperties.getProxyHttpPort()) {
            proxy = Proxys.http(chatgptProperties.getProxyHttpHost(), chatgptProperties.getProxyHttpPort());
        } else if (StringUtils.hasLength(chatgptProperties.getProxySocks5Host()) && null != chatgptProperties.getProxySocks5Port()) {
            proxy = Proxys.socks5(chatgptProperties.getProxySocks5Host(), chatgptProperties.getProxySocks5Port());
        } else {
            proxy = Proxy.NO_PROXY;
        }

        return proxy;
    }

    private List<String> getApiKeys() {
        List<String> apiKeyList = new ArrayList<>();

        if (StringUtils.hasLength(chatgptProperties.getApiKey())) {
            apiKeyList.add(chatgptProperties.getApiKey());
        }
        if (null != chatgptProperties.getApiKeys() && chatgptProperties.getApiKeys().size() > 0) {
            apiKeyList.addAll(chatgptProperties.getApiKeys());
        }

        if (apiKeyList.isEmpty()) {
            throw new ChatgptException("chatgpt api_key not configured");
        }

        return apiKeyList;
    }
}
