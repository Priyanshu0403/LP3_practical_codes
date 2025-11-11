//  Design 8-Queens matrix having first Queen placed. Use backtracking to place remaining
// Queens to generate the final 8-queen’s matrix.
import java.util.Arrays;
import java.util.Scanner;

class NQueens {

    void displayBoard(char[][] board, int n) {
        for (int i=0 ; i<n ; i++) {
            for (int j=0 ; j<n ; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    boolean isSafe(char[][] board, int n, int row, int col) {
        // Checking column
        for (int i=0 ; i<n ; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // upper-left diagonal
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // lower-left diagonal
        for (int i = row+1, j = col-1; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // upper-right diagonal
        for (int i = row-1, j = col+1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        // lower-right diagonal
        for (int i = row+1, j = col+1; i < n && j < n; i++, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    //this is the recursive function to solve N-Queens problem
    //also we can declare it as helper function
    boolean solveNQueens(char[][] board, int n, int row) {
        if (row == n) return true;

        // Check if there is already a queen on the current row
        for (int col=0 ; col<n ; col++) {
            if (board[row][col] == 'Q') return solveNQueens(board, n, row+1);
        }

        // Normal
        for (int col=0 ; col<n ; col++) {
            if (isSafe(board, n, row, col)) {
                board[row][col] = 'Q';

                if (solveNQueens(board, n, row+1)) return true;
                //backtracking and removing the queen from the current position
                board[row][col] = '-';
            }
        }
        return false;
    }

}

public class A5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of queens: ");
        int n = sc.nextInt();

        if (n <= 0) System.out.println("Invalid input");

        char[][] board = new char[n][n];
        for (int i=0 ; i<n ; i++) {
            char[] arr = new char[n];
            Arrays.fill(arr, '-');
            board[i] = arr;
        }

        System.out.println("Indexes are 1-based...");
        System.out.print("First queen row: ");
        int i = sc.nextInt();
        System.out.print("First queen column: ");
        int j = sc.nextInt();

        NQueens q = new NQueens();

        if (i>n || j>n || i<=0 || j<=0) {
            System.out.println("Invalid inputs");
        }
        else {
            board[i-1][j-1] = 'Q';

            if (q.solveNQueens(board, n, 0)) {
                System.out.println();
                q.displayBoard(board, n);
            } else {
                System.out.println("No valid solution");
            }
        }

        /*
        Sample input: 5 3 2
        Sample output:
        Q - - - -
        - - - Q -
        - Q - - -
        - - - - Q
        - - Q - -
        */

    }
}


/*🎯 AIM

To design and implement the N-Queens problem (for 8-Queens) using backtracking, where the position of the first queen is fixed by the user.

🧩 THEORY
1️⃣ The N-Queens Problem

The N-Queens problem asks:

Can we place N queens on an N × N chessboard such that no two queens attack each other?

A queen can attack:

Vertically (same column)

Horizontally (same row)

Diagonally (both directions)

The challenge is to place all queens so that none share any of those lines of attack.

2️⃣ Concept of Backtracking

Backtracking is a depth-first search (DFS) technique used for solving constraint satisfaction problems.

It works like this:

Place a queen in a valid position.

Move to the next row and attempt to place another queen.

If no valid position is found in the next row → backtrack (remove the last queen and try another position).

Continue until:

All queens are placed (solution found), or

All possibilities are tried (no solution).

It’s like trying all possibilities, but intelligently skipping impossible paths.

3️⃣ Approach Used in This Code
Input:

Number of queens n (usually 8 for 8-Queens)

Position of the first queen (row, column) (1-based index)

Process:

Initialize an n × n chessboard with '-' (empty cells).

Place the first queen at the user-specified position.

Use recursion + backtracking to place the remaining queens one by one on subsequent rows.

Each recursive call tries to find a safe column in that row.

Output:

A valid chessboard configuration if possible.

Otherwise, “No valid solution”.

🧠 EXPLANATION OF EACH FUNCTION
💡 main() (Driver Code)

Takes n (number of queens).

Creates an empty board filled with '-'.

Takes the first queen’s position from the user (1-based).

Calls solveNQueens() to fill the remaining positions.

Displays the result.

Sample input:

Enter number of queens: 8
Indexes are 1-based...
First queen row: 3
First queen column: 2

💡 displayBoard(char[][] board, int n)

Prints the board in matrix format.

'Q' represents a queen, '-' means empty.

Example:

- Q - -
- - - Q
Q - - -
- - Q -

💡 isSafe(char[][] board, int n, int row, int col)

Checks whether a queen can be safely placed at (row, col).

It ensures that:

Column Check: No other queen exists in that column.

Upper-left Diagonal Check

Lower-left Diagonal Check

Upper-right Diagonal Check

Lower-right Diagonal Check

If no conflicts → returns true, else false.

💡 solveNQueens(char[][] board, int n, int row)

This is the core recursive backtracking function.

Steps:

Base Case:
If row == n → all queens are placed → return true.

Check for Pre-Placed Queen:
If a queen already exists in the current row (the one fixed by the user),
→ skip to the next row:

if (board[row][col] == 'Q') return solveNQueens(board, n, row + 1);


Try all columns in the current row:
For each column:

Check if (row, col) is safe using isSafe().

If safe, place queen ('Q').

Recurse for the next row.

If the recursion returns false, remove the queen ('-') → backtrack.

If no safe position found, return false.

💡 Backtracking in Action (Concept)

Example for n = 4:

1. Place a queen in row 0, col 1
2. Move to row 1 — try each column
3. If conflict → backtrack to previous row and move the queen
4. Repeat until all rows filled or no possibility remains

⚙️ OUTPUT EXAMPLE
Input:
Enter number of queens: 5
Indexes are 1-based...
First queen row: 3
First queen column: 2

Output:
Q - - - -
- - - Q -
- Q - - -
- - - - Q
- - Q - -

🧾 FLOW OF EXECUTION

Start from row = 0.

Try placing a queen in a column that is safe.

Move to the next row recursively.

If no position in a row works → backtrack.

Stop when all queens are placed (row == n).

🧮 TIME COMPLEXITY

In the worst case, the algorithm checks all possible placements:

𝑂
(
𝑁
!
)
O(N!)

because the first queen has N choices, the second has (N−1), etc.

Space Complexity: O(N^2) (due to the board + recursion stack).

🧑‍🏫 VIVA QUESTIONS (with answers)

Q1. What is backtracking?

Backtracking is a method of solving problems by trying all possible configurations and undoing the previous step when a configuration fails (backtrack).

Q2. Why do we check only diagonals and columns, not rows?

Because we place only one queen per row, so horizontal conflicts are impossible.

Q3. What is the base case in your recursion?

When row == n, meaning all queens are placed successfully.

Q4. What happens when a position is unsafe?

The algorithm skips that cell and tries the next one.

Q5. Can there be multiple solutions?

Yes, N-Queens has multiple valid boards, but this code stops at the first valid one (it returns when it finds true).

Q6. Why is the 2-Queen or 3-Queen problem unsolvable?

Because it’s impossible to place 2 or 3 queens without conflict on such small boards.

Q7. What modifications can make it show all solutions?

Instead of returning true after finding one solution, you can store each solution in a list and continue the recursion.

🧠 SUMMARY
Concept        	Description
Problem	         Place N queens so that no two attack each other
Method	        Backtracking
Key Functions	  isSafe(), solveNQueens(), displayBoard()
Input	         N and first queen’s coordinates
Output	        A valid N-Queens matrix or “No valid solution”
Base Case	    When all queens are placed (row == n)
Backtracking	Undo placement when no safe column exists
Time Complexity	    O(N!)
 */