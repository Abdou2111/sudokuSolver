
import static java.lang.Math.sqrt;
import java.util.Objects;

public class SudokuSolver implements GameSolver {

    // attributes
    IntegerBoard<Integer> board;
    IntegerBoard<Integer> solution;
    LinkedGeneralTree<IntegerBoard<Integer>> tree;

    // constructor
    public SudokuSolver( GameBoard<Integer> board ){
        this.board = (IntegerBoard<Integer>) board;
        this.solution = new IntegerBoard<Integer> ( new Integer[board.getWidth()][board.getHeight()] );
        this.tree = new LinkedGeneralTree<>();
    }

    // print the solution
    public void printSolution(){
        for( int x = 0; x < board.getWidth(); x++ ){
            for( int y = 0; y < board.getHeight(); y++ ){
                System.out.print( solution.getCell( x, y ) + " " );
            }
            System.out.println();
        }
    }

    public boolean isValidPlacement( int row, int col, Integer value ){
        int dimensionSousCarre = (int) sqrt(board.getWidth());
        
        // check row
        for( int x = 0; x < board.getWidth(); x++ ){
            if( Objects.equals(board.getCell( x, col ), value) ){
                return false;
            }
        }
        // check column
        for( int y = 0; y < board.getHeight(); y++ ){
            if( Objects.equals(board.getCell( row, y ), value) ){
                return false;
            }
        }
        // check n x n square
        int x0 = (row / dimensionSousCarre) * dimensionSousCarre;
        int y0 = (col / dimensionSousCarre) * dimensionSousCarre;
        for( int x = 0; x < dimensionSousCarre; x++ ){
            for( int y = 0; y < dimensionSousCarre; y++ ){
                if( Objects.equals(board.getCell( x0 + x, y0 + y ), value) ){
                    return false;
                }
            }
        }
        return true;
    }


    //________________________________________________________________________________________________________
    // mandatory GameSolver interface methods
    @Override
    public boolean solve(){
        tree.addRoot(board);
        return solveBoard(tree.root);
    }

    /**
     * This method is using a tree to solve the sudoku puzzle.
     * The root is initialized as the sudoku given at the start,
     * and the array is traversed in width, checking which value can be added.
     * Once the value has been found, we create a child (Node) of the root and replace 
     * the 0 in the cell with the value found.
     */
    //@SuppressWarnings("unchecked")
    /*public void solveBoard(IntegerBoard<Integer> puzzle){
        //  Browse the puzzle from left to right
        for(int x = 0; x <board.getWidth(); x++) {
            for(int y = 0; y < board.getHeight(); y++) {
                // Finding an empty square (represented by 0) 
                if(board.getCell(x, y) == 0) {
                    // Finding the right value
                    for(Integer value = 1; value <= board.getHeight(); value++) {
                        if(isValidPlacement(x, y, value)) {
                            puzzle.setCell(x, y, value);

                            /*
                            IntegerBoard<Integer> newBoard = new IntegerBoard<>(puzzle.getElement());
                            newBoard.setCell(x, y, value);
                            // If the value is valid, we create a node in the tree
                            board.setCell(x, y, value);
                            Position<IntegerBoard<Integer>> child = sudokuTree.addChild(root, board);
                            LinkedGeneralTree<IntegerBoard<Integer>> childTree =
                                (LinkedGeneralTree<IntegerBoard<Integer>>) child;
                            solveBoard(childTree);
                        }
                    }
                }
            }
        }*/

    private boolean solveBoard(LinkedGeneralTree.Node<IntegerBoard<Integer>> node) {

        // Ici on récupère le board du node
        board = node.getElement();
        // On parcourt le board de gauche à droite_____________________________________________________
        // Traverser les lignes
        for (int x = 0; x < board.getWidth(); x++) {
            // Traverser les colonnes
            for (int y = 0; y < board.getHeight(); y++) {
                // Si la case est vide
                if (board.getCell(x, y) == 0) {
                    // Trouver la bonne valeur
                    for (int value = 1; value <= board.getHeight(); value++) {
                        if (isValidPlacement(x, y, value)) {
                            // On crée un nouveau board
                            IntegerBoard<Integer> newBoard = new IntegerBoard<>(board.getBoard());
                            newBoard.setCell(x, y, value);
                            // On ajoute le noeud à l'arbre
                            LinkedGeneralTree.Node<IntegerBoard<Integer>> child = (LinkedGeneralTree.Node<IntegerBoard<Integer>>) tree.addChild(node, newBoard);
                            if(solveBoard(child)){
                                return true;
                            }
                            else {
                                tree.removeLeaf(node);
                            }
                        }
                    }
                }
            }
        }
        solution = board;
        return true;
    }

    private boolean isSolved(){
        for( int x = 0; x < board.getWidth(); x++ ){
            for( int y = 0; y < board.getHeight(); y++ ){
                if( board.getCell( x, y ) == 0 ){
                    return false;
                }
            }
        }
        return true;
    }
}

