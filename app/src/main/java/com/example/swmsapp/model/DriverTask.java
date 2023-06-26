package com.example.swmsapp.model;

import java.util.List;

public class DriverTask {

    private Node node;

    private List<Bin>binsOfNode;

    public DriverTask(Node node, List<Bin> binsOfNode) {
        this.node = node;
        this.binsOfNode = binsOfNode;
    }

    public Node getNode() {
        return node;
    }

    public List<Bin> getBinsOfNode() {
        return binsOfNode;
    }
}
