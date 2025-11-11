// Write a program to solve a fractional Knapsack problem using a greedy method.

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class FractionalKnapsack {
    public double knapsack(int W, Item[] items) {

        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                double profitA = (double) a.value/a.weight;
                double profitB = (double) b.value/b.weight;

                return Double.compare(profitB, profitA);
            }
        });

        double profit = 0;
        float rem = W;
        int i=0;

        while (i<items.length && rem > 0) {
            if (items[i].weight <= rem) {
                profit += items[i].value;
                rem -= items[i].weight;
                i++;
            }
            else {
                double fraction = ((double) rem/(double) items[i].weight);
                profit += (items[i].value*fraction);
                break;
            }
        }

        return profit;
    }

}

public class A3 {

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

        FractionalKnapsack kp = new FractionalKnapsack();

        System.out.println("\nMaximum profit: " + kp.knapsack(W, items));
        // Sample input: 10 5 40 2 50 3 100 1 95 5 30 3
        // Sample input's output: 268.3333...


    }

}

/*1. Problem Overview

The Fractional Knapsack problem is about maximizing profit in a knapsack of limited capacity:

Each item has a value (profit) and weight.

You can take whole items or fractions of items.

Goal: maximize total profit without exceeding the knapsack capacity.

Approach: greedy algorithm — always pick the item with the highest value/weight ratio first.

2. Items Storage

Items are stored as objects in an array.

Each Item object contains both value and weight.

Arrays store references to these objects, allowing a single element to “hold” multiple values.

3. Sorting Items by Profit-to-Weight Ratio

Items are sorted in descending order of value/weight.

This ensures the algorithm always considers the most profitable items per unit weight first.

Sorting uses a comparator because the items are custom objects, not simple numbers.

4. Greedy Knapsack Filling

Initialize remaining capacity (rem) and total profit (profit).

Iterate through the sorted items:

If the item fits fully:

Add its value to total profit.

Reduce remaining capacity.

If the item doesn’t fit fully:

Take the fraction that fits.

Add fractional profit to total profit.

Stop — knapsack is full.

5. Maximum Profit Calculation

By following the sorted order and filling the knapsack greedily:

You ensure locally optimal choices at each step.

For fractional knapsack, this produces a globally optimal solution.

6. Why Greedy Works

At every step, choosing the item with the highest value-to-weight ratio maximizes profit for the remaining capacity.

Fractional items allow flexibility, so this greedy approach always produces the maximum possible profit.

7. Key Concepts

Custom object (Item) allows storing value and weight together.

Priority (sorting by value/weight ratio) drives the greedy selection.

Fractional selection ensures knapsack capacity is fully utilized.

Arrays of objects let each element store multiple related values. */