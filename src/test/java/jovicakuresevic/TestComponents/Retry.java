package jovicakuresevic.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int TryMax = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		

		if (count <TryMax) {
			count ++;
			return true;
		}
		
		return false;
	}

}
