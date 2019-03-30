package algorithms.ddmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.jenetics.internal.math.random;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;
import com.baeldung.algorithms.ddmin.DDMinDelta;
import com.baeldung.algorithms.ddmin.ParallelDDMinDelta;

public class ParallelDDMinDeltaExt extends ParallelDDMinDelta {

	public ParallelDDMinDeltaExt() {
		super();

		testcases = Arrays.asList("test1", "test2");

		clusters = Arrays.asList("cluster1", "cluster2", "cluster3", "cluster4", "cluster5", "cluster6");
		// clusters = Arrays.asList("cluster1", "cluster2", "cluster3", "cluster4");

		deltas_all = Arrays.asList("delta1", "delta2", "delta3", "delta4", "delta5", "delta6", "delta7", "delta8",
				"delta9", "delta10", "delta11", "delta12", "seqA_6_1_2", "seqA_6_2_3", "seqA_6_3_4", "seqA_6_4_5",
				"seqA_6_5_6", "seqA_6_1_3", "seqA_6_2_4", "seqA_6_3_5", "seqA_6_4_6", "seqA_6_1_4", "seqA_6_2_5",
				"seqA_6_3_6", "seqA_6_1_5", "seqA_6_2_6", "seqA_6_1_6", "seqB_3_1_2", "seqB_3_2_3", "seqB_3_1_3");
		deltas_expected = Arrays.asList("delta3", "delta6", "delta12", "seqA_6_2_3", "seqA_6_2_4", "seqA_6_5_6",
				"seqB_3_1_2"); // seqA error: 134265, seqB error: 213

		// deltas_all = Arrays.asList("1", "2", "3");
		// deltas_expected = Arrays.asList("2");

		deltas_all = new ArrayList<String>();
		for (int i = 1; i <= 41; i++) {
			deltas_all.add(String.valueOf(i));
		}
		deltas_expected = Arrays.asList("1");
		deltas_expected = Arrays.asList("30", "60", "91", "118");

		// deltas_all = Arrays.asList("30", "31", "60", "61", "90", "91");
		// deltas_expected = Arrays.asList("30", "60", "91");

		deltas_all = new ArrayList<String>();
		for (int i = 1; i <= 11; i++) {
			deltas_all.add("instance_" + i);
		}
		deltas_all.addAll(Arrays.asList("seqA_5_1_2", "seqA_5_1_3", "seqA_5_1_4", "seqA_5_1_5", "seqA_5_2_3",
				"seqA_5_2_4", "seqA_5_2_5", "seqA_5_3_4", "seqA_5_3_5", "seqA_5_4_5"));
		for (int i = 1; i <= 22; i++) {
			deltas_all.add("config_" + i);
		}
		deltas_expected = Arrays.asList("instance_6", "seqA_5_1_2", "seqA_5_1_3", "seqA_5_1_4");

		deltas_dependencies = null;
		deltas_conflicts = Arrays.asList(Arrays.asList("seqA_6_1_3"), Arrays.asList("seqA_6_2_4"),
				Arrays.asList("seqA_6_1_4"), Arrays.asList("seqA_6_1_5"), Arrays.asList("seqA_6_1_6"),
				Arrays.asList("seqA_6_1_2", "seqA_6_2_3"), Arrays.asList("seqA_6_1_2", "seqA_6_2_4"),
				Arrays.asList("seqA_6_1_3", "seqA_6_3_4"), Arrays.asList("seqA_6_1_2", "seqA_6_2_3", "seqA_6_3_4"),
				Arrays.asList("seqB_3_1_3"), Arrays.asList("seqB_3_1_2", "seqB_3_2_3"));

		expectError = "error1";
		expectPass = "pass1";
	}

	@Override
	public boolean applyDelta(List<String> deltas, String cluster) {
		Map<String, String> seq_deltas = getSeqDeltas(deltas);
		// System.out.println(seq_deltas);
		// TODO 1. recovery to original cluster status
		try {
			Thread.sleep((long) (600 * Math.random()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO 2. apply delta

		return true;
	}

	@Override
	public String processAndGetResult(List<String> deltas, List<String> testcases, String cluster) {
		// TODO execute testcases, hardcode "delta3", "delta6", "delta12" here
		String returnResult = "";
		if (CollectionUtils.containsAll(deltas, deltas_expected)) {
			returnResult = expectError;
		} else if (CollectionUtils.containsAll(deltas, deltas_expected)) {
			returnResult = expectPass;
		} else {
			return "xxxxxx";
		}

		return returnResult;
	}

}
