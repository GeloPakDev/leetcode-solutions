package com.leetsols.esm.graphs;

import com.leetsols.esm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Tree, Depth-First Search, Breadth-First Search, Binary Tree
 * Number: 112. Path Sum
 */
public class PathSum {
    int target;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        target = targetSum;
        return checkSum(root, 0);
    }

    /*
     * Algorithm:
     * - Find out the path which sums to the target
     * - As this is the binary tree as soon as we reach the leaf node,
     *   check its children, if the node does not have them, check the
     *   total sum resulted in this path.
     * - Do it recursively for left and right subtrees.
     */
    public boolean checkSum(TreeNode root, int currSum) {
        if (root == null) return false;

        if (root.right == null || root.left == null) {
            return (currSum + root.val) == target;
        }

        currSum += root.val;
        boolean left = checkSum(root.left, currSum);
        boolean right = checkSum(root.right, currSum);
        return left || right;
    }

    /*
     * PathSum 2
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> pathsList = new ArrayList<>();
        List<Integer> pathNodes = new ArrayList<>();
        recurseTree(root, targetSum, pathNodes, pathsList);
        return pathsList;
    }

    /*
     * Algorithm:
     * - Preserve current list to add the numbers in the result
     * - Preserve current sum <= targetSum
     * - Prune the path if the currSum > targetSum
     * - If we reached the leaf and currSum != targetSum -> add to the result
     * - Else remove the last node from the currentList and currSum
     * - In order not to observe the whole path, check when currSum > targetSum
     */
    public void recurseTree(TreeNode root, int remainingSum, List<Integer> pathNodes, List<List<Integer>> pathsList) {
        if (root == null) return;

        // Add the current's node to the path's list
        pathNodes.add(root.val);

        if (remainingSum == root.val && root.left == null && root.right == null) {
            pathsList.add(List.copyOf(pathNodes));
        } else {
            recurseTree(root.left, remainingSum - root.val, pathNodes, pathsList);
            recurseTree(root.right, remainingSum - root.val, pathNodes, pathsList);
        }
        pathNodes.removeLast();
    }

    public int pathSumThree(TreeNode root, int targetSum) {
        return 0;
    }
}
