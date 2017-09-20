package com.spring.datasourceaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Aspect
@Service
public class DatasourceAop {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DatasourceAop.class);

/*	@Around("@annotation(dataSourceManage)")
	public Object doAround(ProceedingJoinPoint joinPoint,
			DataSourceManage dataSourceManage) throws Throwable {
		LOGGER.info("DatasourceAop doAround ");
		Object retVal = null;
		boolean selectedDataSource = false;
		try {
			if (null != dataSourceManage) {
				String dbName = dataSourceManage.name();
				DataSourceHolder.setDataSource(dbName);
				LOGGER.info("DatasourceAop run dbName " + dbName);
				selectedDataSource = true;
			}
			retVal = joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		} finally {
			if (selectedDataSource) {
				DataSourceHolder.clearDataSource();
			}
		}
		return retVal;
	}*/
	
	@Before("@annotation(dataSourceManage)")
	 public void intercept(JoinPoint point, DataSourceManage dataSourceManage) throws Exception {
			LOGGER.info("DatasourceAop before ");
			boolean selectedDataSource = false;
			try {
				if (null != dataSourceManage) {
					String dbName = dataSourceManage.name();
					DataSourceHolder.setDataSource(dbName);
					LOGGER.info("DatasourceAop run dbName " + dbName);
					selectedDataSource = true;
				}
			} catch (Throwable e) {
				throw e;
			} finally {
				if (selectedDataSource) {
					DataSourceHolder.clearDataSource();
				}
			}
	    }
}
