import java.util.*;

public class DynamicProgram {
    /**
     * @Author: PPX
     * @Description: 322.零钱兑换
     * @Date: 2019-08-21
     * @Param coins:
     * @Param amount:
     * @return: int
     **/
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        if (len == 0)
            return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < len; ++i) {
            if (coins[i] == amount)
                return 1;
            if (coins[i] < amount)
                dp[coins[i]] = 1;
        }
        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                if (i > coins[j] && dp[i - coins[j]] != -1)
                    dp[i] = dp[i] == -1 ? dp[i - coins[j]] + 1 : Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount];
    }

    /**
     * @Author: PPX
     * @Description: 最长上升子序列
     * @Date: 2019-08-20
     * @Param nums:
     * @return: int
     **/
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    /**
     * @Author: PPX
     * @Description: 完全平方数
     * @Date: 2019-08-20
     * @Param n:
     * @return: int
     **/
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * @Author: PPX
     * @Description: 乘积最大子序列
     * @Date: 2019-08-20
     * @Param nums:
     * @return: int
     **/
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int maxMulti = nums[0];
        for (int i = 1; i < len; ++i) {
            int temp = 1, curMulti = Integer.MIN_VALUE;
            for (int j = i; j >= 0; --j) {
                temp *= nums[j];
                curMulti = Math.max(curMulti, temp);
            }
            maxMulti = Math.max(maxMulti, curMulti);
        }
        return maxMulti;
    }

    /**
     * @Author: PPX
     * @Description: 单词拆分(背包问题 ， 最多装多少物品)
     * @Date: 2019-08-20
     * @Param s:
     * @Param wordDict:
     * @return: boolean
     **/
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; ++i) {
            //此处体现背包思想，分段看是否存在该大小的物品
            for (int j = 0; j < i; ++j) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    /**
     * @Author: PPX
     * @Description: 交错字符串
     * @Date: 2019-08-20
     * @Param s1:
     * @Param s2:
     * @Param s3:
     * @return: boolean
     **/
    public boolean isInterleave(String s1, String s2, String s3) {
        int rows = s1.length(), cols = s2.length();
        if (rows + cols != s3.length())
            return false;
        boolean[][] dp = new boolean[rows + 1][cols + 1];
        dp[0][0] = true;
        for (int i = 1; i <= rows; ++i) dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        for (int i = 1; i <= cols; ++i) dp[0][i] = dp[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= cols; ++j) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[rows][cols];
    }

    /**
     * @Author: PPX
     * @Description:
     * @Date: 2019-08-20
     * @Param triangle:
     * @return: int
     **/
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        int maxLength = triangle.get(height - 1).size();
        if (height == 0)
            return 0;
        int[][] dp = new int[height][maxLength];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < height; ++i) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        for (int i = 2; i < height; ++i) {
            for (int j = 1; j < i; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < maxLength; ++i) {
            if (ret > dp[height - 1][i])
                ret = dp[height - 1][i];
        }
        return ret;
    }

    /**
     * @Author: PPX
     * @Description: 解码方法
     * @Date: 2019-08-15
     * @Param s:
     * @return: int
     **/
    public int numDecodings(String s) {
        if (s.charAt(0) == '0')
            return 0;
        int len = s.length();
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; ++i) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '0' || s.charAt(i - 1) > '2')
                    return 0;
                dp[i] = i > 1 ? dp[i - 2] : 1;
            } else {
                if (s.charAt(i - 1) == '0')
                    dp[i] = dp[i - 1];
                else {
                    if (Integer.parseInt(s.substring(i - 1, i + 1)) > 26)
                        dp[i] = i > 1 ? dp[i - 1] : 1;
                    else
                        dp[i] = i > 1 ? dp[i - 1] + dp[i - 2] : 2;
                }
            }

        }
        return dp[len - 1];
    }

    /**
     * @Author: PPX
     * @Description: 最小路径和
     * @Date: 2019-08-15
     * @Param grid:
     * @return: int
     **/
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j <= i; ++j) {
                dp[i][0] += grid[j][0];
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                dp[0][i] += grid[0][j];
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * @Author: PPX
     * @Description: 63.不同路径II
     * @Date: 2019-08-15
     * @Param obstacleGrid:
     * @return: int
     **/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            if (obstacleGrid[i][0] == 0)
                dp[i][0] = 1;
            else
                break;
        }
        for (int i = 0; i < n; ++i) {
            if (obstacleGrid[0][i] == 0)
                dp[0][i] = 1;
            else
                break;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 1)
                    continue;
                if (obstacleGrid[i - 1][j] == 0)
                    dp[i][j] += dp[i - 1][j];
                if (obstacleGrid[i][j - 1] == 0)
                    dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * @Author: PPX
     * @Description: 62.不同路径
     * @Date: 2019-08-15
     * @Param m:
     * @Param n:
     * @return: int
     **/
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) dp[i][0] = 1;
        for (int i = 0; i < n; ++i) dp[0][i] = 1;
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * @Author: PPX
     * @Description: 最大正方形
     * @Date: 2019-08-11
     * @Param matrix:
     * @return: int
     **/
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int max = 0;
        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= cols; ++j) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }

    /**
     * @Author: PPX
     * @Description: 最长回文子串
     * @Date: 2019-08-02
     * @Param s:
     * @return: java.lang.String
     **/
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * @Author: PPX
     * @Description: 摆动序列，此类即不知道最优解的长度
     * @Date: 2019-07-31
     * @Param nums:
     * @return: int
     **/
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int uptemp = 0, downtemp = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                uptemp = Math.max(uptemp, downtemp + 1);
            } else if (nums[i] < nums[i - 1]) {
                downtemp = Math.max(downtemp, uptemp + 1);
            }
        }
        return 1 + Math.max(downtemp, uptemp);
    }

    /**
     * @Author: PPX
     * @Description: 使用最小花费爬楼梯, 这个问题可以递归来解决，属于知道最优情况下的索引，即最终情况即上到顶楼n = cost.length
     * @Date: 2019-07-31
     * @Param cost:
     * @return: int
     **/
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 1)
            return 0;
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; ++i) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 2], dp[cost.length - 1]);
    }
}
