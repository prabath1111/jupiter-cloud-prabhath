package com.jupiter.utils;

public class ThreadRunner {

    public static ReportUtils reportUtils = new ReportUtils();
    
	public static ConfigHandler configHandler = new ConfigHandler("config.properties");
	
	public static ThreadLocal<ExecutorUnit> tl = new ThreadLocal<ExecutorUnit>();
	
	public static void setExecutorUnit(ExecutorUnit executorUnit) {
		
		tl.set(executorUnit);
	}
	
	public static void unsetExecutorUnit() {
		
		tl.remove();
	}
	
	public static ExecutorUnit getExecutorUnit() {
		
		return tl.get();
	}
}
