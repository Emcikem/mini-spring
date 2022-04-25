package com.lyq.minispring.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 工厂模式返回resource
 */
public class DefaultResourceLoader implements ResourceLoader{

    private static final String CLASSPATH_URL_PREFIX = "classpath:";


    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // classpath下得资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 尝试当成url来处理
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 当成文件系统下得资源处理
                return new FileSystemResource(location);
            }
        }
    }
}
