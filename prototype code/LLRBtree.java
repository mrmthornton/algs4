
public class LLRBtree<Key> {

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);
        if         (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else                     h.val = val;
        
        if (isRed(h.right) && !isRed(h.left))        h = rotateLeft(h);
        if (isRed(h.left)   &&   isRed(h.left.left))  h = rotateRight(h);
        if (isRed(h.left)   &&   isRed(h.right))     flipColors(h);
    }

    private class Node {
        Node(Key key, Value value, Color color) {
            
        }
    }
}
