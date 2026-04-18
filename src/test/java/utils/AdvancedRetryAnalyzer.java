package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class AdvancedRetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetry = 2; // retry 2 times

    @Override
    public boolean retry(ITestResult result) {

        // Get exception
        Throwable cause = result.getThrowable();

        // Retry only for flaky issues
        if (cause != null) {
            String message = cause.toString();

            if (message.contains("Timeout") ||
                message.contains("StaleElement") ||
                message.contains("NoSuchElement")) {

                if (retryCount < maxRetry) {
                    retryCount++;
                    System.out.println("Retrying test: " 
                        + result.getName() + " | Attempt: " + retryCount);
                    return true;
                }
            }
        }

        return false;
    }
}