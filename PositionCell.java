public class PositionCell<E> implements Position<E> {
    private int row;
    private int col;
    private Integer value;

    public PositionCell( int row, int col, Integer value ) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue( Integer value ) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getElement() throws IllegalStateException {
        return (E) value;
    }

    public String toString() {
        return "Row: " + row + " Col: " + col + " Value: " + value;
    }

    public Position<E> getParent(Position<E> p) {
        return ;
    }
}
