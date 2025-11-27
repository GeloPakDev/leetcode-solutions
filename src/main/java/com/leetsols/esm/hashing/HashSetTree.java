package com.leetsols.esm.hashing;

/*
 * Problem type: Array, Hash Table, Linked List, Design, Hash Function
 * Number: 705 Design HashSet
 * Implementation type using Binary Search Tree
 */
public class HashSetTree {
    private final Bucket[] bucketArray;
    private final int keyRange;

    public HashSetTree() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < this.keyRange; ++i)
            this.bucketArray[i] = new Bucket();
    }

    protected int _hash(int key) {
        return (key % this.keyRange);
    }

    public void add(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    public boolean contains(int key) {
        int bucketIndex = this._hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }

    static class Bucket {
        private final BSTree tree;

        public Bucket() {
            tree = new BSTree();
        }

        public void insert(Integer key) {
            this.tree.root = this.tree.insertIntoBST(this.tree.root, key);
        }

        public void delete(Integer key) {
            this.tree.root = this.tree.deleteNode(this.tree.root, key);
        }

        public boolean exists(Integer key) {
            TreeNode root = this.tree.searchBST(this.tree.root, key);
            return (root != null);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static class BSTree {
        TreeNode root = null;

        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null || val == root.val)
                return root;

            return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
        }

        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }

            /*
             * - Insert to the right subtree
             * - Root value equals to the insertable one
             * - Insert to the left subtree
             */
            if (val > root.val) {
                root.right = insertIntoBST(root.right, val);
            } else if (val == root.val) {
                return root;
            } else {
                root.left = insertIntoBST(root.left, val);
            }
            return root;
        }

        public int successor(TreeNode root) {
            root = root.right;
            while (root.left != null)
                root = root.left;
            return root.val;
        }

        public int predecessor(TreeNode root) {
            root = root.left;
            while (root.right != null)
                root = root.right;
            return root.val;
        }

        public TreeNode deleteNode(TreeNode root, int val) {
            if (root == null)
                return null;

            // Delete from the RIGHT subtree
            if (val > root.val)
                root.right = deleteNode(root.right, val);
                // Delete from the LEFT subtree
            else if (val < root.val)
                root.left = deleteNode(root.left, val);

            else {
                // The node is a LEAF
                if (root.left == null && root.right == null)
                    root = null;
                    // The node is not a LEAF and has a right child
                else if (root.right != null) {
                    root.val = successor(root);
                    root.right = deleteNode(root.right, root.val);
                }
                // The node is not a leaf, node has no right child, and has a left child
                else {
                    root.val = predecessor(root);
                    root.left = deleteNode(root.left, root.val);
                }
            }
            return root;
        }
    }
}
