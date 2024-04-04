import sun.nio.cs.ext.ISO2022_CN_CNS;

import java.util.Scanner;

public class Main {

    // 尝试提交改动
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // 测试用例数量
        scanner.nextLine();

        while (t-- > 0) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            scanner.nextLine();

            boolean res = true;
            // 标记是否被访问
            boolean[][] visit = new boolean[x][y];
            for (int i = 0; i < x; i++)
                for (int j = 0; j < y; j++)
                    visit[i][j] = false;
            // 输入
            char[][] input = new char[x][y];

            for (int i = 0; i < x; i++) {
                String s = scanner.nextLine();
                input[i] = s.toCharArray();
            }

            for (int i = 0; i < x && res; i++) {
                for (int j = 0; j < y && res; j++) {
                    if (input[i][j] == '*' && !visit[i][j]) {
                        if (j + 1 < y && input[i][j + 1] == '*')
                            visit[i][j] = true;
                        else
                            res = false;

                        if (i + 1 < x && input[i + 1][j] == '*')
                            visit[i][j] = true;
                        else
                            res = false;

                        if (i + 1 < x && j + 1 < y && input[i + 1][j + 1] == '*')
                            visit[i][j] = true;
                        else
                            res = false;
                    }
                    // 每个都不出现新的 *
                    if (res == true) {
                        if (dfs(i, j, input, visit) == false)
                            res = false;

                        if (dfs(i, j + 1, input, visit) == false)
                            res = false;

                        if (dfs(i + 1, j, input, visit) == false)
                            res = false;

                        if (dfs(i + 1, j + 1, input, visit) == false)
                            res = false;
                    }
                }
            }
            System.out.println(res == true ? "Yes" : "No");
        }
        scanner.close();
    }
    public static boolean dfs(int i, int j, char[][] input, boolean[][] visit) {
        int n = input.length, m = input[0].length;
        int[][] array = {
                {-1, -1},
                {-1 ,0},
                {-1, 1},
                {0, 1},
                {1, 1},
                {1, 0},
                {1, -1},
                {0, -1},
        };
        for (int k = 0; k < 8; k++) {
            int new_i = i + array[k][0], new_j = j + array[k][1];
            if (new_i < n && new_i > 0 && new_j > 0 && new_j < m && !visit[new_i][new_j] && input[new_i][new_j] == '*') {
                return false;
            }
        }
        return true;
    }
}
