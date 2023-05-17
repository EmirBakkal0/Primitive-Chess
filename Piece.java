
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
    public abstract boolean canMove(String loc);
    public void move(String to){

        Square targetLocation=location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;

    }

    public String toString(){
        return " ";
    }
}
