package org.hae.sfaas.global.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
@Configuration
@MapperScan("org.hae.sfaas.domain.**.**.mapper")
public class MapperSessionFactory {

    @Value("${spring.mybatis.config-location}")
    private String configFile;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setVfs(SpringBootVFS.class);

        // Load the MyBatis configuration file
        Resource configRes = new PathMatchingResourcePatternResolver().getResource(configFile);
        sessionFactory.setConfigLocation(configRes);

        return sessionFactory.getObject();
    }
}
