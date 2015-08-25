package cn.itcast.bbs.utils.tree;

import java.util.Collection;

public interface TreeNode {

	String getName();

	void setName(String name);

	TreeNode getParent();

	Collection<? extends TreeNode> getChildren();

	/**
	 * 克隆子节点
	 * 
	 * @return
	 */
	TreeNode deepClone();

	int hashCode();

	boolean equals(Object obj);
}
