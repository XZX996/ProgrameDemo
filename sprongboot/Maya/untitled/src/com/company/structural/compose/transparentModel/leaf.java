package com.company.structural.compose.transparentModel;

/***
 * 子节点2
 */
public class leaf implements Component {

    public String name;
    public leaf(String name)
    {
        this.name=name;
    }
    @Override
    public void add(Component c) {

    }

    @Override
    public void remove(Component c) {
    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println("树叶"+name+"：被访问！");
    }
}
