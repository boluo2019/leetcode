package lcp;

import java.util.Arrays;

/**
 * 2. 早餐组合
 * 通过的用户数1085
 * 尝试过的用户数2323
 * 用户总通过次数1086
 * 用户总提交次数4713
 * 题目难度Easy
 * 小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，
 * 一维整型数组 drinks 中记录了每种饮料的价格。
 * 小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。
 * <p>
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 * <p>
 * 示例 1：
 * <p>
 * 输入：staple = [10,20,5], drinks = [5,5,2], x = 15
 * <p>
 * 输出：6
 * <p>
 * 解释：小扣有 6 种购买方案，所选主食与所选饮料在数组中对应的下标分别是：
 * 第 1 种方案：staple[0] + drinks[0] = 10 + 5 = 15；
 * 第 2 种方案：staple[0] + drinks[1] = 10 + 5 = 15；
 * 第 3 种方案：staple[0] + drinks[2] = 10 + 2 = 12；
 * 第 4 种方案：staple[2] + drinks[0] = 5 + 5 = 10；
 * 第 5 种方案：staple[2] + drinks[1] = 5 + 5 = 10；
 * 第 6 种方案：staple[2] + drinks[2] = 5 + 2 = 7。
 * <p>
 * 示例 2：
 * <p>
 * 输入：staple = [2,1,1], drinks = [8,9,5,1], x = 9
 * <p>
 * 输出：8
 * <p>
 * 解释：小扣有 8 种购买方案，所选主食与所选饮料在数组中对应的下标分别是：
 * 第 1 种方案：staple[0] + drinks[2] = 2 + 5 = 7；
 * 第 2 种方案：staple[0] + drinks[3] = 2 + 1 = 3；
 * 第 3 种方案：staple[1] + drinks[0] = 1 + 8 = 9；
 * 第 4 种方案：staple[1] + drinks[2] = 1 + 5 = 6；
 * 第 5 种方案：staple[1] + drinks[3] = 1 + 1 = 2；
 * 第 6 种方案：staple[2] + drinks[0] = 1 + 8 = 9；
 * 第 7 种方案：staple[2] + drinks[2] = 1 + 5 = 6；
 * 第 8 种方案：staple[2] + drinks[3] = 1 + 1 = 2；
 * <p>
 * 提示：
 * <p>
 * 1 <= staple.length <= 10^5
 * 1 <= drinks.length <= 10^5
 * 1 <= staple[i],drinks[i] <= 10^5
 * 1 <= x <= 2*10^5
 *
 * @author maozhouge
 */
public class LCP0912EasyBreakfastNumber {
    private static final long BASE = 1000000007;

    public static int breakfastNumber(int[] staple, int[] drinks, int x) {
        long count = 0;
        // 递增排序
        Arrays.sort(staple);
        Arrays.sort(drinks);

        int maxIndex = drinks.length - 1;
        // staple 升序
        for (int stap : staple) {
            // drinks降序，drinks中当前maxIndex及以下的数值都满足
            while (maxIndex >= 0 && stap + drinks[maxIndex] > x) {
                maxIndex--;
            }
            count += maxIndex + 1;
        }
        return (int) (count % BASE);
    }

    public static void main(String[] args) {
        int[] staple = {10, 20, 5}, drinks = {5, 5, 2};
        int x = 15;
        System.out.println(breakfastNumber(staple, drinks, x));
        staple = new int[]{2, 1, 1};
        drinks = new int[]{8, 9, 5, 1};
        x = 9;
        System.out.println(breakfastNumber(staple, drinks, x));
    }
}
