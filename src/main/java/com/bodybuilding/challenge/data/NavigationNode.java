package com.bodybuilding.challenge.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Immutable NavigationNode
 */
public class NavigationNode {

    // these are package private so they can be mutated within this package. Once these leave
    // the com.bodybuilding.challenge.data package they should never change
    NavigationNode parent;

    String id;

    String name;

    String url;

    List<NavigationNode> children;

    public NavigationNode(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.children = ImmutableList.of();
    }

    @JsonCreator
    public NavigationNode(@JsonProperty("id") String id,
                          @JsonProperty("name") String name,
                          @JsonProperty("url") String url,
                          @JsonProperty("children") List<NavigationNode> children) {
        this.parent = parent;
        this.id = id;
        this.name = name;
        this.url = url;
        this.children = ImmutableList.copyOf(children);
    }

    @JsonIgnore
    public NavigationNode getParent() {
        return parent;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<NavigationNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("url", url)
                .add("children", children)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NavigationNode that = (NavigationNode) o;

        if (children != null ? !children.equals(that.children) : that.children != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parent != null ? !parent.id.equals(that.parent.id) : that.parent != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.id.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }
}
