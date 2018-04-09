package com.baeldung.algorithms.ddmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class DDMinAlgorithmParallel {
	
	
	private DDMinDelta ddmin_delta = null;
	public DDMinDelta getDdmin_delta() {
		return ddmin_delta;
	}
	public void setDdmin_delta(DDMinDelta ddmin_delta) {
		this.ddmin_delta = ddmin_delta;
	}
	
	private ExecutorService executor = Executors.newFixedThreadPool(12);
	
//	private List<String> resources = Arrays.asList("cluster1", "cluster2", "cluster3");
	public DDMinAlgorithmParallel() {
		super();
	}
	
	
	public String testDelta(List<String> deltas) {
		// apply delta
		boolean result1 = ddmin_delta.applyDelta(deltas);
		if(!result1) {
			return "issue";
		}

		// run test case and get result
		String result2 = ddmin_delta.processAndGetResult(deltas, ddmin_delta.testcases);
		if(ddmin_delta.expectError.equals(result2)) {
			return "error";
		}else if(ddmin_delta.expectPass.equals(result2)){
			return "pass";
		}

		/*
		 * check result: 1. passed then return "pass" 2. exactly match the original
		 * failed return result, then return "error" 3. not match the failed return
		 * result, then "issue"
		 */
		return "issue";
	}


	public List<String> ddmin(List<String> deltas) throws InterruptedException, ExecutionException {

		String result = testDelta(deltas);
		
		if ("error".equals(result)) {
			return ddmin_n(deltas, 2);
		}
		
		return null;
	}

	public List<String> ddmin_n(List<String> deltas, int n) throws InterruptedException, ExecutionException {

		String result = null;
		
		int low = 0;
		int high = deltas.size();
		//make sure the most fine-grained granularity
		if(n > high) {
			return deltas;
		}
		
		int block_size = high / n;
		if(block_size <= 1) {
			n = high;
		}
		
		//subset
		List<CompletableFuture<List<Object>>> futureList = new ArrayList<CompletableFuture<List<Object>>>();
		for(int i = 0; i < n; i++) {
			int start = low + i*block_size;
			int end = Math.min(low + (i+1)*block_size, high);
			List<String> temp_deltas = deltas.subList(start, end);
			
//			Future<String> future = executor.submit(() -> {
//				System.out.println(temp_deltas);
//				String result1 = testDelta(temp_deltas);
//	            return result1;
//	        });
//			result = future2.get();
//			if ("error".equals(result)) {
//				return ddmin_n(temp_deltas, 2);
//			}
			
			CompletableFuture<List<Object>> future2 = CompletableFuture.supplyAsync(() -> {
				System.out.println(temp_deltas);
				String result2 = testDelta(temp_deltas);
	            return Arrays.asList(result2, temp_deltas);
			}, executor);
			future2.thenAccept(result2 -> {
				System.out.println("-------------complete------------" + result2);
			});
			futureList.add(future2);
		}
		
		CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));	
		//wait until all done
		allDoneFuture.join();
		
		for(int i = 0; i < futureList.size(); i++) {
			CompletableFuture<List<Object>> future = futureList.get(i);
			List<Object> result2 = future.get();
			if ("error".equals(result2.get(0))) {
				return ddmin_n((List<String>)result2.get(1), 2);
			}
		}
		
		
		//complement
		for(int i = 0; i < n; i++) {
			int start = low + i*block_size;
			int end = Math.min(low + (i+1)*block_size, high);
			List<String> temp_deltas = deltas.subList(start, end);
			List<String> result_deltas = (List<String>) CollectionUtils.subtract(deltas, temp_deltas);
			result = testDelta(result_deltas);
			if ("error".equals(result)) {
				return ddmin_n(result_deltas, Math.max(n-1, 2));
			}
		}
		
		//granularity
		if(n < deltas.size()) {
			return ddmin_n(deltas, Math.min(deltas.size(), 2*n));
		}
		
		//find the delta
		return deltas;

	}

}
