import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class hashNQueens {
    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of queens");
            int n = sc.nextInt();

            // ans will hold all the possible solutions
            List<List<String>> ans = solveNQueens(n);
            System.out.println("Total number of possibilities: " + (ans.size()));
            System.out.println(ans);
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        helper(result, new ArrayList<String>(), 0, new boolean[n], new boolean[2 * n], new boolean[2 * n], n);
        return result;
    }

    private static void helper(List<List<String>> result, ArrayList<String> board, int row, boolean[] cols,
            boolean[] d1,
            boolean[] d2, int n) {
        if (row >= n) {
            // each formed board will be added to the result
            result.add(new ArrayList<String>(board));
        }
        for (int col = 0; col < n; col++) {
            int for_upper_diagonal = col - row + n;
            int for_lower_diagonal = row + col;
            if (!cols[col] && !d1[for_upper_diagonal] && !d2[for_lower_diagonal]) {
                char[] r = new char[n];
                Arrays.fill(r, '.');
                r[col] = 'Q';
                cols[col] = true;
                d1[for_upper_diagonal] = true;
                d2[for_lower_diagonal] = true;
                board.add(new String(r)); // put the array as String

                // check for the row present ahead
                helper(result, board, row + 1, cols, d1, d2, n);

                // backtrack
                board.remove(board.size() - 1); // remove the last added value
                cols[col] = false;
                d1[for_upper_diagonal] = false;
                d2[for_lower_diagonal] = false;

            }
        }
    }
}