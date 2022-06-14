import java.util.Iterator;

// BST with Comparable and Iterable 
public class BinarySearchTreeToAVL<E extends Comparable<E>> implements Iterable<E>, PrintNode {
    Node<E> root;
    int total;

    // Constructures
    public BinarySearchTreeToAVL() {
        root = null;
    }

    public BinarySearchTreeToAVL(E value) {
        this.root = new Node(value);
    }

    // Find the max depth the BST
    int maxDepth(Node<E> node) {
        if (node == null)
            return -1;
        else {
            // Find left and right depth and select bigger
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    // Select which one max
    int max(int one, int two) {
        return (one > two) ? one : two;
    }

    // Balance the tree 
    public void blance() {
        int rootLeftSize = 0;
        int rootRightSize = 0;

        do {
            // Find the left and right side depth and if more than 1 rotate right or left 
            if (root.left != null)
                rootLeftSize = maxDepth(root.left) + 1;

            if (root.right != null)
                rootRightSize = maxDepth(root.right) + 1;

            if ((rootLeftSize - rootRightSize) > 1)
                rotateRight();

            else if ((rootLeftSize - rootRightSize) < -1)
                rotateLeft();

            // Rotate while |Left-Right| <= 1
        } while (!(((rootLeftSize - rootRightSize) == -1) || ((rootLeftSize - rootRightSize) == 1)));
    }

    // Balance helper function (for recursive) 
    private void blance(Node<E> root, int rootLeftSize, int rootRightSize) {
        if (((rootLeftSize - rootRightSize) == -1) || ((rootLeftSize - rootRightSize) == 1))
            return;

        if ((rootLeftSize - rootRightSize) > 1) {
            rotateRight();
            blance(root, rootLeftSize - 1, rootRightSize + 1);
        }

        else if ((rootLeftSize - rootRightSize) < -1) {
            rotateLeft();
            blance(root, rootLeftSize + 1, rootRightSize - 1);
        }
    }

    // For user call the balance 
    public void balanceTheBST() {
        blance(root, maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    // Rotate the right side 
    public void rotateRight() {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;

        root.size = max(maxDepth(root.left), maxDepth(root.right)) + 1;
        temp.size = max(maxDepth(temp.left), maxDepth(temp.right)) + 1;
        root = temp;
    }

    // Rotate the left side
    public void rotateLeft() {
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;

        root.size = max(maxDepth(root.left), maxDepth(root.right)) + 1;
        temp.size = max(maxDepth(temp.left), maxDepth(temp.right)) + 1;
        root = temp;
    }

    // Insert to node
    private Node<E> insertData(Node<E> root, Node<E> parent, E value) {
        if (root == null) {
            this.root = new Node(value);
            this.root.parent = parent;
            return this.root;
        } else {
            if (value.compareTo(root.value) < 0)
                root.left = insertData(root.left, root, value);
            else
                root.right = insertData(root.right, root, value);

            return root;
        }
    }

    // Inserts for user
    public void insertToLeft(E value) {
        root = insertData(root, root, value);
        this.total++;
    }

    public void insertToRight(E value) {
        root = insertData(root, root, value);
        this.total++;
    }

    // Traverse the node
    private void traverse(Node<E> root) {
        if (root != null) {
            traverse(root.left);
            System.out.println(root.value);
            traverse(root.right);
        }
    }
    
    // Traverse for user
    public void startTraverseWithRoot() {
        traverse(root);
    }

    // Remove from a node for user
    public void remove(E value) {
        root = removeNode(root, value);
        this.total--;
    }

    // Remove from node
    private Node<E> removeNode(Node<E> root, E value) {
        if (root == null)
            return root;
        if (value.compareTo(root.value) < 0)
            root.left = removeNode(root.left, value);
        else if (value.compareTo(root.value) > 0)
            root.right = removeNode(root.right, value);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                root.value = minValue(root.right);
                root.right = removeNode(root.right, root.value);
            }
        }
        return root;
    }

    // Find min value
    private E minValue(Node<E> root) {
        E minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    public int getTotal() {
        return this.total;
    }

    @Override
    public Iterator<E> iterator() {
        BinaryTreeIterator bIter = new BinaryTreeIterator(root);
        return bIter;
    }

    // Iterator for using in the chaning
    private class BinaryTreeIterator implements Iterator<E> {

        private Node<E> node;

        public BinaryTreeIterator(Node<E> node) {
            if (node != null) {
                this.node = smallest(node);
            } else {
                this.node = node;
            }
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        private Node<E> smallest(Node<E> n) {
            if (n.left != null) {
                return smallest(n.left);
            } else {
                return n;
            }
        }

        @Override
        public E next() {
            E result = node.value;
            if (node.right != null) {
                node = smallest(node.right);
            } else {
                while (node.parent != null && node.parent.left != node) {
                    node = node.parent;
                }
                node = node.parent;
            }
            return result;
        }
    }

    // Print the Tree
    public void printTree() {
        PrintNode.printNode(root);
    }

}
