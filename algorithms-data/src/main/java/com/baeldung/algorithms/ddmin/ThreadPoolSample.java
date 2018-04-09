package com.baeldung.algorithms.ddmin;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSample {
	private static int produceTaskSleepTime = 5;
	private static int consumeTaskSleepTime = 5000;
	private static int produceTaskMaxNumber = 20;

	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());

		for (int i = 1; i <= produceTaskMaxNumber; i++) {
			try {
				String work = "work@ " + i;
				System.out.println("put ：" + work);
				threadPool.execute(new ThreadPoolTask(work));
				Thread.sleep(produceTaskSleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static class ThreadPoolTask implements Runnable, Serializable {
		private static final long serialVersionUID = 0;
		private Object threadPoolTaskData;

		ThreadPoolTask(Object works) {
			this.threadPoolTaskData = works;
		}

		public void run() {
			System.out.println("start------" + threadPoolTaskData);
			try {
				Thread.sleep(consumeTaskSleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			threadPoolTaskData = null;
		}

		public Object getTask() {
			return this.threadPoolTaskData;
		}
	}
}