// Write a program to implement Huffman Encoding using greedy method.

// What is a PriorityQueue?
// A PriorityQueue is a special type of queue where elements are removed based on priority, not order of insertion (FIFO).
// By default, smallest element = highest priority (for numbers or strings).
// Internally, it uses a min-heap structure

// Method	             Description
// add(e) / offer(e)	Insert element
// peek()	            Returns top (highest priority) without removing
// poll()	            Removes and returns top
// isEmpty()	        Checks if empty
// size()	            Returns number of elements
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

// Class to represent huffman tree
class HNode {
    int freq;
    Character c;
    HNode left, right;
    HNode(Character c, int x) {
        this.c = c;
        freq = x;
        left = null;
        right = null;
    }
}

class Huffman {

    HNode constructHTree(Map<Character, Integer> freqMap) {
        // By default, a PriorityQueue can only automatically order:
        // Numbers (Integer, Double, etc.)
        // Strings
        // (because these classes implement the Comparable interface)
        // But when you create a custom class (like HNode), Java has no idea how to compare two HNode objects.
        // So — you give it a custom rule using a Comparator.
        PriorityQueue<HNode> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.freq, o2.freq));
        //This is a lambda expression, short for:
        // new Comparator<HNode>() {
        //     @Override
        //     public int compare(HNode o1, HNode o2) {
        //         return Integer.compare(o1.freq, o2.freq);
        //     }
        // }
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.offer(new HNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HNode first = pq.poll();
            HNode second = pq.poll();

            HNode newNode = new HNode(null, first.freq + second.freq);
            newNode.left = first;
            newNode.right = second;
            pq.offer(newNode);
        }

        return pq.poll();
    }

    void traverse(HNode node, Map<Character, String> codes, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            codes.put(node.c, code);
            return;
        }
        traverse(node.left, codes, code + '0');
        traverse(node.right, codes, code + '1');
    }

    Map<Character, Integer> generateMap(String input) {
        Map<Character, Integer> ans = new HashMap<>();
        for (char c : input.toCharArray()) {
            ans.put(c, ans.getOrDefault(c, 0) + 1);
        }
        return ans;
    }

}

public class A2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String input = sc.nextLine();

        Huffman huffman = new Huffman();
        Map<Character, Integer> mp = huffman.generateMap(input);
        System.out.println("Input map:\n" + mp);

        Map<Character, String> output = new HashMap<>();
        huffman.traverse(huffman.constructHTree(mp), output, "");
        System.out.println("\nOutput: \n" + output);

        // Sample input: azdjkqwejaadjjqkzzxqaa
        // Sample output: {a=01, q=110, d=1111, e=11100, w=11101, x=1000, j=00, z=101, k=1001}
    }
}

/*1. Overall Idea
This program implements Huffman Encoding — a compression technique where:
Frequently used characters get shorter binary codes,
Less frequent ones get longer codes,
so the total encoded message size becomes smaller.
It uses a Greedy Algorithm and a PriorityQueue (min-heap) to build an optimal Huffman Tree.

2. Class: HNode

Represents a node in the Huffman tree.

Each node has:

c: the character (can be null for internal nodes),

freq: frequency of that character,

left, right: pointers to child nodes.

Used to build the binary tree structure where left = 0, right = 1.

3. Class: Huffman

This class does all the Huffman logic.

(a) constructHTree()

Takes a map of characters and their frequencies.

Creates a PriorityQueue that always gives the node with the smallest frequency first.

Uses a lambda comparator (o1, o2) -> Integer.compare(o1.freq, o2.freq) to compare frequencies (since Java doesn’t know how to compare custom objects like HNode by default).

Steps inside:

Convert each entry (character, frequency) into a HNode and add it to the queue.

While more than one node exists:

Remove the two smallest frequency nodes.

Create a new internal node whose frequency = sum of both.

Set the two nodes as its left and right children.

Add this new node back to the queue.

When only one node remains → that’s the root of the Huffman tree.

(b) traverse()

Recursively walks the Huffman tree.

When it reaches a leaf node (a real character, not internal):

Stores its code (like a = 01).

Left edge adds '0', right edge adds '1' to the code.

Builds a map of character -> binary code.

(c) generateMap()

Takes the input string and counts frequency of each character.

Returns a map like {a=4, b=2, c=1}.

Uses getOrDefault() to simplify counting.

4. In main()

Takes the input string from the user.

Generates the frequency map using generateMap().

Builds the Huffman tree using constructHTree().

Traverses the tree to generate Huffman codes using traverse().

Prints both the frequency map and final Huffman codes.

5. Example

For input:
azdjkqwejaadjjqkzzxqaa

The frequency map might look like:

{a=5, z=3, d=2, j=3, k=2, q=3, w=1, e=1, x=1}


And output codes (example):

{a=01, q=110, d=1111, e=11100, w=11101, x=1000, j=00, z=101, k=1001}


Each character gets a unique prefix-free binary code. */
