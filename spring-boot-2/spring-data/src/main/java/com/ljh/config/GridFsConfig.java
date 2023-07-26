package com.ljh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * GridFSConfig
 *
 * @author ljh
 * created on 2022/12/6 9:34
 */
@Configuration
public class GridFsConfig {
    @Bean("myGridFsTemplate")
    public GridFsTemplate gridFsTemplate(MongoDatabaseFactory dbFactory, MongoConverter converter) {
        // 自定义 bucketName
        return new GridFsTemplate(dbFactory, converter, "file");
    }
}
