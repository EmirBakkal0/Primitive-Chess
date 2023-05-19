
public class Rook extends Piece {

    public Rook(int color, Square location) {
        super(color, location);
    }
    @Override
    public boolean canMove(String to) {
        return canItMoveVertically(to) || canItMoveHorizontally(to);
    }

    public String toString() {
        return color == Chessboard.WHITE ? "R" : "r";
    }
    /// kale
}
