public class IntegerBoard implements GameBoard {
    private int[][] board;      // The two-dimensional sudoku board
    private int width;          // The number of columns
    private int height;         // The number of rows

    // Construct bord with width and height
    public IntegerBoard( Integer[][] puzzle ){
        // The height is just the length of the puzzle
        this.height = puzzle.length;
        // The width is just the lenght of one line
        this.width = puzzle[0].length;
        // create bord 
        board = new int[height][width];
        for( int x = 0; x < width; x++ ){
            for( int y = 0; y < height; y++ ){
                board[x][y] = puzzle[y][x];
            }
        }
    }

    // acces board's elements
    @Override
    public Object getCell( int x, int y ) throws IndexOutOfBoundsException{
        // return an exception if at least one index is not correct
        if( x < 0 || x >= width || y < 0 || y >= height ){
            throw new IndexOutOfBoundsException
            ( "getCell: position out of bounds" );
        }
        // return the element
        return board[x][y];
    }
    
    // Mosidy board's element
    @Override
    public void setCell( int x, int y, Object value ) 
    throws IndexOutOfBoundsException{
        // return an exception if at least one index is not correct
        if( x < 0 || x >= width || y < 0 || y >= height ){
            throw new IndexOutOfBoundsException
            ( "setCell: position out of bounds" );
        }
        // Change the element by casting the new element as an Int
        board[x][y] = (int) value;
    }

    // Return number of columns
    @Override
    public int getWidth(){
        return width;
    }
    
    // Return number of rows
    @Override
    public int getHeight(){
        return height;
    }
    
    // Display the  game bord
    @Override
    public void display(){
        for( int y = 0; y < height; y++ ){
            for( int x = 0; x < width; x++ ){
                System.out.print( board[x][y] + " " );
            }
            System.out.println();
        }
    }
    
}
