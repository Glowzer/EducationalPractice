import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author TimoshenkoStanislav
 */
public class TreeModel extends DefaultTreeModel {

    private TreeNode root;

    public TreeModel(TreeNode r) {
        super(r);
        root = r;
    }

    @Override
    public TreeNode getRoot() {
        return root;
    }

    public void insertNodeInto(TreeNode child, TreeNode parent, int i, boolean flag) {
        this.insertNodeInto(child, parent, i);
        parent.addNode(child);
    }
}
