package com.baeldung.algorithms.ddmin;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;

public class ParallelDDMinDelta {

	public List<String> testcases = null;

	public List<String> clusters = null;

	public List<String> deltas_expected = null;
	public List<String> deltas_all = null;

	public String expectError = "";
	public String expectPass = "";

	public boolean applyDelta(List<String> deltas) {
		return true;
	}

	public String processAndGetResult(List<String> deltas, List<String> testcases) {
		return expectError;
	}

}
