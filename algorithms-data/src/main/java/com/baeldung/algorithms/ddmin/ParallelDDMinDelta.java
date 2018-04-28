package com.baeldung.algorithms.ddmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;

public class ParallelDDMinDelta {

	public List<String> testcases = null;

	public List<String> clusters = null;

	public List<String> deltas_expected = null;

	// for sequence deltas:
	// "seqA_4_1_2", "seqA_4_2_3","seqA_4_3_4", "seqA_4_1_3", "seqA_4_2_4",
	// "seqA_4_1_4",
	// "seqB_3_1_2", "seqB_3_2_3","seqB_3_1_3"
	public List<String> deltas_all = null;

	public List<String> deltas_dependencies = null;
	public List<List<String>> deltas_conflicts = null;

	public String expectError = "";
	public String expectPass = "";

	@SuppressWarnings("serial")
	public Map<String, List<String>> default_seq_deltas = new HashMap<String, List<String>>() {
		{
			put("3", Arrays.asList("1_2", "2_3", "1_3"));
			put("4", Arrays.asList("1_2", "2_3", "3_4", "1_3", "2_4", "1_4"));
			put("5", Arrays.asList("1_2", "2_3", "3_4", "4_5", "1_3", "2_4", "3_5", "1_4", "2_5", "1_5"));
			put("6", Arrays.asList("1_2", "2_3", "3_4", "4_5", "5_6", "1_3", "2_4", "3_5", "4_6", 
					"1_4", "2_5", "3_6", "1_5", "2_6", "1_6"));
		}
	};

	public Map<String, String> getSeqDeltas(List<String> temp_deltas) {
		Map<String, String> result_seq_map = new HashMap<String, String>();

		List<String> seq_deltas = temp_deltas.stream().filter(p -> p.startsWith("seq")).collect(Collectors.toList());

		// {
		// seqB_3=[seqB_3_1_2, seqB_3_2_3, seqB_3_1_3],
		// seqA_4=[seqA_4_1_2, seqA_4_2_3, seqA_4_3_4, seqA_4_1_3, seqA_4_2_4,
		// seqA_4_1_4]
		// }
		Map<String, List<String>> seq_deltas_result = seq_deltas.stream()
				.collect(Collectors.groupingBy(
						seq_name -> seq_name.toString().split("_")[0] + "_" + seq_name.toString().split("_")[1],
						Collectors.toList()));
		for (String key : seq_deltas_result.keySet()) {
			String seq_name = key.split("_")[0];
			String seq_size = key.split("_")[1];
			List<String> default_seq = default_seq_deltas.get(seq_size);
			List<String> delta_seq = seq_deltas_result.get(key);
			delta_seq = delta_seq.stream().map(x -> {
				return x.split("_")[2] + "_" + x.split("_")[3];
			}).collect(Collectors.toList());
			String final_seq = getFinalSeq(Integer.valueOf(seq_size), default_seq, delta_seq);
			result_seq_map.put(seq_name, final_seq);
		}

		return result_seq_map;
	}

	// input: [1_2, 2_3, 3_4, 4_5, 5_6, 1_3, 2_4, 3_5, 4_6, 1_4, 2_5, 3_6, 1_5, 2_6, 1_6]
	// input: Arrays.asList("2_3", "2_4", "5_6")
	// output: [1_2, 3_2, 3_4, 4_5, 6_5, 1_3, 4_2, 3_5, 4_6, 1_4, 2_5, 3_6, 1_5, 2_6, 1_6]
	// output: 1_3_4_2_6_5
	public String getFinalSeq(int seq_size, List<String> default_seq, List<String> delta_seq) {
		List<String> seq = default_seq.stream().map(x -> {
			if (delta_seq.contains(x)) {
				String[] seq_pair = x.split("_");
				return seq_pair[1] + "_" + seq_pair[0];
			}
			return x;
		}).collect(Collectors.toList());

		List<String> seq_string_list = Arrays.asList();
		for (int i = 0; i < seq.size(); i++) {
			seq_string_list = mergeSeq(seq_size, seq.get(i), seq);
			
			if (seq_string_list.stream().anyMatch(x -> checkCircle(x))) {
				return "error_circuit";
			}
			// System.out.println(seq_string);
			for (String seq_string : seq_string_list) {
				if (seq_string.split("_").length == seq_size) {
					return seq_string;
				}
			}
		}

		return "error_circuit";
	}

	public boolean checkCircle(String seq_string) {
		String[] seq_pair = seq_string.split("_");
		Map<String, Long> result = Stream.of(seq_pair)
				.collect(Collectors.groupingBy(x -> x.toString(), Collectors.counting()));
		// System.out.println(result);
		return result.values().stream().anyMatch(x -> x > 1);
	}

	public List<String> mergeSeq(int seq_size, String base_seq_string, List<String> seq) {
		List<String> mergeResult = new ArrayList<String>();//Arrays.asList(base_seq_string); 
		mergeResult.add(base_seq_string);
		if (base_seq_string.split("_").length >= seq_size) {
			return mergeResult;
		}
		String[] base_seq_array = base_seq_string.split("_");
		for (String s : seq) {
			String[] seq_array = s.split("_");
			if (base_seq_array[0].equals(seq_array[1])) {
				mergeResult.addAll(mergeSeq(seq_size, seq_array[0] + "_" + base_seq_string, seq));
			}
			if (base_seq_array[base_seq_array.length - 1].equals(seq_array[0])) {
				mergeResult.addAll(mergeSeq(seq_size, base_seq_string + "_" + seq_array[1], seq));
			}
		}
		return mergeResult;
	}

	public boolean checkSeqDeltaConflicts(List<String> temp_deltas) {
		if (deltas_conflicts != null && deltas_conflicts.size() > 0) {
			List<String> seq_deltas = temp_deltas.stream().filter(p -> p.startsWith("seq"))
					.collect(Collectors.toList());

			// {
			// seqB=[seqB_3_1_2, seqB_3_2_3, seqB_3_1_3],
			// seqA=[seqA_4_1_2, seqA_4_2_3, seqA_4_3_4, seqA_4_1_3, seqA_4_2_4, seqA_4_1_4]
			// }
			Map<String, List<String>> seq_deltas_result = seq_deltas.stream()
					.collect(Collectors.groupingBy(seq_name -> seq_name.toString().split("_")[0], Collectors.toList()));

			if (seq_deltas_result.size() > 0 && deltas_conflicts.stream().anyMatch(x -> {
				for (String key : seq_deltas_result.keySet()) {
					List<String> seq_deltas_spec = seq_deltas_result.get(key);
					if (CollectionUtils.isEqualCollection(seq_deltas_spec, x)) {
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
