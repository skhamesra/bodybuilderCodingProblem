package com.bodybuilding.challenge.data;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

class MutableTaxonomyNode {
    MutableTaxonomyNode parent;

    String selector;

    String name;

    String url;

    List<MutableTaxonomyNode> children = Lists.newArrayList();

    public MutableTaxonomyNode getParent() {
        return parent;
    }

    public void setParent(MutableTaxonomyNode parent) {
        this.parent = parent;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MutableTaxonomyNode> getChildren() {
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
