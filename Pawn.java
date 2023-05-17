
public class Pawn extends Piece {
    ////done;;;;;;;;;;;;;;
    boolean initialLocation = true;

    public Pawn(int color, Square location) {
        super(color, location);
    }


    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove=false;
        Square targetLocation=location.getBoard().getSquareAt(to);
        int rowDistance= targetLocation.getRowDistance(location);

        if (this.location.isAtSameColumn(targetLocation)){ /// checks if the target and existing location is at same column
            if (color == Chessboard.WHITE && rowDistance >0 && rowDistance <=2) {
                if (rowDistance == 2) {
                    if (initialLocation) {
                        // pawn move 2 squares so we are checking 2 squares

                        validMove = targetLocation.isEmpty() && location.getBoard().getRowSquaresBetween(location, targetLocation)[0].isEmpty();

                    }
                }
                else {
                    validMove = targetLocation.isEmpty();
                }
                return validMove;
            }
                else if(color ==Chessboard.BLACK && rowDistance<0 && rowDistance >= -2){
                    if(rowDistance==-2){
                         if (initialLocation) {
                             validMove = targetLocation.isEmpty() && location.getBoard().getRowSquaresBetween(location,targetLocation)[0].isEmpty();
                         }
                    }
                    else {
                            validMove=targetLocation.isEmpty();
                    }

                }
                return validMove;
             }

        else if (this.location.isNeighborColumn(targetLocation)) {
            if(color== Chessboard.WHITE && rowDistance==1){
                validMove= !targetLocation.isEmpty()&& targetLocation.getPiece().getColor() == Chessboard.BLACK;
            }
            else if (color == Chessboard.BLACK && rowDistance==-1) {
                validMove =!targetLocation.isEmpty()&& targetLocation.getPiece().getColor() == Chessboard.WHITE;
            }
        }

        return validMove;
    }





    @Override
    public void move(String to) {
        Square targetLocation=location.getBoard().getSquareAt(to);

        if (targetLocation.isAtLastRow(color)){   /// this is for promoting to queen at last row
            targetLocation.putNewQueen(color);
        }
        else {
            this.initialLocation= false;
            targetLocation.setPiece(this);
        }
        location.clear();
        location = targetLocation;

        //location.getBoard().nextPlayer();
    }


    @Override
    public String toString() {
        return color == Chessboard.WHITE ? "P" : "p";
    }
}
