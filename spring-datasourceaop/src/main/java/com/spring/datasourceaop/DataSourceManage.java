package com.spring.datasourceaop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源管理，使用name注入
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceManage {
	String name() default "";
	// 数据库的数量，默认只多数据源，故默认为0，多从库的可设置为从库数量
	int dbSize() default 0;
}