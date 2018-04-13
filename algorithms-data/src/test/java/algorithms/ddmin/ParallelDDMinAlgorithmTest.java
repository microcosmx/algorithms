package algorithms.ddmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;
import com.baeldung.algorithms.ddmin.ParallelDDMinAlgorithm;
import com.baeldung.algorithms.ddmin.ParallelDDMinDelta;
import com.baeldung.algorithms.ddmin.DDMinDelta;

public class ParallelDDMinAlgorithmTest {

	@Test
	public void ddmin_search() throws InterruptedException, ExecutionException {
		ParallelDDMinAlgorithm ddmin = new ParallelDDMinAlgorithm();
		ParallelDDMinDelta ddmin_delta = new ParallelDDMinDeltaExt();
		ddmin.setDdmin_delta(ddmin_delta);
		ddmin.initEnv();

		List<String> result = ddmin.ddmin(ddmin_delta.deltas_all);
		System.out.println("-----------final result---------");
		System.out.println(result);

		Assert.assertTrue(CollectionUtils.isEqualCollection(ddmin_delta.deltas_expected, result));

	}
	
	
	@Test
	public void get_seq_deltas() throws InterruptedException, ExecutionException {
		ParallelDDMinDelta ddmin_delta = new ParallelDDMinDeltaExt();
		List<String> right = Arrays.asList("delta3", "delta6", "delta12", "seqA_4_1_2", "seqA_4_1_3", "seqB_3_1_2");
		List<String> error = Arrays.asList("delta3", "delta6", "delta12", "seqA_4_1_3", "seqB_3_1_2");
		List<String> right_6 = Arrays.asList("delta3", "delta6", "delta12", "seqA_6_2_3", "seqA_6_2_4", "seqA_6_5_6");
		Map<String, String> result = ddmin_delta.getSeqDeltas(right_6);

		System.out.println("-------------final seq----------------");
		System.out.println(result);
		
		Assert.assertTrue("1_3_4_2_6_5".equals(result.get("seqA")));
	}

}
