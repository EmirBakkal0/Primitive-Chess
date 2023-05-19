import java.util.HashMap;

public class Square {
    //private char column; // A B C D E F G
    //private char row;  // 1 2 3 4 5 6 7 8 
    private String location ;
    private Piece piece;
    private Chessboard board;
    char [] column={'a','b','c','d','e','f','g','h'};
    HashMap<Character,Integer> columnKey = new HashMap<Character, Integer>();
    public Square(String location,Chessboard board){
        this.location=location;
        this.board=board;
        columnKey.put('a',0);
        columnKey.put('b',1);
        columnKey.put('c',2);
        columnKey.put('d',3);
        columnKey.put('e',4);
        columnKey.put('f',5);
        columnKey.put('g',6);
        columnKey.put('h',7);
    }

    public Square(int row,char column,Chessboard board){
        //This is constructor for Square class
        //It initializes row column and board variables.
        this.location=column+""+row;
        this.board=board;
        columnKey.put('a',0);
        columnKey.put('b',1);
        columnKey.put('c',2);
        columnKey.put('d',3);
        columnKey.put('e',4); //making a dictionary type thing for columns
        columnKey.put('f',5);
        columnKey.put('g',6);
        columnKey.put('h',7);
    }
    public String getLocation(){
        return location;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return piece;
    }
    public int getRow(){
        String[] splitted = this.location.split("");
        return Integer.parseInt(splitted[1]);
    }
    public char getColumn(){
        return location.charAt(0);
    }


    public boolean isAtLastRow(int color){//Returns True if the piece is white and its at 8th row or if the piece is black its at 1st row.
        if (color ==Chessboard.WHITE  && getRow() ==8){
            return true;
        }
        else if (Chessboard.BLACK== color && getRow() ==1){
            return true;
        }

        else {return false;}
    }


    public int getRowDistance(Square s){//Returns the row distance between two square objects.
        return this.getRow()-s.getRow();
        //return this.getRow()> s.getRow() ? this.getRow() - s.getRow() : s.getRow()-this.getRow();
    }

    public int getColDistance(Square s){//Returns the col distance between two square objects.
        return columnKey.get(this.getColumn())- columnKey.get(s.getColumn());

    }


    public boolean isAtSameColumn(Square s){ //Checks if both squares are at the same column.
        if (this.getColumn()==s.getColumn()){
            return true;
        }
        else return false;
    }

    public boolean isAtSameRow(Square s) {
        return this.getRow()==s.getRow();
    } //Checks if both squares are at the same row.
    public boolean isAtSameDiagonal(Square targetLocation) {
        if (Math.abs(this.getRow()-targetLocation.getRow()) == Math.abs(columnKey.get(this.getColumn()) -columnKey.get(targetLocation.getColumn())  )){
            /// if rowDifference equals colDifference then they're at same diagonal
            return true;
        }
        else return false;
    }

    public boolean isNeighborColumn(Square target){ //Checks if both square are next to each other.
        int col=columnKey.get(this.getColumn());
        int targetCol=columnKey.get(target.getColumn());
        if(col-1==targetCol || col+1 == targetCol){ //Returns true if abs value of (column of this) –(column of target) = 1
            return true;
        }
        else
            return false;
    }

    public boolean isEmpty(){//Checks to see if a square is empty or not.
        if (piece==null|| piece.toString().equals(" ")){
            return true;
        }
        else return false;

    }
    public Square getSquare(){
        return  this;
    }

    public String toString(){
        if (isEmpty()){
            return " ";
        }
        else return piece.toString();
    }

    public Chessboard getBoard() {
        return board;
    }

    public void putNewQueen(int color) { //puts a new queen at this square
        piece = new Queen(color,this);

    }

    public void clear() {
        piece=null;
    } //clears the piece in the square



}
