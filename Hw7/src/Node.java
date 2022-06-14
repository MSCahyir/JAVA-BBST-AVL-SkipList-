
// Create a node to use in 3 class at same time
public class Node<T> {

    Node<T> left, right, parent;
    T value;
    int size;

    public Node(T value) {
        this.value = value;
        this.left = this.right = parent = null;
        size++;
    }
}