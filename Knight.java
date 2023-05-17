
public class Knight extends Piece {
    ////done

    public Knight(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String loc) {
        boolean validMove=false;
        Square targetLocation=location.getBoard().getSquareAt(loc);
        int rowDistance= targetLocation.getRowDistance(location);
        int colDistance= targetLocation.getColDistance(location);
        boolean validDistances=(colDistance ==2 && rowDistance ==1) || (colDistance == 1 && rowDistance == 2 )  // these are the possible moves for the knight
                || (colDistance== -1 && rowDistance ==-2 ) || (colDistance ==-2 && rowDistance== -1 ) || (colDistance ==2 && rowDistance== -1 )
                || (colDistance ==-2 && rowDistance== 1 ) || (colDistance ==-1 && rowDistance== 2 ) || (colDistance ==1 && rowDistance== -2 );

        if (color==Chessboard.WHITE && validDistances){
            if( targetLocation.getPiece() == null || targetLocation.getPiece().getColor() ==Chessboard.BLACK){
                validMove=true;
            }
        }
        else if (color == Chessboard.BLACK && validDistances ) {
            if (targetLocation.getPiece() == null || targetLocation.getPiece().getColor() ==Chessboard.WHITE) {
                validMove = true;
            }
        }
        return validMove;
    }


    public String toString() {
        return color == Chessboard.WHITE ? "N" : "n";
    }
    /// at
}
