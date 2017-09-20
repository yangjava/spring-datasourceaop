package com.spring.datasourceaop;

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

