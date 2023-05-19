
public abstract class Piece {
    int color;
    Square location;

    public Piece(int color,Square location){
        this.color=color;
        this.location=location;

    }
    public int getColor(){
        return color;
    }
    public abstract boolean canMove(String loc); //Checks if the piece can move to the specified location
    public void move(String to){ //Moves the piece to the specified location
        Square targetLocation=location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear(); //First it gets the specified locations square object, and it sets the piece to that location and it clears the previous location.
        location = targetLocation;
    }

    public boolean canItMoveDiagonally(String to){
        boolean validMove =false;
        Square targetLocation=location.getBoard().getSquareAt(to);


        if (this.location.isAtSameDiagonal(targetLocation)){
            int arrayLen=location.getBoard().getDiagSquaresBetween(location,targetLocation).length;

            if (color == Chessboard.WHITE && (targetLocation.isEmpty() || targetLocation.getPiece().getColor() == Chessboard.BLACK)  ) {
                validMove =true;
                for (int i = 0; i < arrayLen; i++) {
                    if ( !(location.getBoard().getDiagSquaresBetween(location, targetLocation)[i].isEmpty() )){
                        validMove=false;
                        break;
                    }
                }
            }
            else if (color == Chessboard.BLACK && (targetLocation.isEmpty() || targetLocation.getPiece().getColor() == Chessboard.WHITE)  ) {
                validMove =true;
                for (int i = 0; i < arrayLen; i++) {
                    if ( !(location.getBoard().getDiagSquaresBetween(location, targetLocation)[i].isEmpty() )){
                        validMove=false;
                        break;
                    }
                }
            }
        }

        return validMove;
    }

    public boolean canItMoveHorizontally(String to){
        boolean validMove =false;
        Square targetLocation=location.getBoard().getSquareAt(to);

        if (this.location.isAtSameRow(targetLocation)){
            if (color == Chessboard.WHITE && (targetLocation.isEmpty()|| targetLocation.getPiece().getColor() == Chessboard.BLACK) ){ // if target is empty or has an opposing colored piece
                int arraylen=location.getBoard().getColSquaresBetween(location,targetLocation).length;
                validMove=true;
                for (int i = 0; i <arraylen ; i++) {
                    if (!location.getBoard().getColSquaresBetween(location, targetLocation)[i].isEmpty()){
                        validMove = false;
                        break;
                    }
                }

            }
            else if (color == Chessboard.BLACK && (targetLocation.isEmpty()|| targetLocation.getPiece().getColor() == Chessboard.WHITE) ){ // if target is empty or has an opposing colored piece
                int arraylen=location.getBoard().getColSquaresBetween(location,targetLocation).length;
                validMove=true;
                for (int i = 0; i <arraylen ; i++) {
                    if (!location.getBoard().getColSquaresBetween(location, targetLocation)[i].isEmpty()){
                        validMove = false;
                        break;
                    }
                }
            }
        }
        return validMove;
    }

    public boolean canItMoveVertically(String to){
        boolean validMove =false;
        Square targetLocation=location.getBoard().getSquareAt(to);

        // checking the target if it's at the same column as existing location

        if (this.location.isAtSameColumn(targetLocation)){
            int arrayLen= location.getBoard().getRowSquaresBetween(location, targetLocation).length;

            if (color == Chessboard.WHITE && (targetLocation.isEmpty()|| targetLocation.getPiece().getColor() == Chessboard.BLACK)  ) {// if target is empty or has an opposing colored piece
                validMove =true;

                for (int i = 0; i < arrayLen; i++) {
                    if ( !(location.getBoard().getRowSquaresBetween(location, targetLocation)[i].isEmpty() )){
                        validMove = false;
                        break;
                    }
                }
            }
            else if (color==Chessboard.BLACK && (targetLocation.isEmpty()|| targetLocation.getPiece().getColor() == Chessboard.WHITE) ) {// if target is empty or has an opposing colored piece

                validMove =true;
                for (int i = 0; i < arrayLen; i++) {
                    if (!location.getBoard().getRowSquaresBetween(location, targetLocation)[i].isEmpty()){
                        validMove = false;
                        break;
                    }
                }
            }

        }
        return validMove;
    }

    public String toString(){
        return " ";
    }
}
