package algorithms.ddmin;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;

public class DDMinAlgorithmTest {

	int[] sortedArray = { 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 9 };
	int key = 6;
	int expectedIndexForSearchKey = 7;
	int low = 0;
	int high = sortedArray.length - 1;
	List<Integer> sortedList = Arrays.asList(0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 9);

	@Test
	public void ddmin_search() {
		DDMinAlgorithm ddmin = new DDMinAlgorithm();
		Assert.assertEquals(expectedIndexForSearchKey,
				ddmin.runBinarySearchRecursively(sortedArray, key, low, high));
	}

}
