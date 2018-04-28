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
import com.baeldung.algorithms.ddmin.ParallelDDMinAlgorithm2;
import com.baeldung.algorithms.ddmin.ParallelDDMinDelta;
import com.baeldung.algorithms.ddmin.DDMinDelta;

public class ParallelDDMinAlgorithmTest2 {

	@Test
	public void ddmin_search() throws InterruptedException, ExecutionException {
		ParallelDDMinAlgorithm2 ddmin = new ParallelDDMinAlgorithm2();
		ParallelDDMinDelta ddmin_delta = new ParallelDDMinDeltaExt();
		ddmin.setDdmin_delta(ddmin_delta);
		ddmin.initEnv();

		List<String> result = ddmin.ddmin(ddmin_delta.deltas_all);
		System.out.println("-----------final result---------");
		System.out.println("=========" + result);

		Assert.assertTrue(CollectionUtils.isEqualCollection(ddmin_delta.deltas_expected, result));

	}

}
