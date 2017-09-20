package com.spring.datasourceaop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p>
 * 多数据源的配置,并支持多从库
 * </p>
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    

    /**
     * 如果是选择使用数据库
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

}