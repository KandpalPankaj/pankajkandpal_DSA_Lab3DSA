package lab3DSA;

class Node {
    int val;
    Node left, right, parent;

    public Node(int val) {
        this.val = val;
        left = right = parent = null;
    }
}


public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }

        if (val < root.val) {
            Node leftChild = insertRec(root.left, val);
            root.left = leftChild;
            leftChild.parent = root;
        } else if (val > root.val) {
            Node rightChild = insertRec(root.right, val);
            root.right = rightChild;
            rightChild.parent = root;
        }

        return root;
    }


    public void findPair(Node root, int targetSum) {
        if (root == null)
            return;

        Node left = root, right = root;
        while (left.left != null)
            left = left.left;
        while (right.right != null)
            right = right.right;

        while (left != right && right.val > left.val) {
            int sum = left.val + right.val;
            if (sum == targetSum) {
                System.out.println("Pair is (" + left.val + ", " + right.val + ")");
                return;
            }

            if (sum < targetSum)
                left = inorderSuccessor(left);
            else
                right = inorderPredecessor(right);
        }

        System.out.println("nodes are not found.");
    }

    private Node inorderSuccessor(Node root) {
        if (root.right != null) {
            root = root.right;
            while (root.left != null)
                root = root.left;
            return root;
        }

        Node parent = root.parent;
        while (parent != null && root == parent.right) {
            root = parent;
            parent = parent.parent;
        }

        return parent;
    }

    private Node inorderPredecessor(Node root) {
        if (root.left != null) {
            root = root.left;
            while (root.right != null)
                root = root.right;
            return root;
        }

        Node parent = root.parent;
        while (parent != null && root == parent.left) {
            root = parent;
            parent = parent.parent;
        }

        return parent;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(40);
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(60);
        tree.insert(50);
        tree.insert(70);
        int targetSum = 80;
        tree.findPair(tree.root, targetSum);
    }
}

