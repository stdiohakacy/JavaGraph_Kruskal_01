package Kruskal._01;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
	private int nodeCount = 0;
	private int setCount = 0;
	private List<Node> rootNodes;

	public DisjointSet(List<Vertex> vertexs) {
		this.rootNodes = new ArrayList<>(vertexs.size());
		makeSets(vertexs);
	}

	private void makeSets(List<Vertex> vertexs) {
		for (Vertex vertex : vertexs) {
			makeSet(vertex);
		}
	}

	private void makeSet(Vertex vertex) {
		Node node = new Node(0, rootNodes.size(), null);
		vertex.setNode(node);
		this.rootNodes.add(node);
		this.setCount++;
		this.nodeCount++;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public int getSetCount() {
		return setCount;
	}

	public void setSetCount(int setCount) {
		this.setCount = setCount;
	}

	public int find(Node node) {
		Node curNode = node;
		while (curNode.getParent() != null) {
			curNode = curNode.getParent();
		}

		Node root = curNode;
		curNode = node;

		while (curNode != root) {
			Node temp = curNode.getParent();
			curNode.setParent(root);
			curNode = temp;
		}
		return root.getId();
	}

	public void union(Node node1, Node node2) {
		int index1 = find(node1);
		int index2 = find(node2);

		if (index1 == index2)
			return;

		Node root1 = this.rootNodes.get(index1);
		Node root2 = this.rootNodes.get(index2);

		if (root1.getRank() < root2.getRank()) {
			root1.setParent(root2);
		} else if (root1.getRank() > root2.getRank()) {
			root2.setParent(root1);
		} else {
			root2.setParent(root1);
			root1.setRank(root1.getRank() + 1);
		}
		this.setCount--;
	}

}
