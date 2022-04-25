package com.lyq.minispring.ioc;

import cn.hutool.core.io.IoUtil;
import com.lyq.minispring.core.io.DefaultResourceLoader;
import com.lyq.minispring.core.io.FileSystemResource;
import com.lyq.minispring.core.io.Resource;
import com.lyq.minispring.core.io.UrlResource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        // 加载classpath下的资源
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        assertThat(content).isEqualTo("111111\n");

        // 加载文件系统资源
        resource = resourceLoader.getResource("src/main/resources/hello.txt");
        assertThat(resource instanceof FileSystemResource).isTrue();
        inputStream =  resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        assertThat(content).isEqualTo("111111\n");

        // 加载url资源
        resource = resourceLoader.getResource("https://github.com/DerekYRC/mini-spring/blob/main/README.md");
        assertThat(resource instanceof UrlResource).isTrue();
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}
