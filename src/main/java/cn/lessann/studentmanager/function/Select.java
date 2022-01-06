package cn.lessann.studentmanager.function;

import java.util.List;

/**
 * 系统选项
 *
 * @author LessAnn
 * @version 1.0
 */
public class Select {

    private Select child;

    private List<String> item;

    private List<String> functionItem;

    private Select parent;

    public Select() {
    }

    public Select(List<String> item) {
        this.item = item;
    }

    public Select getChild() {
        return child;
    }

    public void setChild(Select child) {
        this.child = child;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public Select getParent() {
        return parent;
    }

    public void setParent(Select parent) {
        this.parent = parent;
    }
}
