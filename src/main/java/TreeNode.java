import java.util.List;

class TreeNode {
    public String name;
    public List<TreeNode> child;

    public TreeNode(String name, List<TreeNode> child) {
        this.name = name;
        this.child = child;
    }

/*    public static TreeNode findNode(TreeNode node, String name) {
        TreeNode resultNode = null;
        for (TreeNode child : node.child) {
            if (child.name.equals(name)) {
                resultNode = child;
                break;
            }
        }
        return resultNode;
    }*/

    public static TreeNode findNode(TreeNode node, String name) {
        if(node == null) return null;
        if(node.name.equals(name)) return node;

        if(node.child != null) {
            for(TreeNode child : node.child) {
                TreeNode resultNode = findNode(child, name);
                if(resultNode != null) return resultNode;
            }
        }

        return null;
    }
}