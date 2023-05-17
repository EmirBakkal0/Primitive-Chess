public class King extends Piece {
// done

    public King(int color,Square location) {
        super(color,location);
    }

    @Override
    public boolean canMove(String loc) {
        boolean validMove = false;
        Square targetLocation=location.getBoard().getSquareAt(loc);
        int rowDistance= targetLocation.getRowDistance(location);
        int colDistance= targetLocation.getColDistance(location);
        boolean distances= colDistance<= 1 && colDistance >= -1 && rowDistance <= 1 && rowDistance >= -1;

        if(color == Chessboard.WHITE && distances ){
            if(targetLocation.getPiece() == null || targetLocation.getPiece().getColor() == Chessboard.BLACK ) {
                validMove = true;
            }
        } else if (color == Chessboard.BLACK && distances) {
            if(targetLocation.getPiece() == null || targetLocation.getPiece().getColor() == Chessboard.WHITE ) {
                validMove = true;
            }
        }

        return validMove;
    }


    public String toString() {
        return color == Chessboard.WHITE ? "K" : "k";
    }
}
