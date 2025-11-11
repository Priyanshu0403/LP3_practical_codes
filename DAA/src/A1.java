// 1. Write a program to calculate Fibonacci numbers and find its step count.
import java.util.Scanner;

class Solution {
    int stepRec = 0;    
    int fibonacci_rec(int n) {
        stepRec++; 
        if (n < 1) return 0;
        if (n == 1) return 1;

        int result = fibonacci_rec(n-1) + fibonacci_rec(n-2);
        return result;
    }
    int stepIter = 0;
    int fibonacci_iter(int n) {
      
        int firstNum = 0,secondNum = 1;
        for (int i=2 ; i<=n ; i++) {
            stepIter++;
            int nextNum = firstNum + secondNum;
            firstNum = secondNum;
            secondNum = nextNum;
        }

        return secondNum;
    }


}


public class A1 {

    public static void main(String[] args) {

        Solution obj = new Solution();
        long start;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        sc.close();

        start = System.nanoTime();
        System.out.println("\nn'th fibonacci using iterative (nanoseconds): " + obj.fibonacci_iter(n));
        System.out.println("Time for iterative: " + (System.nanoTime() - start));
        System.out.println("Steps (iterative): " + obj.stepIter);
        
        
        start = System.nanoTime();
        System.out.println("\nn'th fibonacci using recursive (nanoseconds): " + obj.fibonacci_rec(n));
        System.out.println("Time for recursive: " + (System.nanoTime() - start));
        System.out.println("Steps (recursive): " + obj.stepRec);

    }

}

/*🎯 AIM

To write a Java program to calculate the nᵗʰ Fibonacci number using recursion and iteration, and to determine the step count and time taken for both methods.

🧩 THEORY
1️⃣ Fibonacci Sequence

The Fibonacci sequence is a famous mathematical series defined as:

𝐹
(
0
)
=
0
,
𝐹
(
1
)
=
1
F(0)=0,F(1)=1
𝐹
(
𝑛
)
=
𝐹
(
𝑛
−
1
)
+
𝐹
(
𝑛
−
2
)
for 
𝑛
≥
2
F(n)=F(n−1)+F(n−2)for n≥2

So the sequence looks like:

0, 1, 1, 2, 3, 5, 8, 13, 21, ...


Each number is the sum of the previous two numbers.

2️⃣ Approaches to Solve Fibonacci
🌀 Recursive Method

The function calls itself to compute F(n-1) and F(n-2).

Structure:

fib(n) = fib(n-1) + fib(n-2)


Base conditions:

fib(0) = 0

fib(1) = 1

It has overlapping subproblems, meaning the same values are recomputed many times.

📉 Time Complexity: O(2ⁿ)
📈 Space Complexity: O(n) (due to recursive call stack)

⚙️ Iterative Method

Uses a simple loop to compute Fibonacci numbers.

Structure:

for (i = 2; i <= n; i++)
    next = first + second;


Efficient, since each value is computed only once.

🚀 Time Complexity: O(n)
🧠 Space Complexity: O(1)

3️⃣ Step Count

Step count is used to measure algorithmic efficiency —
how many “important” steps or operations the algorithm takes to reach the result.

In recursion, stepRec increments each time a recursive call is made.

In iteration, stepIter increments inside the loop.

Thus, comparing stepRec and stepIter helps show which approach is more efficient.

4️⃣ Time Measurement

You’re using:

System.nanoTime()


to measure how long each algorithm takes in nanoseconds.

🧠 EXPLANATION OF CODE
🔹 Class Solution

Contains two implementations of Fibonacci:

fibonacci_rec(int n) → recursive version

fibonacci_iter(int n) → iterative version

1. Recursive Fibonacci
int fibonacci_rec(int n) {
    stepRec++; 
    if (n < 1) return 0;
    if (n == 1) return 1;
    return fibonacci_rec(n-1) + fibonacci_rec(n-2);
}


Logic:

Every call increases stepRec counter.

Base case: when n < 1 or n == 1.

Recursive case: sum of the previous two Fibonacci numbers.

Drawback:
For large n, the number of recursive calls grows exponentially.

2. Iterative Fibonacci
int fibonacci_iter(int n) {
    int firstNum = 0, secondNum = 1;
    for (int i = 2; i <= n; i++) {
        stepIter++;
        int nextNum = firstNum + secondNum;
        firstNum = secondNum;
        secondNum = nextNum;
    }
    return secondNum;
}


Logic:

Starts with 0 and 1.

Repeats addition to find each next term.

Much faster and more efficient than recursion.

🔹 Class A1 (Main Class)

This class:

Takes input n from user.

Calls both methods.

Displays:

Fibonacci number

Time taken (in nanoseconds)

Step count

🧮 SAMPLE OUTPUT
Example Input:
Enter n: 10

Example Output:
n'th fibonacci using iterative (nanoseconds): 55
Time for iterative: 25900
Steps (iterative): 8

n'th fibonacci using recursive (nanoseconds): 55
Time for recursive: 710000
Steps (recursive): 177

📊 COMPARISON TABLE
Method	Time Complexity	Space Complexity	Steps (approx.)	Efficiency
Recursive	O(2ⁿ)	O(n)	High (Exponential)	Slow
Iterative	O(n)	O(1)	Low (Linear)	Fast
🧾 FLOW OF EXECUTION

User enters n.

Program computes fibonacci_iter(n) using a loop.

Counts steps and measures time.

Program then computes fibonacci_rec(n) using recursion.

Prints both results for comparison.

🧪 THEORY QUESTIONS (For Viva)

Q1. What is the Fibonacci series?

A series where each number is the sum of the previous two numbers, starting from 0 and 1.

Q2. What is the difference between recursion and iteration?

Recursion solves problems by repeatedly calling itself; iteration uses loops. Recursion uses more memory and time.

Q3. Which method is more efficient and why?

The iterative method is faster and more space-efficient because it avoids repeated function calls.

Q4. What is the base case in recursion?

When n < 1 (return 0) or n == 1 (return 1).

Q5. What does the step count represent?

The number of significant operations (like additions or recursive calls) required to compute the result.

Q6. Why is recursion slower for Fibonacci?

Because it recomputes the same subproblems many times (e.g., fib(3) gets calculated multiple times).

Q7. How can recursive Fibonacci be optimized?

Using memoization or dynamic programming to store intermediate results.

🧠 SUMMARY
Concept	             Description
Problem	            Compute n-th Fibonacci number
Approach	        Recursive & Iterative
Key Variables	    stepRec, stepIter
Time Measurement	System.nanoTime()
Recursive Time Complexity	O(2ⁿ)
Iterative Time Complexity	O(n)
Recursive Space	            O(n)
Iterative Space	            O(1)
Result	                Iterative is faster and more efficient */