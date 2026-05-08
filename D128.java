// 3629. Minimum Jumps to Reach End via Prime Teleportation
// You are given an integer array nums of length n.
// You start at index 0, and your goal is to reach index n - 1.
// From any index i, you may perform one of the following operations:
// Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
// Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that nums[j] % p == 0.
// Return the minimum number of jumps required to reach index n - 1.
// Example 1:
// Input: nums = [1,2,4,6]
// Output: 2
// Explanation:
// One optimal sequence of jumps is:
// Start at index i = 0. Take an adjacent step to index 1.
// At index i = 1, nums[1] = 2 is a prime number. Therefore, we teleport to index i = 3 as nums[3] = 6 is divisible by 2.
// Thus, the answer is 2.

class Solution {

    static final int MAXV = 1000001;

    static int[] spf = buildSPF();

    static int[] buildSPF() {

        int[] spf = new int[MAXV];

        for(int i = 0; i < MAXV; i++){
            spf[i] = i;
        }

        spf[0] = 0;
        spf[1] = 1;

        for(long i = 2; i * i < MAXV; i++){

            if(spf[(int)i] == i){

                for(long j = i * i; j < MAXV; j += i){

                    if(spf[(int)j] == j){
                        spf[(int)j] = (int)i;
                    }
                }
            }
        }

        return spf;
    }

    boolean isPrime(int x){

        return x >= 2 && spf[x] == x;
    }

    public int minJumps(int[] nums) {

        int n = nums.length;

        if(n == 1) return 0;

        int maxi = 0;

        for(int x : nums){
            maxi = Math.max(maxi, x);
        }

        // store which prime numbers are present
        boolean[] primeSeen = new boolean[maxi + 1];

        for(int x : nums){

            if(isPrime(x)){
                primeSeen[x] = true;
            }
        }

        // prime -> divisible indices
        HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();

        for(int i = 0; i < n; i++){

            int x = nums[i];

            if(x == 1) continue;

            while(x > 1){

                int p = spf[x];

                if(p <= maxi && primeSeen[p]){

                    mp.putIfAbsent(p, new ArrayList<>());
                    mp.get(p).add(i);
                }

                while(x % p == 0){
                    x /= p;
                }
            }
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        boolean[] usedPrime = new boolean[maxi + 1];

        Queue<Integer> q = new LinkedList<>();

        q.offer(0);
        dist[0] = 0;

        while(!q.isEmpty()){

            int idx = q.poll();

            if(idx == n - 1){
                return dist[idx];
            }

            // left
            if(idx - 1 >= 0 && dist[idx - 1] == -1){

                dist[idx - 1] = dist[idx] + 1;
                q.offer(idx - 1);
            }

            // right
            if(idx + 1 < n && dist[idx + 1] == -1){

                dist[idx + 1] = dist[idx] + 1;
                q.offer(idx + 1);
            }

            int x = nums[idx];

            // teleportation
            if(isPrime(x) && !usedPrime[x]){

                usedPrime[x] = true;

                if(mp.containsKey(x)){

                    for(int nextIdx : mp.get(x)){

                        if(dist[nextIdx] == -1){

                            dist[nextIdx] = dist[idx] + 1;
                            q.offer(nextIdx);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
