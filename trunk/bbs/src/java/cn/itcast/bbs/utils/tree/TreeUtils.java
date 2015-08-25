package cn.itcast.bbs.utils.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.bbs.entities.privilege.Role;

public class TreeUtils {

	/**
	 * 从treeNode所属的树中把treeNode移除掉
	 * 
	 * @param treeNode
	 */
	public static void removeTreeNodeAndChildrenFromAllTreeNodes(Collection<? extends TreeNode> allNodes, TreeNode treeNode) {
		allNodes.remove(treeNode);
		for (TreeNode tn : treeNode.getChildren()) {
			removeTreeNodeAndChildrenFromAllTreeNodes(allNodes, tn);
		}
	}

	/**
	 * 拷贝所有的节点信息（不包含子节点）后，再把树中所有的节点的名字加上对应的前缀，以便以文本遍历显示所有节点的名字时，显示成树形；<br>
	 * 最后返回这个拷贝。 <br>
	 * 
	 * 显示成树形的效果如下：
	 * 
	 * <pre>
	 * 1，遍历显示树中所有节点的名字（原始）：
	 * 传智播客
	 * 汤阳光
	 * Hibernate
	 * Jbpm
	 * Lucene
	 * 黎老师
	 * Struts2
	 * Spring
	 * 
	 * 2，调用此方法修改名字后，再遍历显示树中所有节点的名字：
	 * |--传智播客
	 * |   |--汤阳光
	 * |   |   |--Hibernate
	 * |   |   |--Jbpm
	 * |   |   |--Lucene
	 * |   |--黎老师
	 * |   |   |--Struts
	 * |   |   |--Struts2
	 * |   |   |--Spring
	 * </pre>
	 * 
	 * @param sameLevelNodes
	 */
	public static List<? extends TreeNode> shallowCopyAndChangeTreeNodeNameForTreeTextView(List<? extends TreeNode> sameLevelNodes) {
		List<TreeNode> copy = new ArrayList<TreeNode>(sameLevelNodes.size());
		for (TreeNode treeNode : sameLevelNodes) {
			copy.add(treeNode.deepClone());
		}

		changeTreeNodeNameForTreeTextView(copy, "|");

		List<TreeNode> allNodes = new ArrayList<TreeNode>();
		addToListWithAllChildren(copy, allNodes);
		return allNodes;
	}

	private static void addToListWithAllChildren(Collection<? extends TreeNode> sameLevelNodes, List<TreeNode> allNodes) {
		for (TreeNode tn : sameLevelNodes) {
			allNodes.add(tn);
			addToListWithAllChildren(tn.getChildren(), allNodes);
		}
	}

	// public static Collection<? extends TreeNode> deepCopyAndChangeTreeNodeNameForTreeTextView(Collection<? extends TreeNode> sameLevelNodes) {
	// Collection<TreeNode> copy = new ArrayList<TreeNode>(sameLevelNodes.size());
	// for (TreeNode treeNode : sameLevelNodes) {
	// try {
	// copy.add(treeNode.deepClone());
	// } catch (DeepCloneNotSupportedException e) {
	// throw new ItcastException(e);
	// }
	// }
	//
	// changeTreeNodeNameForTreeTextView(copy, "|");
	// return copy;
	// }

	private static void changeTreeNodeNameForTreeTextView(Collection<? extends TreeNode> sameLevelNodes, String indent) {
		for (TreeNode treeNode : sameLevelNodes) {
			String newName = indent + "--" + treeNode.getName();
			treeNode.setName(newName);
			changeTreeNodeNameForTreeTextView(treeNode.getChildren(), indent + "　　|"); // 全角的空格，要不然在html中只显示一个半角空格
		}
	}

	// ---------

	/**
	 * 打印节点的信息（包含子节点）
	 * 
	 * @param treeNode
	 */
	static void printTreeNodeInfo(TreeNode treeNode) {
		System.out.println(treeNode.getName());
		for (TreeNode tn : treeNode.getChildren()) {
			printTreeNodeInfo(tn);
		}
	}

	public static void main(String[] args) {
		Role tree = new Role(1, "传智播客");
		Role n1 = new Role(2, "汤阳光");
		Role n2 = new Role(3, "黎老师");
		tree.getChildren().add(n1);
		tree.getChildren().add(n2);

		Role n11 = new Role(11, "Hibernate");
		Role n12 = new Role(12, "Lucene");
		Role n13 = new Role(13, "Jbpm");
		n1.getChildren().add(n11);
		n1.getChildren().add(n12);
		n1.getChildren().add(n13);

		Role n21 = new Role(21, "Spring");
		Role n22 = new Role(22, "Struts");
		Role n23 = new Role(23, "Struts2");
		n2.getChildren().add(n21);
		n2.getChildren().add(n22);
		n2.getChildren().add(n23);

		List<Role> topLevelNodes = new ArrayList<Role>();
		topLevelNodes.add(tree);
		topLevelNodes.add(new Role(100, "itcast"));

		// System.out.println(treeViewText(topLevelNodes));
		Collection<? extends TreeNode> nodes = shallowCopyAndChangeTreeNodeNameForTreeTextView(topLevelNodes);
		printTreeNodeInfo(tree);
		// printTreeNodeInfo(nodes.iterator().next());
		// System.out.println(nodes);

		// Role r = tree.deepClone();
		// printTreeNodeInfo(r);
	}
}
