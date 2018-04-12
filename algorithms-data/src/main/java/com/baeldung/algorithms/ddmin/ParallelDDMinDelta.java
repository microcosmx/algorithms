package com.baeldung.algorithms.ddmin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;

public class ParallelDDMinDelta {

	public List<String> testcases = null;

	public List<String> clusters = null;

	public List<String> deltas_expected = null;
	public List<String> deltas_all = null;

	public List<String> deltas_dependencies = null;
	public List<List<String>> deltas_conflicts = null;

	public String expectError = "";
	public String expectPass = "";

	public boolean checkSeqDeltaConflicts(List<String> temp_deltas) {
		if (deltas_conflicts != null && deltas_conflicts.size() > 0) {
			List<String> seq_deltas = temp_deltas.stream().filter(p -> p.startsWith("seq"))
					.collect(Collectors.toList());

			// {seqB=[seqB_3_1_2, seqB_4_2_3, seqB_4_1_3], seqA=[seqA_4_1_2, seqA_4_2_3,
			// seqA_4_3_4, seqA_4_1_3, seqA_4_2_4, seqA_4_1_4]}
			Map<String, List<String>> seq_deltas_result = seq_deltas.stream()
					.collect(Collectors.groupingBy(seq_name -> seq_name.toString().split("_")[0], Collectors.toList()));

			if (seq_deltas_result.size() > 0 && deltas_conflicts.stream().anyMatch(x -> {
				for (String key : seq_deltas_result.keySet()) {
					List<String> seq_deltas_spec = seq_deltas_result.get(key);
					if (seq_deltas_spec.equals(x)) {
						return true;
					}
				}
				return false;
			})) {
				return true;
			}
		}
		return false;

	}

	public boolean applyDelta(List<String> deltas, String cluster) {
		return true;
	}

	public String processAndGetResult(List<String> deltas, List<String> testcases, String cluster) {
		return expectError;
	}

}
