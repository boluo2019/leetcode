package contest;

/**
 * 5511. 二进制矩阵中的特殊位置 显示英文描述
 * 通过的用户数1175
 * 尝试过的用户数1326
 * 用户总通过次数1177
 * 用户总提交次数1515
 * 题目难度Easy
 * 给你一个大小为 rows x cols 的矩阵 mat，其中 mat[i][j] 是 0 或 1，请返回 矩阵 mat 中特殊位置的数目 。
 *
 * 特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。
 *
 *
 *
 * 示例 1：
 *
 * 输入：mat = [[1,0,0],
 *             [0,0,1],
 *             [1,0,0]]
 * 输出：1
 * 解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
 * 示例 2：
 *
 * 输入：mat = [[1,0,0],
 *             [0,1,0],
 *             [0,0,1]]
 * 输出：3
 * 解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
 * 示例 3：
 *
 * 输入：mat = [[0,0,0,1],
 *             [1,0,0,0],
 *             [0,1,1,0],
 *             [0,0,0,0]]
 * 输出：2
 * 示例 4：
 *
 * 输入：mat = [[0,0,0,0,0],
 *             [1,0,0,0,0],
 *             [0,1,0,0,0],
 *             [0,0,1,0,0],
 *             [0,0,0,1,1]]
 * 输出：3
 *
 *
 * 提示：
 *
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] 是 0 或 1
 * @author maozhouge
 */
public class WC206EasyNumSpatial {

    public static int numSpecial(int[][] mat) {
        // time: O(N^2)
        // space: O(N)
        if (mat.length == 0) {
            return 0;
        }
        int[] rowSum = new int[mat.length];
        int[] colSum = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            int[] tmp = mat[i];
            for (int j = 0; j < tmp.length; j++) {
                rowSum[i] += mat[i][j];
                colSum[j] += mat[i][j];
            }
        }

        int count = 0;
        for (int i = 0; i < rowSum.length; i++) {
            if (rowSum[i] != 1) {
                continue;
            }
            for (int j = 0; j < colSum.length; j++) {
                if (colSum[j] != 1) {
                    continue;
                }
                if (mat[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 1
        int[][] mat = {{1,0,0}, {0,0,1}, {1,0,0}};
        System.out.println(numSpecial(mat));
        // 3
        mat = new int[][]{{1,0,0}, {0,1,0}, {0,0,1}};
        System.out.println(numSpecial(mat));
        // 2
        mat = new int[][]{{0,0,0,1}, {1,0,0,0}, {0,1,1,0}, {0,0,0,0}};
        System.out.println(numSpecial(mat));
        // 3
        mat = new int[][]{{0,0,0,0,0}, {1,0,0,0,0}, {0,1,0,0,0}, {0,0,1,0,0}, {0,0,0,1,1}};
        System.out.println(numSpecial(mat));
        // 2
        mat = new int[][]{{0,0,1,0}, {0,0,0,0}, {0,0,0,0}, {0,1,0,0}};
        System.out.println(numSpecial(mat));
        // 1
        mat = new int[][]{
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,1,0,0,0},
                {1,0,0,0,1,0,0,0},
                {0,0,1,1,0,0,0,0}
        };
        System.out.println(numSpecial(mat));
    }
}
