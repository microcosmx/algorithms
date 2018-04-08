package algorithms.ddmin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;
import com.baeldung.algorithms.ddmin.DDMinAlgorithmParallel;
import com.baeldung.algorithms.ddmin.DDMinDelta;

public class DDMinAlgorithmParallelTest {
	
	@Test
	public void ddmin_search() throws InterruptedException, ExecutionException {
		DDMinAlgorithmParallel ddmin = new DDMinAlgorithmParallel();
		DDMinDelta ddmin_delta = new DDMinDeltaExt();
		ddmin.setDdmin_delta(ddmin_delta);
		
		List<String> result = ddmin.ddmin(ddmin_delta.deltas_all);
		System.out.println(result);
		
		Assert.assertTrue(CollectionUtils.isEqualCollection(ddmin_delta.deltas_expected, result));
	}

}

