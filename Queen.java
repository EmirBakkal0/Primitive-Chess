
public class Queen extends Piece {


    public Queen(int color, Square location) {
        super(color, location);
    }



    @Override
    public boolean canMove(String to) {
        return canItMoveDiagonally(to) || canItMoveHorizontally(to) || canItMoveVertically(to);
    }


    public String toString() {
        return color == Chessboard.WHITE ? "Q" : "q";
    }
}
