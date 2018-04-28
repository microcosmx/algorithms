package com.baeldung.algorithms.ddmin;

public interface MonitorHandler {  
    
    boolean usable();   
      
    void before(Thread thread, Runnable runnable);    
      
    void after(Runnable runnable, Throwable throwable);  
      
    void terminated(int largestPoolSize, long completedTaskCount);  
}  
