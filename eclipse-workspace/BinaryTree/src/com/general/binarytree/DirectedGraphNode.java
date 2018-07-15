package com.general.binarytree;

import java.util.ArrayList;

public class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;

	public DirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}

	public static void main(String[] args) {

	}

}
