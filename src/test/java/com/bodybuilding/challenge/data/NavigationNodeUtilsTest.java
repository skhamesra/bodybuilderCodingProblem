package com.bodybuilding.challenge.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.bodybuilding.challenge.data.NavigationUtil.loadNavigationTree;
import static com.bodybuilding.challenge.data.NavigationUtil.pruneTree;
import static org.junit.Assert.*;

public class NavigationNodeUtilsTest {

    private static NavigationNode root;

    @BeforeClass
    public static void setup() throws IOException {
        root = loadNavigationTree();
    }


    @Test
    public void testPruneTree() throws Exception {


        NavigationNode node = pruneTree(root, "30dayweek1");
        // calculate number of nodes with children
        int childCount = 0;
        for (NavigationNode child : node.getChildren()) {
            assertNotNull(child);
            assertNotNull(child.getChildren());
            if(!child.getChildren().isEmpty()) {
                childCount++;
            }
        }

        assertEquals("Only one node should have children", 1, childCount);

        ObjectMapper om = new ObjectMapper();
        NavigationNode correctRoot = om.readValue(new ClassPathResource("test1.json").getInputStream(), NavigationNode.class);
        fixParents(correctRoot);

        assertEquals("Node should match tree in test1.json", correctRoot, node);
    }

    @Test
    public void testPruneTree_topLevel() throws Exception {

        NavigationNode node = pruneTree(root, "Training_Main");
        // no nodes should have children (other than "root")
        for (NavigationNode child : node.getChildren()) {
            assertNotNull(child);
            assertNotNull(child.getChildren());
            assertTrue("Top level shouldn't have children", child.getChildren().isEmpty());
        }

        ObjectMapper om = new ObjectMapper();
        NavigationNode correctRoot = om.readValue(new ClassPathResource("test2.json").getInputStream(), NavigationNode.class);
        fixParents(correctRoot);

        assertEquals("Node should match tree in test2.json", correctRoot, node);
    }

    @Test
    public void testPruneTree_root() throws Exception {

        NavigationNode node = pruneTree(root, "root");
        assertNotNull(node);
        assertFalse(node.getChildren().isEmpty());
        for (NavigationNode child : node.getChildren()) {
            assertTrue(child.getChildren().isEmpty());
        }
    }

    @Test
    public void testPruneTree_nonExistent() throws Exception {

        NavigationNode node = pruneTree(root, "I-Do-Not-Exist");
        assertNull("Result should be null if not found", node);
    }

    private void fixParents(NavigationNode root) {
        for (NavigationNode child : root.getChildren()) {
            child.parent = root;
            fixParents(child);;
        }
    }
}