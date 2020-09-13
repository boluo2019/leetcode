package lcp;

/**
 * 3. 秋叶收藏集
 * 通过的用户数126
 * 尝试过的用户数278
 * 用户总通过次数126
 * 用户总提交次数395
 * 题目难度Medium
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
 *
 * 示例 1：
 *
 * 输入：leaves = "rrryyyrryyyrr"
 *
 * 输出：2
 *
 * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
 *
 * 示例 2：
 *
 * 输入：leaves = "ryr"
 *
 * 输出：0
 *
 * 解释：已符合要求，不需要额外操作
 *
 * 提示：
 *
 * 3 <= leaves.length <= 10^5
 * leaves 中只包含字符 'r' 和字符 'y'
 *
 * 思路：0-1背包问题的滚动数组
 * 参考资料：
 * 1. https://www.bilibili.com/video/BV1L64y1F7k5
 * 2. https://www.kancloud.cn/kancloud/pack/70124
 * @author maozhouge
 */
public class LCP0912MediumMinimumOperations {
    public static int minimumOperations(String leaves) {
        int res = 0;
        int rLeft = 0, yCount = 0, rMiddle = 0, rRight = 0;
        if (leaves.charAt(0) == 'y') {
            rLeft += 1;
            res += 1;
        }
        if (leaves.charAt(leaves.length() - 1) == 'y') {
            res += 1;
        }
        for (int i = 1; i < leaves.length() - 1; i++) {
            char c = leaves.charAt(i);
            if (c == 'r') {
                if (yCount == 0 && rRight == 0) {
                    rLeft++;
                } else if (yCount > 0) {
                    rRight++;
                }
            } else if (c == 'y') {
                yCount++;
                if (rRight > 0) {
                    rMiddle += rRight;
                    rRight = 0;
                }
            } else {
                throw new IllegalArgumentException("invalid char " + c);
            }
        }

        res += rMiddle;

        if (yCount == 0) {
            res += 1;
        }

        return res;
    }

    public static void main(String[] args) {
        // target : ryr
        System.out.println(minimumOperations("rrryyyrryyyrr")); // 2
        System.out.println(minimumOperations("ryr")); // 0
        System.out.println(minimumOperations("yrrrrrrry")); // 3
        System.out.println(minimumOperations("yryrrrrry")); // 2
        System.out.println(minimumOperations("rrrrrrrrrrrr")); // 1
        System.out.println(minimumOperations("yyyyyyyyyy")); // 2
        System.out.println(minimumOperations("ryyyrrryyr")); // 2
    }
}
