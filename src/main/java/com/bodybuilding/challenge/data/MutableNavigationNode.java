package com.bodybuilding.challenge.data;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * A mutable version of {@link com.bodybuilding.challenge.data.NavigationNode}
 */
class MutableNavigationNode {
    MutableNavigationNode parent;

    String id;

    String name;

    String url;

    List<MutableNavigationNode> children = Lists.newArrayList();

    public MutableNavigationNode() {
    }

    public MutableNavigationNode(MutableNavigationNode parent, String id, String name, String url, List<MutableNavigationNode> children) {
        this.parent = parent;
        this.id = id;
        this.name = name;
        this.url = url;
        this.children = MoreObjects.firstNonNull(children, Lists.<MutableNavigationNode>newArrayList());
    }

    public MutableNavigationNode getParent() {
        return parent;
    }

    public void setParent(MutableNavigationNode parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<MutableNavigationNode> getChildren() {
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

        MutableNavigationNode that = (MutableNavigationNode) o;

        if (children != null ? !children.equals(that.children) : that.children != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }
}
