package com.bodybuilding.challenge.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TaxonomyNodeUtils {
    static void setParent(TaxonomyNode child, TaxonomyNode parent) {
        child.parent = parent;
    }

    static void addChild(TaxonomyNode child, TaxonomyNode parent) {
        if(child != null) {
            List<TaxonomyNode> children = new ArrayList<>(parent.children);
            children.add(child);
            parent.children = ImmutableList.copyOf(children);
        }
    }

    static TaxonomyNode toTaxonomyNode(MutableTaxonomyNode node, TaxonomyNode taxParent){
        TaxonomyNode taxNode = new TaxonomyNode(node.selector, node.name, node.url);
        setParent(taxNode, taxParent);

        for (MutableTaxonomyNode child: node.children){
            TaxonomyNode taxChild = toTaxonomyNode(child, taxNode);
            addChild(taxChild, taxNode);
        }

        return taxNode;
    }

    static TaxonomyNode loadTaxonomyTree() throws IOException {
        ObjectMapper om = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("taxonomy.json");

        String json = Files.toString(resource.getFile(), Charsets.UTF_8);

        JsonNode rootJson = om.readTree(json);

        MutableTaxonomyNode mutableTaxonomyNode = convert(rootJson);
        return TaxonomyNodeUtils.toTaxonomyNode(mutableTaxonomyNode, null);
    }

    static MutableTaxonomyNode convert(JsonNode json){
        MutableTaxonomyNode taxonomyNode = new MutableTaxonomyNode();
        taxonomyNode.setName(json.get("name").asText());
        taxonomyNode.setSelector(json.get("selector").asText());
        taxonomyNode.setUrl(json.get("url").asText());

        JsonNode children = json.get("children");
        if (children != null && children.isArray()) {
            for (JsonNode childJson : children) {
                MutableTaxonomyNode child = convert(childJson);
                taxonomyNode.getChildren().add(child);
                child.setParent(taxonomyNode);
            }
        }

        return taxonomyNode;
    }
}
