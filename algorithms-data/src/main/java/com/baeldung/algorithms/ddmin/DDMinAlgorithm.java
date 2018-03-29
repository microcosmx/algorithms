package com.baeldung.algorithms.ddmin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class DDMinAlgorithm {

	public int runBinarySearchRecursively(int[] sortedArray, int key, int low, int high) {

		int middle = (low + high) / 2;
		if (high < low) {
			return -1;
		}

		if (key == sortedArray[middle]) {
			return middle;
		} else if (key < sortedArray[middle]) {
			return runBinarySearchRecursively(sortedArray, key, low, middle - 1);
		} else {
			return runBinarySearchRecursively(sortedArray, key, middle + 1, high);
		}
	}

	
	
	public List<String> testcases = Arrays.asList("test1", "test2");

	public String testDelta(List<String> deltas) {
		// apply delta
		boolean result1 = applyDelta(deltas);

		// run test case and get result
		boolean result2 = processAndGetResult();

		/*
		 * check result: 1. passed then return "pass" 2. exactly match the original
		 * failed return result, then return "error" 3. not match the failed return
		 * result, then "issue"
		 */
		return "issue";
	}

	public boolean applyDelta(List<String> deltas) {
		return true;
	}

	public boolean processAndGetResult() {
		// execute testcases

		return true;
	}

	public List<String> ddmin(List<String> deltas) {

		String result = testDelta(deltas);

		if ("pass".equals(result)) {
			return null;
		} else if ("error".equals(result)) {
			ddmin_n(deltas, 2);
		} else if ("issue".equals(result)) {
			return null;
		}
		
		return null;
	}

	public List<String> ddmin_n(List<String> deltas, int n) {

		String result = null;
		
		int low = 0;
		int high = deltas.size() - 1;
		
		int block_size = high / n;
		
		//subset
		for(int i = 0; i < n; i++) {
			int start = low + i*block_size;
			int end = Math.min(low + (i+1)*block_size, high);
			List<String> temp_deltas = deltas.subList(start, end);
			result = testDelta(temp_deltas);
			if ("error".equals(result)) {
				return ddmin_n(temp_deltas, 2);
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
				return ddmin_n(temp_deltas, Math.max(n-1, 2));
			}
		}
		
		//granularity
		if(n < deltas.size()) {
			return ddmin_n(deltas, Math.max(deltas.size(), 2*n));
		}
		
		//find the delta
		return deltas;

	}

}
