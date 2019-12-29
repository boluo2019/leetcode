package stack;

public class Medium71SimplifyPath {

	/**
	 * 题目描述：
	 *	Given an absolute path for a file (Unix-style), simplify it.
	 *	Or in other words, convert it to the canonical path.
	 *
	 * In a UNIX-style file system, a period . refers to the current directory.
	 * Furthermore, a double period .. moves the directory up a level.
	 * For more information, see: Absolute path vs relative path in Linux/Unix
	 *
	 * Note that the returned canonical path must always begin with a slash /,
	 * and there must be only a single slash / between two directory names. 
	 * The last directory name (if it exists) must not end with a trailing /.
	 * Also, the canonical path must be the shortest string representing the absolute path.
	 *
	 *  
	 *
	 * Example 1:
	 *
	 * Input: "/home/"
	 * Output: "/home"
	 * Explanation: Note that there is no trailing slash after the last directory name.
	 * Example 2:
	 *
	 * Input: "/../"
	 * Output: "/"
	 * Explanation: Going one level up from the root directory is a no-op,
	 * as the root level is the highest level you can go.
	 * Example 3:
	 *
	 * Input: "/home//foo/"
	 * Output: "/home/foo"
	 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
	 * Example 4:
	 *
	 * Input: "/a/./b/../../c/"
	 * Output: "/c"
	 * Example 5:
	 *
	 * Input: "/a/../../b/../c//.//"
	 * Output: "/c"
	 * Example 6:
	 *
	 * Input: "/a//b////c/d//././/.."
	 * Output: "/a/b/c"
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/simplify-path
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	/**
	 * 解题思路： 根据 '/' split，子string只会是: '目录名', '.', '..', 空字符串这四种情况。
	 * 双指针
	 * Q1: 输入会不会不是/开头？比如： ../
	 *
	 */

	private static final String CURRENT_DIR = ".";
	private static final String PARENT_DIR = "..";
	// 7ms， split太耗时。
	public static String simplifyPath1(String path) {
		String[] dirs = path.split("/");
		int resNextIndex = 0;
		int curIndex = 0;

		while (curIndex < dirs.length) {
			if (dirs[curIndex].isEmpty() || CURRENT_DIR.equals(dirs[curIndex])) {
			} else if(PARENT_DIR.equals(dirs[curIndex])) {
				if (resNextIndex > 0) {
					resNextIndex--;
				}
			} else {
				dirs[resNextIndex++] = dirs[curIndex];
			}

			curIndex++;
		}

		path = "/";
		for (int i = 0; i < resNextIndex; i++) {
			if (i == 0) {
				path += dirs[i];
			} else {
				path += "/" + dirs[i];
			}
		}

		return path;
	}

	public static String simplifyPath(String path) {
		char[] chars = path.toCharArray();
		int curIndex = 0;
		int nextIndex = 0;

		while (curIndex < chars.length) {

		}

		return path;
	}

	public static void main(String[] args) {
		System.out.println(simplifyPath("/home/")); // "/home"
		System.out.println(simplifyPath("../")); // "/" 还是报错？
		System.out.println(simplifyPath("/./")); // "/"
		System.out.println(simplifyPath("/../")); // "/"
		System.out.println(simplifyPath("/./")); // "/"
		System.out.println(simplifyPath("/home//foo/")); // "/home/foo"
		System.out.println(simplifyPath("/a/./b/../../c/")); // "/c"
		System.out.println(simplifyPath("/a/../../b/../c//.//")); // "/c"
		System.out.println(simplifyPath("/a//b////c/d//././/..")); // "/a/b/c"
	}
}
