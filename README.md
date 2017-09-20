# spring-datasourceaop  spring 动态创建数据源

解决方案：

　　　　spring提供了一个类，AbstractRoutingDataSource，可以创建多个数据库，并在几个数据库中进行切换。
    建议读者在读本文之前先了解一下这个类的使用

　　　　afterPropertiesSet()，

　　　　determineCurrentLookupKey()，

　　　　determineTargetDataSource(), 

　　　　上面这3个方法是AbstractRoutingDataSource类中的3个方法，这个方案也是基于这3个方法来实现的,
    
    Spring动态切换数据库的原理是通过继承AbstractRoutingDataSource
    
    重写determineCurrentLookupKey()方法，来决定使用那个数据库。在开启事务之前，通过改变lookupKey来达到切换数据源目的。
    
    DynamicDataSource类
    
    public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

}


DataSourceHolder类

public class DataSourceHolder {
	
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();
	

    public static void setDataSource(String dataSource){
    	holder.set(dataSource);
    }

    public static String getDataSource(){
        return holder.get();
    }

    public static void clearDataSource() {
    	holder.remove();
    }

	
}

对于注解方式，下面提供DataSource自定义注解类，在每个需要更改数据源的函数上面加上DataSource注解即可。

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceManage {
	String name() default "";
	// 数据库的数量，默认只多数据源，故默认为0，多从库的可设置为从库数量
	int dbSize() default 0;
}






@Aspect
@Service
public class DatasourceAop {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DatasourceAop.class);


	
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

配置文件

    <aop:aspectj-autoproxy />
    <context:component-scan base-package="com.spring.dynamicdatasource.test" />













