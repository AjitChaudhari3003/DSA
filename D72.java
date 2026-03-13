// 3296. Minimum Number of Seconds to Make Mountain Height Zero
// You are given an integer mountainHeight denoting the height of a mountain.
// You are also given an integer array workerTimes representing the work time of workers in seconds.
// The workers work simultaneously to reduce the height of the mountain. For worker i:
// To decrease the mountain's height by x, it takes workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x seconds. For example:
// To reduce the height of the mountain by 1, it takes workerTimes[i] seconds.
// To reduce the height of the mountain by 2, it takes workerTimes[i] + workerTimes[i] * 2 seconds, and so on.
// Return an integer representing the minimum number of seconds required for the workers to make the height of the mountain 0.
// Example 1:
// Input: mountainHeight = 4, workerTimes = [2,1,1]
// Output: 3
// Explanation:
// One way the height of the mountain can be reduced to 0 is:
// Worker 0 reduces the height by 1, taking workerTimes[0] = 2 seconds.
// Worker 1 reduces the height by 2, taking workerTimes[1] + workerTimes[1] * 2 = 3 seconds.
// Worker 2 reduces the height by 1, taking workerTimes[2] = 1 second.
// Since they work simultaneously, the minimum time needed is max(2, 3, 1) = 3 seconds.

import java.util.Arrays;

class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        Arrays.sort(workerTimes);
        
        long lowTime = 0;
        long highTime = (long) 1e16; 
        long minRequiredTime = highTime;
        
        // Outer Binary Search: Finding the minimum required time
        while (lowTime <= highTime) {
            long midTime = lowTime + (highTime - lowTime) / 2;
            long totalHeightReduced = 0;
            
            for (int baseTime : workerTimes) {
                // Inner Binary Search: Finding max height THIS worker can reduce in 'midTime'
                long lowHeight = 0;
                long highHeight = mountainHeight;
                long maxWorkerHeight = 0;
                
                while (lowHeight <= highHeight) {
                    long midHeight = lowHeight + (highHeight - lowHeight) / 2;
                    
                    // Crucial: Cast to long BEFORE multiplying to prevent 32-bit overflow
                    long timeNeeded = (long) baseTime * midHeight * (midHeight + 1) / 2;
                    
                    if (timeNeeded <= midTime) {
                        maxWorkerHeight = midHeight;
                        lowHeight = midHeight + 1;
                    } else {
                        highHeight = midHeight - 1;
                    }
                }
                
                totalHeightReduced += maxWorkerHeight;
                
                // Early Exit Optimization
                if (totalHeightReduced >= mountainHeight) {
                    break;
                }
            }
            
            // Adjust outer binary search bounds
            if (totalHeightReduced >= mountainHeight) {
                minRequiredTime = midTime;
                highTime = midTime - 1;
            } else {
                lowTime = midTime + 1;
            }
        }
        
        return minRequiredTime;
    }
}
