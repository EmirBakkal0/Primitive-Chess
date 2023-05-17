public class Bishop extends Piece{
    public Bishop(int color, Square location) {
        super(color, location);
    }
//fil


    public boolean canMove(String destination){
        boolean validMove;

        return true;

    }



    @Override
    public String toString() {
        return color == Chessboard.WHITE ? "B" : "b";
    }

}
