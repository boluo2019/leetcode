package lcp;

import java.util.ArrayList;
import java.util.List;

/**
 * 4. 快速公交
 * 通过的用户数5
 * 尝试过的用户数24
 * 用户总通过次数5
 * 用户总提交次数32
 * 题目难度Hard
 * 小扣打算去秋日市集，由于游客较多，小扣的移动速度受到了人流影响：
 * <p>
 * 小扣从 x 号站点移动至 x + 1 号站点需要花费的时间为 inc；
 * 小扣从 x 号站点移动至 x - 1 号站点需要花费的时间为 dec。
 * 现有 m 辆公交车，编号为 0 到 m-1。小扣也可以通过搭乘编号为 i 的公交车，从 x 号站点移动至 jump[i]*x 号站点，耗时仅为 cost[i]。小扣可以搭乘任意编号的公交车且搭乘公交次数不限。
 * <p>
 * 假定小扣起始站点记作 0，秋日市集站点记作 target，请返回小扣抵达秋日市集最少需要花费多少时间。由于数字较大，最终答案需要对 1000000007 (1e9 + 7) 取模。
 * <p>
 * 注意：小扣可在移动过程中到达编号大于 target 的站点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 31, inc = 5, dec = 3, jump = [6], cost = [10]
 * <p>
 * 输出：33
 * <p>
 * 解释：
 * 小扣步行至 1 号站点，花费时间为 5；
 * 小扣从 1 号站台搭乘 0 号公交至 6 * 1 = 6 站台，花费时间为 10；
 * 小扣从 6 号站台步行至 5 号站台，花费时间为 3；
 * 小扣从 5 号站台搭乘 0 号公交至 6 * 5 = 30 站台，花费时间为 10；
 * 小扣从 30 号站台步行至 31 号站台，花费时间为 5；
 * 最终小扣花费总时间为 33。
 * <p>
 * 示例 2：
 * <p>
 * 输入：target = 612, inc = 4, dec = 5, jump = [3,6,8,11,5,10,4], cost = [4,7,6,3,7,6,4]
 * <p>
 * 输出：26
 * <p>
 * 解释：
 * 小扣步行至 1 号站点，花费时间为 4；
 * 小扣从 1 号站台搭乘 0 号公交至 3 * 1 = 3 站台，花费时间为 4；
 * 小扣从 3 号站台搭乘 3 号公交至 11 * 3 = 33 站台，花费时间为 3；
 * 小扣从 33 号站台步行至 34 站台，花费时间为 4；
 * 小扣从 34 号站台搭乘 0 号公交至 3 * 34 = 102 站台，花费时间为 4；
 * 小扣从 102 号站台搭乘 1 号公交至 6 * 102 = 612 站台，花费时间为 7；
 * 最终小扣花费总时间为 26。
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 10^9
 * 1 <= jump.length, cost.length <= 10
 * 2 <= jump[i] <= 10^6
 * 1 <= inc, dec, cost[i] <= 10^6
 *
 * @author maozhouge
 */
public class LCP0912HardBusRapidTransit {
    public static int busRapidTransit(int target, int inc, int dec, int[] jump, int[] cost) {
        List<Delta> forward = new ArrayList<>(jump.length + 1);
        List<Delta> backward = new ArrayList<>(1);
        forward.add(new Delta(1, inc));
        backward.add(new Delta(1, dec));
        for (int i = 0; i < jump.length; i++) {
            forward.add(new Delta(jump[i], cost[i]));
        }
        int[][] res = new int[target + 1][target + 1];
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j <= target; j++) {
                res[i][j] = -1;
            }
        }
        res[0][0] = 0;

        return getMinCost(0, target, forward, backward, res);
    }

    private static int getMinCost(int start, int end, List<Delta> forward, List<Delta> backward, int[][] res) {
        if (start >= res.length || end >= res.length) {
            throw new IllegalArgumentException("invalid");
        }
        if (res[start][end] >= 0) {
            return res[start][end];
        }

        int minCost = 0;
        if (start > end) {
            for (Delta delta : backward) {
                int newEnd = end - delta.deltaIndex;
                int cost = getMinCost(start, newEnd, forward, backward, res) + delta.deltaCost;
                minCost = Math.min(minCost, cost);
            }
        } else {
            for (Delta delta : forward) {
                int cost = getMinCost(start + delta.deltaIndex, end, forward, backward, res) + delta.deltaCost;
                minCost = Math.min(minCost, cost);
            }
        }

        res[start][end] = minCost;
        return minCost;
    }

    private static class Delta {
        final int deltaIndex;
        final int deltaCost;

        private Delta(int deltaIndex, int deltaCost) {
            this.deltaIndex = deltaIndex;
            this.deltaCost = deltaCost;
        }
    }

    public static void main(String[] args) {
        int target = 31, inc = 5, dec = 3;
        int[] jump = {6}, cost = {10};
        // 33
        System.out.println(busRapidTransit(target, inc, dec, jump, cost));
        target = 612;
        inc = 4;
        dec = 5;
        jump = new int[]{3, 6, 8, 11, 5, 10, 4};
        cost = new int[]{4, 7, 6, 3, 7, 6, 4};
        // 26
        System.out.println(busRapidTransit(target, inc, dec, jump, cost));
    }
}
