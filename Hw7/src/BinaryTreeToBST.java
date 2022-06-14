import java.util.*;

public class BinaryTreeToBST<T extends Comparable<T>> implements PrintNode {

    static int index;
    Node<T> root;

    // Order the array
    <T> void order(Node<T> node, T newArr[]) {
        // if node equals null
        if (node == null)
            return;

        // Order the left tree
        order(node.left, newArr);

        // Copy the root
        node.value = newArr[index];
        index++;

        // Order the right tree
        order(node.right, newArr);
    }

    <T> void arrayToBST(T[] arr, Node<T> root) {
        // if node equals null
        if (root == null)
            return;

        // Store the left tree on array
        arrayToBST(arr, root.left);

        // Copy the root
        root.value = arr[index];
        index++;

        // Store the right tree on array
        arrayToBST(arr, root.right);
    }

    // This function converts a given Binary Tree to BST
    void binaryTreeToBST(Node<T> root, T arr[]) {
        // if node equals null
        if (root == null)
            return;

        order(root, arr);

        // Sorting the array
        Arrays.sort(arr);

        // Copy array elements back to Binary Tree
        index = 0;
        arrayToBST(arr, root);
        this.root = root;
    }

    // Print the Tree
    public void printTree() {
        PrintNode.printNode(root);
    }

    public void printTree(Node<T> root) {
        PrintNode.printNode(root);
    }
}