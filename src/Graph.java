import java.util.*;

public class Graph {
    /**
     * @Author: PPX
     * @Description: 不临接植花
     * @Date: 2019-08-12
     * @Param N:
     * @Param paths:
     * @return: int[]
     **/
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i < N + 1 ; ++i)
            graph.put(i, new HashSet<>());
        for (int[] path : paths) {
            graph.get(path[0]).add(path[1]);
            graph.get(path[1]).add(path[0]);
        }
        int[] res = new int[N];
        for (int i = 0; i < N; ++i) {
            boolean[] used = new boolean[5];
            for (int connect : graph.get(i + 1)) {
                used[res[connect - 1]] = true;
            }
            for (int j = 1; j < 5; ++j) {
                if (!used[j]){
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * @Author: PPX
     * @Description: 找到小镇的法官
     * @Date: 2019-08-12
     * @Param N:
     * @Param trust:
     * @return: int
     **/
    public int findJudge(int N, int[][] trust) {
        int ret = -1;
        if (trust.length < 1)
            return ret;
        if (trust.length == 1)
            return 1;
        int[] trustCount = new int[N + 1];
        for (int i = 0; i < trust.length; ++i) {
            trustCount[trust[i][1]]++;
        }
        for (int i = 0; i < N + 1; i++) {
            if (trustCount[i] == N - 1 && noTrust(trust, i)) {
                if (ret == -1)
                    ret = i;
                else
                    return -1;
            }
        }
        return ret;
    }

    private boolean noTrust(int[][] trust, int index) {
        for (int i = 0; i < trust.length; ++i) {
            if (trust[i][0] == index)
                return false;
        }
        return true;
    }
}
