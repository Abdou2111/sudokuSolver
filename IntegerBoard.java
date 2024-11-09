public class IntegerBoard implements GameBoard {
    private int[][] board;
    private int width;
    private int height;

    public IntegerBoard( Integer[][] puzzle ){
        this.height = puzzle.length;
        this.width = puzzle[0].length;
        board = new int[height][width];
        for( int x = 0; x < width; x++ ){
            for( int y = 0; y < height; y++ ){
                board[x][y] = puzzle[y][x];
            }
        }
    }

    @Override
    public Object getCell( int x, int y ) throws IndexOutOfBoundsException{
        if( x < 0 || x >= width || y < 0 || y >= height ){
            throw new IndexOutOfBoundsException( "getCell: position out of bounds" );
        }
        return board[x][y];
    }
    
    @Override
    public void setCell( int x, int y, Object value ) throws IndexOutOfBoundsException{
        if( x < 0 || x >= width || y < 0 || y >= height ){
            throw new IndexOutOfBoundsException( "setCell: position out of bounds" );
        }
        board[x][y] = (int) value;
    }
    
    @Override
    public int getWidth(){
        return width;
    }
    
    @Override
    public int getHeight(){
        return height;
    }
    
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
