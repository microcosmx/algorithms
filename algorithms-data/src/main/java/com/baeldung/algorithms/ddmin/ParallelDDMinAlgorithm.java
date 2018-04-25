package com.baeldung.algorithms.ddmin;

import static org.hamcrest.CoreMatchers.startsWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class ParallelDDMinAlgorithm {

	private ParallelDDMinDelta ddmin_delta = null;

	public ParallelDDMinDelta getDdmin_delta() {
		return ddmin_delta;
	}

	public void setDdmin_delta(ParallelDDMinDelta ddmin_delta) {
		this.ddmin_delta = ddmin_delta;
	}

	private ExecutorService executor = Executors.newFixedThreadPool(18);
	private volatile Set<List<String>> processed_deltas = new HashSet<List<String>>();

	BlockingQueue<String> cluster_queue = null;

	// private List<String> resources = Arrays.asList("cluster1", "cluster2",
	// "cluster3");
	public ParallelDDMinAlgorithm() {
		super();

	}

	public void initEnv() {
		cluster_queue = new ArrayBlockingQueue<String>(ddmin_delta.clusters.size());
		ddmin_delta.clusters.stream().forEach((x -> {
			try {
				cluster_queue.put(x);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));
	}

	public String testDelta(List<String> deltas, String cluster) {
		// apply delta
		boolean result1 = ddmin_delta.applyDelta(deltas, cluster);
		if (!result1) {
			return "issue";
		}

		// run test case and get result
		String result2 = ddmin_delta.processAndGetResult(deltas, ddmin_delta.testcases, cluster);
		if (ddmin_delta.expectError.equals(result2)) {
			return "error";
		} else if (ddmin_delta.expectPass.equals(result2)) {
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

		// String cluster = cluster_queue.take();
		// String result = testDelta(deltas, cluster);
		// cluster_queue.put(cluster);
		//
		// if ("error".equals(result)) {
		// return ddmin_n(deltas, 2);
		// }
		//
		// return null;

		return ddmin_n(deltas, 4);
	}

	public List<String> ddmin_n(List<String> deltas, int n) throws InterruptedException, ExecutionException {

		// System.out.println(cluster_queue);

		int low = 0;
		int high = deltas.size();
		
		int slices = 4;
		if(high < 8) {
			slices = 2;
		}
		
		if(high < 4) {
			n=2;
		}
		// make sure the most fine-grained granularity
		if (n > high) {
			return deltas;
		}

		int block_size = high / n;
		if (block_size <= 1) {
			n = high;
		}

		// subset
		List<CompletableFuture<List<Object>>> futureList = new ArrayList<CompletableFuture<List<Object>>>();
		for (int i = 0; i < n; i++) {
			int start = low + i * block_size;
			int end = Math.min(low + (i + 1) * block_size, high);
			List<String> temp_deltas = deltas.subList(start, end);

			if (processed_deltas.contains(temp_deltas)) {
				continue;
			}
			// error sequence error_circuit
			if (ddmin_delta.checkSeqDeltaConflicts(temp_deltas) || ddmin_delta.getSeqDeltas(temp_deltas).values()
					.stream().anyMatch(x -> "error_circuit".equals(x))) {
				continue;
			}
			CompletableFuture<List<Object>> future2 = CompletableFuture.supplyAsync(() -> {
				try {
					String cluster = cluster_queue.take();
					String result2 = testDelta(temp_deltas, cluster);
					cluster_queue.put(cluster);
					// System.out.println(cluster_queue);
					return Arrays.asList(result2, temp_deltas, cluster);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}, executor);
			future2.thenAccept(result2 -> {
				System.out.println(result2);
				processed_deltas.add(temp_deltas);
			});
			futureList.add(future2);
		}
		
		List<CompletableFuture<List<Object>>> futureList3 = null;
		if (n == slices || slices == 2) {
			futureList3 = new ArrayList<CompletableFuture<List<Object>>>();

			for (int i = 0; i < 2; i++) {
				int start = i == 0 ? 0 : high / 2;
				int end = i == 0 ? high / 2 : high;
				List<String> temp_deltas = deltas.subList(start, end);

				if (processed_deltas.contains(temp_deltas)) {
					continue;
				}
				// error sequence error_circuit
				if (ddmin_delta.checkSeqDeltaConflicts(temp_deltas) || ddmin_delta.getSeqDeltas(temp_deltas).values()
						.stream().anyMatch(x -> "error_circuit".equals(x))) {
					continue;
				}
				CompletableFuture<List<Object>> future3 = CompletableFuture.supplyAsync(() -> {
					try {
						String cluster = cluster_queue.take();
						String result2 = testDelta(temp_deltas, cluster);
						cluster_queue.put(cluster);
						// System.out.println(cluster_queue);
						return Arrays.asList(result2, temp_deltas, cluster);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				}, executor);
				future3.thenAccept(result2 -> {
					System.out.println(result2);
					processed_deltas.add(temp_deltas);
				});
				futureList3.add(future3);
			}

		}

		// complement
		List<CompletableFuture<List<Object>>> futureList2 = new ArrayList<CompletableFuture<List<Object>>>();
		for (int i = 0; i < n; i++) {
			int start = low + i * block_size;
			int end = Math.min(low + (i + 1) * block_size, high);
			List<String> temp_deltas = deltas.subList(start, end);
			List<String> result_deltas = (List<String>) CollectionUtils.subtract(deltas, temp_deltas);

			if (processed_deltas.contains(result_deltas)) {
				continue;
			}
			if (ddmin_delta.checkSeqDeltaConflicts(result_deltas) || ddmin_delta.getSeqDeltas(result_deltas).values()
					.stream().anyMatch(x -> "error_circuit".equals(x))) {
				continue;
			}
			CompletableFuture<List<Object>> future2 = CompletableFuture.supplyAsync(() -> {
				try {
					String cluster = cluster_queue.take();
					String result2 = testDelta(result_deltas, cluster);
					cluster_queue.put(cluster);
					return Arrays.asList(result2, result_deltas, cluster);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}, executor);
			future2.thenAccept(result2 -> {
				System.out.println(result2);
				processed_deltas.add(result_deltas);
			});
			futureList2.add(future2);
		}

		CompletableFuture<Void> allDoneFuture = CompletableFuture
				.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
		allDoneFuture.join();
		
		if(futureList3!=null) {
			CompletableFuture<Void> allDoneFuture3 = CompletableFuture
					.allOf(futureList3.toArray(new CompletableFuture[futureList3.size()]));
			allDoneFuture.join();
		}
		
		CompletableFuture<Void> allDoneFuture2 = CompletableFuture
				.allOf(futureList2.toArray(new CompletableFuture[futureList2.size()]));
		allDoneFuture2.join();

		
		
		for (int i = 0; i < futureList.size(); i++) {
			CompletableFuture<List<Object>> future = futureList.get(i);
			List<Object> result = future.get();
			if (result != null && "error".equals(result.get(0))) {
				return ddmin_n((List<String>) result.get(1), slices);
			}
		}
		
		if(futureList3!=null) {
			for (int i = 0; i < futureList3.size(); i++) {
				CompletableFuture<List<Object>> future = futureList3.get(i);
				List<Object> result = future.get();
				if (result != null && "error".equals(result.get(0))) {
					return ddmin_n((List<String>) result.get(1), slices);
				}
			}
		}

		for (int i = 0; i < futureList2.size(); i++) {
			CompletableFuture<List<Object>> future = futureList2.get(i);
			List<Object> result = future.get();
			if (result != null && "error".equals(result.get(0))) {
				return ddmin_n((List<String>) result.get(1), Math.max(n - 1, 2));
			}
		}

		// granularity
		if (n < deltas.size()) {
			return ddmin_n(deltas, Math.min(deltas.size(), 2 * n));
		}

		// find the delta
		return deltas;

	}

}
