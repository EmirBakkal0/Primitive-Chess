public class King extends Piece {
// done

    public King(int color,Square location) {
        super(color,location);
    }

    @Override
    public boolean canMove(String loc) {
        //First it gets the column and row distance of the locations and if the distances are less and equal to 1
        //and if the place is empty or there is an opposing color piece there it will return true.

        boolean validMove = false;
        Square targetLocation=location.getBoard().getSquareAt(loc);
        int rowDistance= targetLocation.getRowDistance(location);
        int colDistance= targetLocation.getColDistance(location);
        boolean distances= colDistance<= 1 && colDistance >= -1 && rowDistance <= 1 && rowDistance >= -1;

        if(color == Chessboard.WHITE && distances ){
            if(targetLocation.getPiece() == null || targetLocation.getPiece().getColor() == Chessboard.BLACK ) { // if target is empty or has an opposing colored piece
                validMove = true;
            }
        } else if (color == Chessboard.BLACK && distances) {
            if(targetLocation.getPiece() == null || targetLocation.getPiece().getColor() == Chessboard.WHITE ) { // if target is empty or has an opposing colored piece
                validMove = true;
            }
        }
        //First it gets the column and row distance of the locations and if the distances are less and equal to 1
        //and if the place is empty or there is an opposing color piece there it will return true.

        return validMove;
    }


    public String toString() {
        return color == Chessboard.WHITE ? "K" : "k";
    }
}
