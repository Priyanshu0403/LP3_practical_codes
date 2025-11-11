// Write a program to solve a 0-1 Knapsack problem using dynamic programming or branch and
// bound strategy
import java.util.Scanner;

class DP {

    // Returns the maximum value that
    // can be put in a knapsack of capacity W
    int knapsackDP(int W, Item[] items) {
        int n = items.length;
        int[] dp = new int[W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = W; w >= 0 && items[i - 1].weight <= w ; w--) {
                dp[w] = Math.max(
                        items[i - 1].value + dp[w - items[i - 1].weight],
                        dp[w]
                );
            }
        }
        return dp[W];
    }

}

public class A4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the capacity of Knapsack: ");
        int W = sc.nextInt();
        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        for (int i=0 ; i<n ; i++) {
            System.out.print("Enter profit for item " + (i+1) + ": ");
            int val = sc.nextInt();
            System.out.print("Enter weight for item " + (i+1) + ": ");
            int w = sc.nextInt();
            items[i] = new Item(val, w);
        }

        DP dp = new DP();
        System.out.println("\nMaximum profit using Dynamic Programming: " + dp.knapsackDP(W, items));


        sc.close();

        // Sample input: 10 5 40 2 50 3 100 1 95 5 30 3
        // Sample input's answer: 245

    }
}

/*🎯 Goal:

To find the maximum total profit that can fit into a knapsack of capacity W
when each item can either be included or excluded (0/1 Knapsack Problem).

🧩 Concept of Dynamic Programming (DP):

Dynamic Programming is used when:

The problem has overlapping subproblems (same smaller cases appear multiple times)

The problem has optimal substructure (the overall best solution is built from best smaller solutions)

In the 0/1 Knapsack, we decide for each item:

“Should I take this item or leave it?”

This decision depends on smaller capacities → perfect for DP.

⚙️ How This Code Works Step-by-Step
(1) Input Phase

User enters:

Knapsack capacity W

Number of items n

Profit (value) and weight of each item.

Each item is stored in an Item object → Item[] items.

(2) Creating DP Array
int[] dp = new int[W + 1];


dp[w] represents maximum profit possible with capacity w.

Initially all 0 (no items chosen yet).

(3) Filling the DP Array
for (int i = 1; i <= n; i++) {
    for (int w = W; w >= 0 && items[i - 1].weight <= w; w--) {
        dp[w] = Math.max(
            items[i - 1].value + dp[w - items[i - 1].weight],
            dp[w]
        );
    }
}


Let’s break this down:

Outer loop (i) → goes through each item one by one.

Inner loop (w) → checks all capacities from full (W) down to 0.

We go backward so that we don’t reuse the same item multiple times.

(4) The Key Formula
dp[w] = Math.max(
    items[i - 1].value + dp[w - items[i - 1].weight],  // include the item
    dp[w]                                              // exclude the item
);


This means:

At capacity w, take the better of:

Including the item (if it fits)

Excluding the item

If the item fits (items[i-1].weight <= w):

Including it gives profit = item’s value + best profit for remaining weight (w - weight)

Excluding it keeps profit = dp[w]

(5) Final Result

dp[W] → maximum profit possible with full knapsack capacity.
That’s printed as the final output.

💡 Dry Run Example

For example:

Capacity W = 10
Items:
1) value=40, weight=2
2) value=50, weight=3
3) value=100, weight=1
4) value=95, weight=5
5) value=30, weight=3


After DP updates,
dp[10] = 245 → maximum profit that fits in a capacity of 10.

🧾 Key Points
Concept             Explanation
Type	         0/1 Knapsack (each item taken or not)
Approach	     Bottom-up DP (iterative)
Time Complexity	  O(n × W)
Space Complexity	O(W) (optimized 1D array)
Advantage	      Exact optimal solution
Limitation	       Not efficient for very large W (since it depends on capacity, not items count) */




/*⚔️ 2. Theory of Branch and Bound (for Knapsack)
used for minimization
minimum is the best answer
so the maximum profit is converted to negative profit for minimization
🎯 Purpose
branching- process of generating subproblems
bounding- refers to ignoring subproblems that cannot yield a better solution than the best one found so far.

in this method we basically extend the cheapest partial path.

Branch and Bound is another approach to solve 0/1 Knapsack exactly —
it’s not DP, but uses a search tree with pruning to avoid unnecessary paths.

🧠 Main Idea

Consider the problem as a binary decision tree:

Each node represents including or excluding an item.

Left child → include item

Right child → exclude item

The root node represents starting with no items.

🧩 Bounding (Upper Bound)

For each node, compute an upper bound (maximum possible profit if we could fill remaining capacity fractionally — like in fractional knapsack).

If this upper bound is less than the best profit already found,
we discard (prune) that branch.

⚙️ Algorithm Steps

Sort items by value/weight ratio (descending order).

Start with root node:

Level = 0

Profit = 0

Weight = 0

Bound = maximum possible profit from fractional inclusion

Use a Priority Queue (max-heap) to always expand node with highest bound first.

At each node:

Branch 1: Include current item (if it fits)

Branch 2: Exclude current item

Compute new profit, weight, and bound.

Add both branches to queue if their bound > current best.

Continue until queue empty
→ The best profit obtained is the answer.

⚡ Advantages

Finds exact optimal solution.

Efficient for large problems where DP is memory-heavy.

Skips exploring impossible or less promising paths.

📉 Drawbacks

Still exponential in worst case.

Needs complex implementation with bound calculations.

🧾 Summary
Approach	        Type	           Time             Space	           Notes
DP	               Bottom-up	      O(n×W)	         O(W)	Works well when W is small
Branch & Bound	Tree + Heuristic	Depends on pruning	 O(n)	Works well when W is large or pruning is strong */