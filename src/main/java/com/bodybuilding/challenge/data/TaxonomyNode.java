package com.bodybuilding.challenge.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class TaxonomyNode {

    TaxonomyNode parent;

    String selector;

    String name;

    String url;

    List<TaxonomyNode> children;

    public TaxonomyNode(String selector, String name, String url) {
        this.selector = selector;
        this.name = name;
        this.url = url;
        this.children = ImmutableList.of();
    }

    @JsonIgnore
    public TaxonomyNode getParent() {
        return parent;
    }

    public String getSelector() {
        return selector;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<TaxonomyNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("selector", selector)
                .add("name", name)
                .add("url", url)
                .add("children", children)
                .toString();
    }
}
