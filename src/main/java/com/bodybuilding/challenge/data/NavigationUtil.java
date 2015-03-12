package com.bodybuilding.challenge.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class NavigationUtil {
    /**
     * Sets the parent field on an immutable {@link com.bodybuilding.challenge.data.NavigationNode}
     * @param child Child node
     * @param parent Parent node
     */
    static void setParent(NavigationNode child, NavigationNode parent) {
        child.parent = parent;
    }

    /**
     * Adds a child to an immutable NavigationNode
     * @param child child to add
     * @param parent parent node
     */
    static void addChild(NavigationNode child, NavigationNode parent) {
        if(child != null) {
            List<NavigationNode> children = new ArrayList<>(parent.children);
            children.add(child);
            parent.children = ImmutableList.copyOf(children);
        }
    }

    /**
     * Converts a {@link com.bodybuilding.challenge.data.MutableNavigationNode} to an immutable {@link com.bodybuilding.challenge.data.NavigationNode}
     * @param node mutable node
     * @param taxParent the parent node to be used for the new {@link com.bodybuilding.challenge.data.NavigationNode}
     * @return converted node
     */
    static NavigationNode toNavigationNode(MutableNavigationNode node, NavigationNode taxParent){
        NavigationNode taxNode = new NavigationNode(node.id, node.name, node.url);
        setParent(taxNode, taxParent);

        for (MutableNavigationNode child: node.children){
            NavigationNode taxChild = toNavigationNode(child, taxNode);
            addChild(taxChild, taxNode);
        }

        return taxNode;
    }

    /**
     * Converts an immutable {@link com.bodybuilding.challenge.data.NavigationNode} to a {@link com.bodybuilding.challenge.data.MutableNavigationNode}
     * @param node {@link NavigationNode} to convert
     * @return {@link com.bodybuilding.challenge.data.MutableNavigationNode} that corresponds to the supplied node
     */
    static MutableNavigationNode fromNavigationNode(NavigationNode node){
        MutableNavigationNode mutableRoot = new MutableNavigationNode(null, node.id, node.name, node.url, null);
        for (NavigationNode child : node.getChildren()) {
            MutableNavigationNode newChild = fromNavigationNode(child);
            mutableRoot.children.add(newChild);
            newChild.setParent(mutableRoot);
        }

        return mutableRoot;
    }


    /**
     * Loads the navigation tree from navigation.json
     * @return Root navigation node
     * @throws IOException
     */
    static NavigationNode loadNavigationTree() throws IOException {
        ObjectMapper om = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("navigation.json");

        String json = Files.toString(resource.getFile(), Charsets.UTF_8);

        JsonNode rootJson = om.readTree(json);

        MutableNavigationNode mutableNavigationNode = convert(rootJson);
        return NavigationUtil.toNavigationNode(mutableNavigationNode, null);
    }

    /**
     * Converts a JSON node to a {@link MutableNavigationNode MutableNavigationNode}
     * @param json json node to convert
     * @return  MutableNavigationNode for the supplied json
     */
    static MutableNavigationNode convert(JsonNode json){
        MutableNavigationNode navigationNode = new MutableNavigationNode();
        navigationNode.setName(json.get("name").asText());
        navigationNode.setId(json.get("id").asText());
        navigationNode.setUrl(json.get("url").asText());

        JsonNode children = json.get("children");
        if (children != null && children.isArray()) {
            for (JsonNode childJson : children) {
                MutableNavigationNode child = convert(childJson);
                navigationNode.getChildren().add(child);
                child.setParent(navigationNode);
            }
        }

        return navigationNode;
    }

    /**
     * This returns a tree that has the full path to the node with the supplied id, plus the root node and its
     * direct children
     * @param root root of the navigation tree
     * @return root of new tree or null if node with id is not found
     */
    // TODO, implement this method
    static NavigationNode pruneTree(NavigationNode root, String id) {
        return null;
    }
}
