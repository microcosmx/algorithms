package com.baeldung.algorithms.ddmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class ParallelDDMinAlgorithm {
	
	
	private DDMinDelta ddmin_delta = null;
	public DDMinDelta getDdmin_delta() {
		return ddmin_delta;
	}
	public void setDdmin_delta(DDMinDelta ddmin_delta) {
		this.ddmin_delta = ddmin_delta;
	}
	
	private ExecutorService executor = Executors.newFixedThreadPool(12);
	private Set<List<String>> processed_deltas = new HashSet<List<String>>();
	
//	private List<String> resources = Arrays.asList("cluster1", "cluster2", "cluster3");
	public ParallelDDMinAlgorithm() {
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
		processed_deltas.clear();

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
			
			if(processed_deltas.contains(temp_deltas)) {
				continue;
			}
			CompletableFuture<List<Object>> future2 = CompletableFuture.supplyAsync(() -> {
				String result2 = testDelta(temp_deltas);
	            return Arrays.asList(result2, temp_deltas);
			}, executor);
			future2.thenAccept(result2 -> {
				System.out.println(result2);
				processed_deltas.add(temp_deltas);
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
		List<CompletableFuture<List<Object>>> futureList2 = new ArrayList<CompletableFuture<List<Object>>>();
		for(int i = 0; i < n; i++) {
			int start = low + i*block_size;
			int end = Math.min(low + (i+1)*block_size, high);
			List<String> temp_deltas = deltas.subList(start, end);
			List<String> result_deltas = (List<String>) CollectionUtils.subtract(deltas, temp_deltas);
			
			if(processed_deltas.contains(result_deltas)) {
				continue;
			}
			CompletableFuture<List<Object>> future2 = CompletableFuture.supplyAsync(() -> {
				String result2 = testDelta(result_deltas);
	            return Arrays.asList(result2, result_deltas);
			}, executor);
			future2.thenAccept(result2 -> {
				System.out.println(result2);
				processed_deltas.add(result_deltas);
			});
			futureList2.add(future2);
		}
		
		CompletableFuture<Void> allDoneFuture2 = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));	
		//wait until all done
		allDoneFuture2.join();
		
		for(int i = 0; i < futureList2.size(); i++) {
			CompletableFuture<List<Object>> future = futureList2.get(i);
			List<Object> result2 = future.get();
			if ("error".equals(result2.get(0))) {
				return ddmin_n((List<String>)result2.get(1), Math.max(n-1, 2));
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
