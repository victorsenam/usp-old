/*************************************************************************
 * $ java-algs4 List < list.in
 * $ java-algs4 List < list2in
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class List<Value> implements Iterable<Value> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {

        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int N;             // subtree count

        public Node(Value val, boolean color, int N) {

            this.val = val;
            this.color = color;
            this.N = N;
        }
    }

   /*************************************************************************
    *  Node helper methods
    *************************************************************************/
    // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) return false;
        return (x.color == RED);
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    } 

   /*************************************************************************
    *  Size methods
    *************************************************************************/

    // return number of key-value pairs in this symbol table
    public int size() { return size(root); }

    // is this symbol table empty?
    public boolean isEmpty() {
        return root == null;
    }

   /*************************************************************************
    *  Standard BST search
    *************************************************************************/

    // value associated with the given rank; null if no such rank
    public Value get(int i) {
	return select(i);
    }
    
   /*************************************************************************
    *  Red-black insertion
    *************************************************************************/

    // insert the rank-value pair
    public void add(int i, Value val) {
	if (i < 0 || i > size()) return;
        root = add(root, i, val);
        root.color = BLACK;
        // assert check();
    }

    // insert the rank-value pair in the subtree rooted at h
    private Node add(Node h, int i, Value val) {
        if (h == null) return new Node(val, RED, 1);

	int k = size(h.left);
        if (i <= k) 
	    h.left = add(h.left, i, val); 
        else 
	    h.right = add(h.right, i - k - 1, val);

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

   /*************************************************************************
    *  Red-black deletion
    *************************************************************************/

    // delete the node with the minimum rank
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the node with the minimum rank in the tree rooted at h
    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    // delete the node with the maximum rank
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the node with the maximum rank in the tree rooted at h
    private Node deleteMax(Node h) { 
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    // delete the node with the given rank
    public void delete(int i) {
        if (i < 0 || i >= size()) { 
            System.err.println("symbol table does not contain " + i + "th element");
            return;
        }

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, i);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the node with the given rank in the tree rooted at h
    private Node delete(Node h, int i) {
        // assert contains(h, key);

	int k = size(h.left);
        if (i < k) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, i);
        }
        else {
            if (isRed(h.left)) {
                h = rotateRight(h);
		k = size(h.left);
	    }
            if (i == k && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
		k = size(h.left);
	    }
            if (i == k) {
                Node x = min(h.right);
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else {
		k = size(h.left);		
		h.right = delete(h.right, i - k - 1);
	    }
        }
        return balance(h);
    }

   /*************************************************************************
    *  addFront, addBack, deleteFront, deleteBack 
    *************************************************************************/
    public void addFront(Value val) {
	add(0, val);
    }

    public void addBack(Value val) {
	add(size(), val);
    }

    public Value deleteFront() {
	Value v = get(0);
	delete(0);
	return v;
    }

    public Value deleteBack() {
	Value v = get(size() - 1);
	delete(size() - 1);
	return v;
    }

    /*************************************************************************
    *  red-black tree helper functions
    *************************************************************************/

    // make a left-leaning link lean to the right
    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //     || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


   /*************************************************************************
    *  Utility functions
    *************************************************************************/

    // height of tree (1-node tree has height 0)
    public int height() { return height(root); }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

   /*************************************************************************
    *  Ordered symbol table methods.
    *************************************************************************/

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) { 
        // assert x != null;
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) { 
        // assert x != null;
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 

    // the key of rank k
    public Value select(int k) {
        if (k < 0 || k >= size()) return null;
        Node x = select(root, k);
        return x.val;
    }

    // the key of rank k in the subtree rooted at x
    private Node select(Node x, int k) {
        // assert x != null;
        // assert k >= 0 && k < size(x);
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 

   /***********************************************************************
    *  Range count and range search.
    ***********************************************************************/

    // all of the list, as an Iterator
    public Iterator<Value> iterator() {
        Queue<Value> queue = new Queue<Value>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        values(root, queue, 0, size() - 1);
	return queue.iterator();
    }

    // all of the list, as an Iterable
    public Iterable<Value> values() {
        return values(0, size() - 1);
    }

    // the values with rank between lo and hi, as an Iterable
    public Iterable<Value> values(int lo, int hi) {
        Queue<Value> queue = new Queue<Value>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        values(root, queue, lo, hi);
        return queue;
    } 

    // add the values with rank between lo and hi in the subtree rooted at x
    // to the queue
    private void values(Node x, Queue<Value> queue, int lo, int hi) { 
        if (x == null) return;
	int k = size(x.left);
	if (lo < k) values(x.left, queue, lo, hi); 
        if (lo <= k && k <= hi) queue.enqueue(x.val); 
        if (hi > k) values(x.right, queue, lo, hi); 
    } 

    // number values between lo and hi
    public int size(int lo, int hi) {
        if (lo > hi) return 0;
	return hi - lo + 1;
    }


   /*************************************************************************
    *  Check integrity of red-black BST data structure
    *************************************************************************/
    private boolean check() {

        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");

        if (!is23())             StdOut.println("Not a 2-3 tree");
        if (!isBalanced())       StdOut.println("Not balanced");
        return isSizeConsistent() && is23() && isBalanced();
    }

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    } 

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }
    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    } 

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() { 
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    } 


   /*****************************************************************************
    *  Test client
    *****************************************************************************/
    public static void main(String[] args) { 
        List<String> l = new List<String>();
	while (!StdIn.isEmpty()) {
	    String op = StdIn.readString();
	    if (op.equals("+")) {
		int i = StdIn.readInt();
		String val = StdIn.readString();
		l.add(i, val);
	    } else if (op.equals("-")) {
		int i = StdIn.readInt();
		l.delete(i);
	    } else {
		StdOut.println("Current:");
		int j = 0;
		for (String s : l)
		    StdOut.println(j++ + ": " + s);
	    }
	    // l.check();
        }
    }
}
