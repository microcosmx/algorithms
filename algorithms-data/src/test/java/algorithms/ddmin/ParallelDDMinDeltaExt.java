package algorithms.ddmin;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;
import com.baeldung.algorithms.ddmin.DDMinDelta;
import com.baeldung.algorithms.ddmin.ParallelDDMinDelta;

public class ParallelDDMinDeltaExt extends ParallelDDMinDelta {

	public ParallelDDMinDeltaExt() {
		super();

		testcases = Arrays.asList("test1", "test2");

		clusters = Arrays.asList("cluster1", "cluster2", "cluster3");

		deltas_all = Arrays.asList("delta1", "delta2", "delta3", "delta4", "delta5", "delta6", 
				"delta7", "delta8","delta9", "delta10", "delta11", "delta12",
				"seqA_4_1_2", "seqA_4_2_3","seqA_4_3_4", "seqA_4_1_3", "seqA_4_2_4", "seqA_4_1_4",
				"seqB_3_1_2", "seqB_4_2_3","seqB_4_1_3");
		deltas_expected = Arrays.asList("delta3", "delta6", "delta12", "seqA_4_1_2", "seqA_4_1_3", "seqB_3_1_2"); //seqA error: 2314, seqB error: 213

		// deltas_all = Arrays.asList("delta1", "delta2", "delta3");
		// deltas_expected = Arrays.asList("delta2");
		
		deltas_dependencies = null;
		deltas_conflicts = Arrays.asList( 
				Arrays.asList("seqA_4_1_3"), 
				Arrays.asList("seqA_4_2_4"),
				Arrays.asList("seqA_4_1_4"),
				Arrays.asList("seqA_4_1_2", "seqA_4_2_3"),
				Arrays.asList("seqA_4_1_2", "seqA_4_2_4"),
				Arrays.asList("seqA_4_1_3", "seqA_4_3_4"),
				Arrays.asList("seqA_4_1_2", "seqA_4_2_3", "seqA_4_3_4")
			);

		expectError = "error1";
		expectPass = "pass1";
	}

	public boolean applyDelta(List<String> deltas) {
		// TODO 1. recovery to original cluster status
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO 2. apply delta

		return true;
	}

	public String processAndGetResult(List<String> deltas, List<String> testcases) {
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
