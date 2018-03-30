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
	
	private List<String> deltas_expected = null;
	public void setDeltas_expected(List<String> deltas_expected) {
		this.deltas_expected = deltas_expected;
	}

	public String expectError = "error1";
	public String expectPass = "pass1";

	public String testDelta(List<String> deltas) {
		// apply delta
		boolean result1 = applyDelta(deltas);
		if(!result1) {
			return "issue";
		}

		// run test case and get result
		String result2 = processAndGetResult(deltas, testcases);
		if(expectError.equals(result2)) {
			return "error";
		}else if(expectPass.equals(result2)){
			return "pass";
		}

		/*
		 * check result: 1. passed then return "pass" 2. exactly match the original
		 * failed return result, then return "error" 3. not match the failed return
		 * result, then "issue"
		 */
		return "issue";
	}

	
	public boolean applyDelta(List<String> deltas) {
		// TODO apply delta
		return true;
	}

	public String processAndGetResult(List<String> deltas, List<String> testcases) {
		// TODO execute testcases, hardcode "delta3", "delta6" here
		String returnResult = "";
		if(CollectionUtils.containsAll(deltas, deltas_expected)) {
			returnResult = expectError;
		}else if(CollectionUtils.containsAll(deltas, deltas_expected)){
			returnResult = expectPass;
		}else {
			return "xxxxxx";
		}
		
		return returnResult;
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
