
import static java.lang.Math.sqrt;
import java.util.Objects;

public class SudokuSolver implements GameSolver {

    // attributes
    IntegerBoard<Integer> board;
    IntegerBoard<Integer> solution;
    Tree<IntegerBoard<Integer>> tree;

    // constructor
    public SudokuSolver( GameBoard<Integer> board ){
        this.board = (IntegerBoard<Integer>) board;
        this.solution = new IntegerBoard<Integer> ( new Integer[board.getWidth()][board.getHeight()] );
    }

    // print the solution
    public void printSolution(){
        System.out.println( solution );
    }

    //________________________________________________________________________________________________________
    // mandatory GameSolver interface methods
    @Override
    public boolean solve(){
        return true;
    }

    public boolean isValidPlacement( int row, int col, Integer value ){
        int dimensionSousCarre = (int) sqrt(board.getWidth());
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
        int x0 = row / dimensionSousCarre ; 
        int y0 = col / dimensionSousCarre ;
        for( int x = 0; x < dimensionSousCarre; x++ ){
            for( int y = 0; y < dimensionSousCarre; y++ ){
                if( Objects.equals(board.getCell( x0 + x, y0 + y ), value) ){
                    return false;
                }
            }
        }
        return true;
        
    }
    
/*
    // validate an insertion in the board
    public boolean isValidPlacement( int row, int col, Integer value ){
        // check row
        for( int x = 0; x < board.getWidth(); x++ ){
            if( board.getCell( x, col ) == value ){
                return false;
            }
        }
        // check column
        for( int y = 0; y < board.getHeight(); y++ ){
            if( board.getCell( row, y ) == value ){
                return false;
            }
        }
        // check 3x3 square
        int x0 = (row / 3) * 3;
        int y0 = (col / 3) * 3;
        for( int x = 0; x < 3; x++ ){
            for( int y = 0; y < 3; y++ ){
                if( board.getCell( x0 + x, y0 + y ) == value ){
                    return false;
                }
            }
        }
        return true;
    }*/

    // actual solver
    /*private boolean solveBoard(){
        for( int x = 0; x < board.getWidth(); x++ ){
            for( int y = 0; y < board.getHeight(); y++ ){
                if( board.getCell( x, y ) == 0 ){
                    for( int value = 1; value <= 9; value++ ){
                        if( isValidPlacement( x, y, value ) ){
                            board.setCell( x, y, value );
                            if( solveBoard() ){
                                return true;
                            }
                            board.setCell( x, y, 0 );
                        }
                    }
                    return false;
                }
            }
        }
        solution = board;
        return true;
    }*/

    
}
