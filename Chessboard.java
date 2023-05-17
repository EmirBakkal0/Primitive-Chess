import java.util.HashMap;
public class Chessboard{

    public static final int BLACK = 1;
    public static final int WHITE = 0;

    Square[][] squares= new Square[8][8];
    private boolean whitePlaying =true;
    char [] column={'a','b','c','d','e','f','g','h'};
    HashMap<Character,Integer> columnKey = new HashMap<Character, Integer>();

 
    public Chessboard(){
        initialize();
        columnKey.put('a',0);
        columnKey.put('b',1);
        columnKey.put('c',2);
        columnKey.put('d',3);
        columnKey.put('e',4);
        columnKey.put('f',5);
        columnKey.put('g',6);
        columnKey.put('h',7);


    }

    public void printBoard(){
        String chars ="    A   B   C   D   E   F   G   H   \n";
        String line="  ---------------------------------  ";
        System.out.println(chars+line);

        for (int i = 7; i >= 0; i--) {
            System.out.print(i+1+" | ");
            for (int j = 0; j <8 ; j++) {

                System.out.print(squares[i][j]);
                System.out.print(" | ");
            }
            System.out.println(i+1);
            System.out.println(line);
        }
        System.out.println(chars);

    }

    public void initialize(){
        for (int i=0;i<8;i++){ //// creating 64 empty squares
            for (int j = 0; j < 8; j++) {
                squares[i][j]= new Square(i+1,column[j],this);
            }

        }
        squares[0][0].setPiece(new Rook(0,squares[0][0]));  //starting positions
        squares[0][1].setPiece(new Knight(Chessboard.WHITE,squares[0][1]));
        squares[0][2].setPiece(new Bishop(0,squares[0][2]));
        squares[0][3].setPiece(new Queen(0,squares[0][3]));
        squares[0][4].setPiece(new King(0,squares[0][4]));
        squares[0][5].setPiece(new Bishop(0,squares[0][5]));
        squares[0][6].setPiece(new Knight(0,squares[0][6]));
        squares[0][7].setPiece(new Rook(0,squares[0][7]));

        squares[1][0].setPiece(new Pawn(0,squares[1][0]));
        squares[1][1].setPiece(new Pawn(0,squares[1][1]));
        squares[1][2].setPiece(new Pawn(0,squares[1][2]));
        squares[1][3].setPiece(new Pawn(0,squares[1][3]));
        squares[1][4].setPiece(new Pawn(0,squares[1][4]));
        squares[1][5].setPiece(new Pawn(0,squares[1][5]));
        squares[1][6].setPiece(new Pawn(0,squares[1][6]));
        squares[1][7].setPiece(new Pawn(0,squares[1][7]));


        squares[7][0].setPiece(new Rook(1,squares[7][0]));
        squares[7][1].setPiece(new Knight(Chessboard.BLACK,squares[7][1] ));
        squares[7][2].setPiece(new Bishop(1,squares[7][2]));
        squares[7][3].setPiece(new Queen(1,squares[7][3]));
        squares[7][4].setPiece(new King(1,squares[7][4]));
        squares[7][5].setPiece(new Bishop(1,squares[7][5]));
        squares[7][6].setPiece(new Knight(1,squares[7][6]));
        squares[7][7].setPiece(new Rook(1,squares[7][7]));

        for (int i = 0; i <8 ; i++) {
            squares[6][i].setPiece(new Pawn(Chessboard.BLACK,squares[6][i]));
        }
        //whitePlaying=true;
    }


    public boolean isGameEnded(){
        //TODO make it so it ends when no pieces exist from one color
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j < 8 ; j++) {
                if (squares[i][j].isEmpty()){   // all the squares are empty the game ends
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWhitePlaying(){
        return whitePlaying;
    }



    public Piece getPieceAt(String location){
        char column = location.charAt(0);
        String[] splitted = location.split("");
        int row = Integer.parseInt(splitted[1]);
        //System.out.println(column);
        //System.out.println(row);
        return squares[row-1][columnKey.get(column)].getPiece();
    }

    public Square getSquareAt(String location){
        char column = location.charAt(0);
        String[] splitted = location.split("");
        int row = Integer.parseInt(splitted[1]);

        return squares[row-1][columnKey.get(column)];
    }

    public Square[] getRowSquaresBetween(Square loc1,Square loc2) {
        int row2= loc2.getRow();
        int row1= loc1.getRow();
        int count;
        if(row2> row1){
            count=loc2.getRow()-loc1.getRow();
        }
        else count=loc1.getRow()-loc2.getRow();


        Square[] toReturn= new Square[count];

        for (int i = 0; i < count ; i++) {
            //TODO figure out this part
            //toReturn[i]=squares[i][j]
            toReturn[i]=new Square(loc1.getRow()+i+1,loc1.getColumn(),this);
        }
        return toReturn;
    }

    public Square[] getColSquaresBetween(Square loc1,Square loc2){
        int count= loc1.getColDistance(loc2);
        if(count<0){
            count=count-(2*count);
        }

        Square[] toReturn =new Square[count];
        for (int i = 0; i <count ; i++) {
            toReturn[i]=new Square(loc1.getRow(), (char) (loc1.getColumn()+1),this);
        }
        return toReturn;
    }


    public void nextPlayer() {

        whitePlaying= !whitePlaying;
    }
}