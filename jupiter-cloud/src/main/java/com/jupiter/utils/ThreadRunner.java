package com.jupiter.utils;

/**
 * 
 * ThreadRunner class enables parallel execution of this framework
 * 
 * 
 * @author Prabath
 *
 */
public class ThreadRunner {

    public static ReportUtils reportUtils = new ReportUtils();
    
	public static ConfigHandler configHandler = new ConfigHandler("config.properties");
	
	/** Parallel Thread */
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
