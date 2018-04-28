package guava;

public class Sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
		List<Integer> countDown = Lists.reverse(theList); // {5, 4, 3, 2, 1}

		List<List<Integer>> parts = Lists.partition(countUp, 2); // {{1, 2}, {3, 4}, {5}}

	}

}
