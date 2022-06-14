public class Driver {
    public static void BinaryToBinarySearch()
    {
        // Create example data and show print first and second stuation

        Node<Integer> root = new Node<Integer>(0);
        Node<Integer> n11 = new Node<Integer>(0);
        Node<Integer> n12 = new Node<Integer>(0);
        Node<Integer> n21 = new Node<Integer>(0);
        Node<Integer> n22 = new Node<Integer>(0);
        Node<Integer> n24 = new Node<Integer>(0);
        Node<Integer> n31 = new Node<Integer>(0);
        Node<Integer> n32 = new Node<Integer>(0);
        Node<Integer> n33 = new Node<Integer>(0);

        root.left = n11;
        root.right = n12;

        n11.left = n21;
        n11.right = n22;
        n12.right = n24;

        n21.left = n31;
        n21.right = n32;
        n22.left = n33;

        Integer[] arr = {1,2,3,4,5,6,7,8,9};
        BinaryTreeToBST<Integer> newSort = new BinaryTreeToBST<Integer>();

        newSort.printTree(root);
        newSort.binaryTreeToBST(root, arr);
        newSort.printTree();
    }

    public static void BinarySearchToAVL() {
        // Create example data and show print first and second stuation

        BinarySearchTreeToAVL<Integer> bst = new BinarySearchTreeToAVL<Integer>();
        
        Node<Integer> root = new Node<Integer>(20);
        Node<Integer> n11 = new Node<Integer>(10);
        Node<Integer> n12 = new Node<Integer>(40);
        Node<Integer> n21 = new Node<Integer>(5);
        Node<Integer> n22 = new Node<Integer>(15);
        Node<Integer> n32 = new Node<Integer>(7);

        bst.root = root;
        bst.root.left = n11;

        n11.left = n21;
        n11.right = n22;

        n21.right = n32;
        n22.left = n12;

        bst.printTree();
        bst.balanceTheBST();
        bst.printTree();
    }
}
