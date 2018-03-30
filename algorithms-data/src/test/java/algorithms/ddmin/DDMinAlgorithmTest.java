package algorithms.ddmin;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;

public class DDMinAlgorithmTest {

//	int[] sortedArray = { 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 9 };
//	int key = 6;
//	int expectedIndexForSearchKey = 7;
//	int low = 0;
//	int high = sortedArray.length - 1;
//	List<Integer> sortedList = Arrays.asList(0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 9);
//
//	@Test
//	public void binary_search() {
//		DDMinAlgorithm ddmin = new DDMinAlgorithm();
//		Assert.assertEquals(expectedIndexForSearchKey,
//				ddmin.runBinarySearchRecursively(sortedArray, key, low, high));
//	}
	
	
	
	
	private List<String> deltas_all = Arrays.asList("delta1", "delta2", "delta3",
			"delta4", "delta5", "delta6","delta7", "delta8", "delta9","delta10", "delta11", "delta12");
	private List<String> deltas_expected = Arrays.asList("delta3","delta6","delta12");
	
	
	@Test
	public void ddmin_search() {
		DDMinAlgorithm ddmin = new DDMinAlgorithm();
		ddmin.setDeltas_expected(deltas_expected);
		Assert.assertTrue(CollectionUtils.isEqualCollection(deltas_expected, ddmin.ddmin(deltas_all)));
	}

}
