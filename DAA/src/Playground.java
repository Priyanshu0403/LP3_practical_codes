
class Practice {

    int knapsack(Item[] items, int W) {
        int n = items.length;
        int[][] dp = new int[n+1][W+1];

        for (int i=1 ; i<=n ; i++) {
            for (int w=0 ; w<=W ; w++) {
                if (items[i-1].weight <= w) {
                    dp[i][w] = Math.max(
                            dp[i-1][w],
                            items[i-1].value + dp[i-1][w - (int) items[i-1].weight]
                    );
                }
                else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        return dp[n][W];
    }


}


public class Playground {
    public static void main(String[] args) {
        Item[] items = {
                new Item(40, 2),  // value=60, weight=10
                new Item(50, 3), // value=100, weight=20
                new Item(100, 1),  // value=120, weight=30
                new Item(95, 5),  // value=120, weight=30
                new Item(30, 3),  // value=120, weight=30
        };

        int W = 10;

        Practice p = new Practice();
        System.out.println("Maximum value: " + p.knapsack(items, W));
    }
}