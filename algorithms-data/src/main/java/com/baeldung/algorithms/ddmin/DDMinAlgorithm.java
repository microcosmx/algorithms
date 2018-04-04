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
	
	
	private DDMinDelta ddmin_delta = null;
	public DDMinDelta getDdmin_delta() {
		return ddmin_delta;
	}
	public void setDdmin_delta(DDMinDelta ddmin_delta) {
		this.ddmin_delta = ddmin_delta;
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


	public List<String> ddmin(List<String> deltas) {

		String result = testDelta(deltas);
		
		if ("error".equals(result)) {
			return ddmin_n(deltas, 2);
		}
		
		return null;
	}

	public List<String> ddmin_n(List<String> deltas, int n) {

		String result = null;
		
		int low = 0;
		int high = deltas.size();
		if(n > high) {
			return deltas;
		}
		
		int block_size = high / n;
		if(block_size <= 1) {
			n = high;
		}
		
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
