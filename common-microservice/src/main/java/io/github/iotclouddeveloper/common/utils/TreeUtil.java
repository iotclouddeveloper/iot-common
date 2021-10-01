package io.github.iotclouddeveloper.common.utils;

import io.github.iotclouddeveloper.common.struct.*;
import io.github.iotclouddeveloper.common.struct.BaseTreeGrid;

import java.util.*;

public class TreeUtil
{
    public static <T extends BaseTreeGrid> List<T> formatTree(final List<T> list) {
        final List<T> nodeList = new ArrayList<T>();
        for (final T node1 : list) {
            node1.setChild(new ArrayList<BaseTreeGrid<Object>>());
            if (node1.getParent().equals(node1.getRootIdentity())) {
                nodeList.add(node1);
            }
            for (final T node2 : list) {
                if (node2.getParent().equals(node1.getIdentity())) {
                    node1.getChild().add(node2);
                }
            }
        }
        return nodeList;
    }
    
    public static <T extends BaseTreeGrid> List<T> flat(final List<T> list) {
        final List<T> result = new ArrayList<T>();
        for (final T grid : list) {
            result.add(grid);
            if (grid.getChild().size() > 0) {
                result.addAll((Collection<? extends T>)flat(grid.getChild()));
                grid.setChild(null);
            }
        }
        return result;
    }
}
