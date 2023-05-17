
public class Rook extends Piece {

    public Rook(int color, Square location) {
        super(color, location);
    }
    @Override
    public boolean canMove(String to) {
        boolean validMove =false;
        Square targetLocation=location.getBoard().getSquareAt(to);
        int rowDist= targetLocation.getRowDistance(location);
        int colDist= targetLocation.getColDistance(location);

        // checking the target if it's at the same column as existing location

        if (this.location.isAtSameColumn(targetLocation)){
            int arrayLen= location.getBoard().getRowSquaresBetween(location, targetLocation).length;
            if (color == Chessboard.WHITE && rowDist >0 && rowDist <=8) {
                validMove =true;
                for (int i = 0; i < arrayLen; i++) {
                    if (!location.getBoard().getRowSquaresBetween(location, targetLocation)[i].isEmpty()){
                        validMove = false;
                    }
                }

            }
            else if (color==Chessboard.BLACK && rowDist <0 && rowDist>=-8) {

                validMove =true;
                for (int i = 0; i < arrayLen; i++) {
                    if (!location.getBoard().getRowSquaresBetween(location, targetLocation)[i].isEmpty()){
                        validMove = false;
                    }
                }
            }



        }

        else if (this.location.isAtSameRow(targetLocation)){
            if (colDist >= -8 && colDist <=8) {
                int arraylen=location.getBoard().getColSquaresBetween(location,targetLocation).length;
                validMove=true;
                for (int i = 0; i <arraylen ; i++) {
                    if (!location.getBoard().getColSquaresBetween(location, targetLocation)[i].isEmpty()){
                        validMove = false;
                    }
                }

            }

        }


          return validMove;
    }

    public int getColor() {
        return color;
    }
    /*
    @Override
    public void move(String to) {
        Square targetLocation=location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
    }
    */

    public String toString() {
        return color == Chessboard.WHITE ? "R" : "r";
    }
    /// kale
}
