package io.github.iotclouddeveloper.common.struct;

import com.fasterxml.jackson.annotation.*;
import java.util.*;

public abstract class BaseTreeGrid<T>
{
    List<BaseTreeGrid<T>> child;
    @JsonIgnore
    public T parent;
    @JsonIgnore
    public T identity;
    @JsonIgnore
    public T rootIdentity;
    
    public BaseTreeGrid() {
        this.child = new ArrayList<BaseTreeGrid<T>>();
    }
    
    public List<BaseTreeGrid<T>> getChild() {
        return this.child;
    }
    
    public T getParent() {
        return this.parent;
    }
    
    public T getIdentity() {
        return this.identity;
    }
    
    public T getRootIdentity() {
        return this.rootIdentity;
    }
    
    public void setChild(final List<BaseTreeGrid<T>> child) {
        this.child = child;
    }
    
    public void setParent(final T parent) {
        this.parent = parent;
    }
    
    public void setIdentity(final T identity) {
        this.identity = identity;
    }
    
    public void setRootIdentity(final T rootIdentity) {
        this.rootIdentity = rootIdentity;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseTreeGrid)) {
            return false;
        }
        final BaseTreeGrid<?> other = (BaseTreeGrid<?>)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$child = this.getChild();
        final Object other$child = other.getChild();
        Label_0065: {
            if (this$child == null) {
                if (other$child == null) {
                    break Label_0065;
                }
            }
            else if (this$child.equals(other$child)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$parent = this.getParent();
        final Object other$parent = other.getParent();
        Label_0102: {
            if (this$parent == null) {
                if (other$parent == null) {
                    break Label_0102;
                }
            }
            else if (this$parent.equals(other$parent)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$identity = this.getIdentity();
        final Object other$identity = other.getIdentity();
        Label_0139: {
            if (this$identity == null) {
                if (other$identity == null) {
                    break Label_0139;
                }
            }
            else if (this$identity.equals(other$identity)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$rootIdentity = this.getRootIdentity();
        final Object other$rootIdentity = other.getRootIdentity();
        if (this$rootIdentity == null) {
            if (other$rootIdentity == null) {
                return true;
            }
        }
        else if (this$rootIdentity.equals(other$rootIdentity)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof BaseTreeGrid;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $child = this.getChild();
        result = result * 59 + (($child == null) ? 43 : $child.hashCode());
        final Object $parent = this.getParent();
        result = result * 59 + (($parent == null) ? 43 : $parent.hashCode());
        final Object $identity = this.getIdentity();
        result = result * 59 + (($identity == null) ? 43 : $identity.hashCode());
        final Object $rootIdentity = this.getRootIdentity();
        result = result * 59 + (($rootIdentity == null) ? 43 : $rootIdentity.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "BaseTreeGrid(child=" + this.getChild() + ", parent=" + this.getParent() + ", identity=" + this.getIdentity() + ", rootIdentity=" + this.getRootIdentity() + ")";
    }
}
