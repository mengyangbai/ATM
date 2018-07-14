package Bai.ATM.datasource;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    /**
     * 注入环境变量的值
     */
    @Autowired
    private Environment environment;
 
    /**
     * 获取数据源DataSource
     * @return
     */
    @Bean
    public DataSource basicDataSource() {
    	BasicDataSource basicDataSource = new BasicDataSource();
    	basicDataSource.setUrl(environment.getProperty("spring.datasource.url"));
    	basicDataSource.setUsername(environment.getProperty("spring.datasource.username"));
    	basicDataSource.setPassword(environment.getProperty("spring.datasource.password"));        
        return basicDataSource;
    }
 
    /**
     * @param druidDataSource
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(DataSource basicDataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(basicDataSource);
        LogFactory.useLog4JLogging();
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String xmlPath = environment.getProperty("mybatis.config-location");
        try {
            bean.setMapperLocations(resolver.getResources(xmlPath));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
 
    /**
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
 
    /**
     * 增加事务
     * @param druidDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource basicDataSource) {
        return new DataSourceTransactionManager(basicDataSource);
    }
}