import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

public class LinkedGeneralTree<E> extends AbstractTree<E> {
    
    // ------- inner Node class
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private List<Node<E>> children;
        private Object container;

        // construct a node with element and neighbors
        public Node(E e, Node<E> parent, Object container) {
            this.element = e;
            this.parent = parent;
            this.children = new LinkedList<>();
            this.container = container;
        }

        // getters
        public E getElement() { return this.element; } 
        public Node<E> getParent() { return this.parent; }
        public List<Node<E>> getChildren() { return this.children; }
        public Object getContainer() { return this.container; }

        // setters
        public void setElement(E element) { this.element = element; }
        public void setParent(Node<E> parent) { this.parent = parent; }
        public void setContainer(Object container) { this.container = container; }
        public String toString() { return this.element.toString(); }

        public Node<E> getLastChild(Node<E> node) {
            if (node.getChildren().size() == 0) {
                return null;
            } else {
                return node.getChildren().get(node.getChildren().size() - 1);
            }
        }
    } // ----------- end of inner class Node

    protected Node<E> createNode( E element, Node<E> parent) {
        return new Node<E>(element, parent, this);
    }
    protected Node<E> root = null;
    private int size = 0;

    public LinkedGeneralTree() {}

    protected Node<E> validate(Position<E> p) 
    throws IllegalArgumentException {
        if(!(p instanceof Node)) 
            throw new IllegalArgumentException("Invalid position type");
        Node<E> node = (Node<E>)p;
        if(node.getContainer() != (Object)this)
            throw new IllegalArgumentException("Invalid position container");
        if(node.getParent() == node)
            throw new IllegalArgumentException("Position has been deleted");
        return node;
    }

    protected void setContainer(Position<E> p, Object container)
    throws IllegalArgumentException {
        Node<E> node = (Node<E>)p;
        node.setContainer(container);
    }

    @Override
    public Position<E> root() { return this.root; }

    @Override
    public Position<E> parent(Position<E> p) 
    throws IllegalArgumentException { 
        Node<E> node = this.validate(p);
        return node.getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        Node<E> node = validate(p);
        return Collections.unmodifiableList(node.getChildren());
    }

    @Override
    public int numChildren(Position<E> p) {
        Node<E> node = validate(p);
        return node.getChildren().size();
    }

    @Override
    public int size() { return this.size; }
    
    public Position<E> addChild(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        Node<E> child = createNode(e, parent);
        parent.getChildren().add(child);
        size++;
        return child;
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (root != null) throw new IllegalStateException("Tree already has a root");
        root = createNode(e, null);
        size = 1;
        return root;
    }

    public E removeLeaf(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(numChildren(p) > 0) throw new IllegalArgumentException("Position is not a leaf");
        Node<E> parent = node.getParent();
        if(parent != null) {
            parent.getChildren().remove(node);
        }
        size--;
        return node.getElement();
    }


    private int calculateSubtreeSize(Node<E> node) {
        int count = 1; // Count the node itself
        for (Node<E> child : node.getChildren()) {
            count += calculateSubtreeSize(child);
        }
        return count;
    }




// ---------------------------------------------------------------------------

    // Ajouter cette méthode à la classe LinkedGeneralTree
    public String toExpression(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        // Si le nœud est une feuille, retourner simplement son élément
        if (isExternal(p)) {
            return node.getElement().toString();
        }

        // Si le nœud est interne, construire l'expression avec ses enfants
        StringBuilder expression = new StringBuilder("("); // Début avec une parenthèse ouvrante
        List<Node<E>> children = node.getChildren();       // Obtenir les enfants du nœud
        for (int i = 0; i < children.size(); i++) {
            if (i > 0) {
                // Ajouter un opérateur (par défaut, espace ; modifier selon le contexte)
                expression.append(" ").append(node.getElement().toString()).append(" ");
            }
            // Appeler récursivement toExpression sur chaque enfant
            expression.append(toExpression(children.get(i)));
        }
        expression.append(")"); // Ajouter une parenthèse fermante
        return expression.toString();
    }


}