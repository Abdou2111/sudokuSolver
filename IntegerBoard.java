public class IntegerBoard<T> implements GameBoard<T> {
    private Integer[][] board;      // The two-dimensional sudoku board
    private final int WIDTH;          // The number of columns
    private final int HEIGHT;         // The number of rows

    // Construct bord with width and height
    public IntegerBoard( Integer[][] puzzle ){
        // The height is just the length of the puzzle
        this.HEIGHT = puzzle.length;
        // The width is just the lenght of one line
        this.WIDTH = puzzle[0].length;
        // create bord 
        board = new Integer[HEIGHT][WIDTH];
        for( int y = 0; y < HEIGHT; y++ ){
            for( int x = 0; x < WIDTH ; x++ ){
                board[y][x] = puzzle[y][x];
            }
        }
    }


    // acces board's elements
    @Override
    @SuppressWarnings("unchecked")
    public T getCell( int x, int y ) 
    throws IndexOutOfBoundsException{
        // return an exception if at least one index is not correct
        if( x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT ){
            throw new IndexOutOfBoundsException
            ( "getCell: position out of bounds" );
        }
        // return the element
        return (T) board[x][y];
    }
    
    // Mosidy board's element
    @Override
    public void setCell( int x, int y, Object value ) 
    throws IndexOutOfBoundsException{
        // return an exception if at least one index is not correct
        if( x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT ){
            throw new IndexOutOfBoundsException
            ( "setCell: position out of bounds" );
        }
        // Change the element by casting the new element as an Int
        board[x][y] = (Integer) value;
    }

    // Return number of columns
    @Override
    public int getWidth(){
        return WIDTH;
    }
    
    // Return number of rows
    @Override
    public int getHeight(){
        return HEIGHT;
    }
    
    // Display the  game bord
    @Override
    public void display(){
        verifySize();
        for( int y = 0; y < HEIGHT; y++ ){
            for( int x = 0; x < WIDTH; x++ ){
                System.out.print( board[y][x] + " " );
            }
            System.out.println();
        }
    }

    // Check puzzle size
    private Boolean verifySize() 
    throws IllegalArgumentException{
        if ( HEIGHT == 0  || WIDTH == 0) {
            throw new IllegalArgumentException("Puzzle is empty");
        }
        return perfectSquare(this.WIDTH);
    }

    // Check if the puzzle is a perfect square
    private boolean perfectSquare(int n) 
    throws IllegalArgumentException{
        if( WIDTH != HEIGHT ){ 
            throw new IllegalArgumentException("Not a square");
        }
        int racine = (int) Math.sqrt(n);
        return racine * racine == n;
    }

    public Integer[][] getBoard() {
        return board;
    }
}
