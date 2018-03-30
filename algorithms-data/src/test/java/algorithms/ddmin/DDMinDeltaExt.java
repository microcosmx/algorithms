package algorithms.ddmin;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.algorithms.ddmin.DDMinAlgorithm;
import com.baeldung.algorithms.ddmin.DDMinDelta;

public class DDMinDeltaExt extends DDMinDelta {
	
	public DDMinDeltaExt() {
		super();
		
		testcases = Arrays.asList("test1", "test2");
		
		deltas_all = Arrays.asList("delta1", "delta2", "delta3",
				"delta4", "delta5", "delta6","delta7", "delta8", "delta9","delta10", "delta11", "delta12");
		deltas_expected = Arrays.asList("delta3","delta6","delta12");

		expectError = "error1";
		expectPass = "pass1";
	}

	public boolean applyDelta(List<String> deltas) {
		// TODO apply delta
		return true;
	}

	public String processAndGetResult(List<String> deltas, List<String> testcases) {
		// TODO execute testcases, hardcode "delta3", "delta6" here
		String returnResult = "";
		if(CollectionUtils.containsAll(deltas, deltas_expected)) {
			returnResult = expectError;
		}else if(CollectionUtils.containsAll(deltas, deltas_expected)){
			returnResult = expectPass;
		}else {
			return "xxxxxx";
		}
		
		return returnResult;
	}

}
