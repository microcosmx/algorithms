package algorithms.ddmin;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;
import com.baeldung.algorithms.ddmin.DDMinDelta;

public class DDMinAlgorithmTest {

	@Test
	public void ddmin_search() {
		DDMinAlgorithm ddmin = new DDMinAlgorithm();
		DDMinDelta ddmin_delta = new DDMinDeltaExt();
		ddmin.setDdmin_delta(ddmin_delta);

		List<String> result = ddmin.ddmin(ddmin_delta.deltas_all);
		System.out.println(result);

		Assert.assertTrue(CollectionUtils.isEqualCollection(ddmin_delta.deltas_expected, result));
	}

}
