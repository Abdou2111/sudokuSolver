
import java.util.Iterator;

import org.w3c.dom.Node;

public class LinkedGeneralTree<E> extends AbstractTree<E> {
    protected Node<E> root = null;
    private int size = 0;

    protected Node<E> createNode(E e, Node<E> parent, Node<E>[] children, Node<E> container) {
        return new Node(e, parent, children, this);
    }

    public LinkedGeneralTree() {
    }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node<E> node)) {
            throw new IllegalArgumentException("Invalid position type");
        } else if (node.getContainer() != this) {
            throw new IllegalArgumentException("Invalid position container");
        } else if (node.getParent() == node) {
            throw new IllegalArgumentException("Position has been deleted");
        } else {
            return node;
        }
    }

    protected void setContainer(Position<E> p, Object container) throws IllegalArgumentException {
        Node<E> node = (Node)p;
        node.setContainer(container);
    }

    public int size() {
        return this.size;
    }

    public Position<E> root() {
        return this.root;
    }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        return node.getParent();
    }

    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        return node.getLeft();
    }

    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        return node.getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!this.isEmpty()) {
            throw new IllegalStateException("Tree is not empty");
        } else {
            this.root = this.createNode(e, (Node)null, (Node)null, (Node)null);
            this.size = 1;
            return this.root;
        }
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = this.validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("p already has a left child");
        } else {
            Node<E> child = this.createNode(e, parent, (Node)null, (Node)null);
            parent.setLeft(child);
            ++this.size;
            return child;
        }
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = this.validate(p);
        if (parent.getRight() != null) {
            throw new IllegalArgumentException("p already has a right child");
        } else {
            Node<E> child = this.createNode(e, parent, (Node)null, (Node)null);
            parent.setRight(child);
            ++this.size;
            return child;
        }
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        E tmp = node.getElement();
        node.setElement(e);
        return tmp;
    }


    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        if (this.numChildren(p) == 2) {
            throw new IllegalArgumentException("p has two children");
        } else {
            Node<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();
            if (child != null) {
                child.setParent(node.getParent());
            }

            if (node == this.root) {
                this.root = child;
            } else {
                Node<E> parent = node.getParent();
                if (node == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }

            --this.size;
            E tmp = node.getElement();
            node.setElement((E)null);
            node.setLeft((Node)null);
            node.setRight((Node)null);
            node.setParent(node);
            return tmp;
        }
    }

    public void toExpression(Position<E> p) {
        if (this.left(p) != null) {
            System.out.print("(");
            this.toExpression(this.left(p));
        }

        System.out.print(p.getElement());
        if (this.right(p) != null) {
            this.toExpression(this.right(p));
            System.out.print(")");
        }

    }

    public Iterable<Position<E>> children( Position<E> p ) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        Node<E>[] children = node.getChildren();
        fo
    }


    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private PositionCell<E>[] children;
        private Object container;

        public Node(E e, Node<E> parent, PositionCell[] children, Object container) {
            this.element = e;
            this.parent = parent;
            this.children = children;
            this.container = container;
        }

        public E getElement() {
            return this.element;
        }

        public Node<E> getParent() {
            return this.parent;
        }

        public PositionCell[] getChildren() {
            return this.children;
        }

        public Node<E> getLeft() {
            return this.left;
        }

        public Node<E> getRight() {
            return this.right;
        }

        public Object getContainer() {
            return this.container;
        }

        public void setElement(E e) {
            this.element = e;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public void setContainer(Object container) {
            this.container = container;
        }

        public String toString() {
            return this.element.toString();
        }
    }
}