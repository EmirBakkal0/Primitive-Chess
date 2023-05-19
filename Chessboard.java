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

    }


    public boolean isGameEnded(){
        boolean whiteFound=false;
        boolean blackFound=false;

        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j < 8 ; j++) {
                if (squares[i][j].getPiece() != null ){
                    if(( squares[i][j].getPiece().getColor()==WHITE )) {
                        whiteFound= true;
                    }

                    if (squares[i][j].getPiece().getColor()==BLACK) {
                        blackFound= true;
                    }
                }
            }
        }
        return !(whiteFound&&blackFound);
    }

    public boolean isWhitePlaying(){
        return whitePlaying;
    }



    public Piece getPieceAt(String location) throws InvalidCoordinateException{
        char column = location.charAt(0);
        String[] splitted = location.split("");
        int row = Integer.parseInt(splitted[1]);
        //System.out.println(column);
        //System.out.println(row);
        if(row > 8 ||row < 1){
            throw new InvalidCoordinateException("Row number should be between 1 and 8.");
        }


        if(columnKey.get(column)==null){
            throw new InvalidCoordinateException("Column character should be between A and H.");
        }
        return squares[row-1][columnKey.get(column)].getPiece();
    }

    public Square getSquareAt(String location){
        char column = location.charAt(0);
        String[] splitted = location.split("");
        int row = Integer.parseInt(splitted[1]);

        return squares[row-1][columnKey.get(column)];
    }

    public Square[] getRowSquaresBetween(Square s1, Square s2){
        char col= s1.getColumn();
        int upperLimit= s2.getRow();
        int lowerLimit= s1.getRow();
        if(lowerLimit > upperLimit){
            lowerLimit=s2.getRow();
            upperLimit=s1.getRow();
        }
        Square[] toReturn =new Square[upperLimit-lowerLimit-1];//minus 1 here otherwise the last object will be null
        int j =0;
        for (int i = lowerLimit; i < upperLimit-1; i++) { //-1 on upperlim becuz the index will be out of bounds

            toReturn[j]=squares[i][columnKey.get(col)];
            j++;
        }
        return toReturn;
    }



    public Square[] getColSquaresBetween(Square loc1,Square loc2){
        int row= loc1.getRow();
        char upperCol=loc2.getColumn();
        char lowerCol=loc1.getColumn();
        if(columnKey.get(lowerCol) > columnKey.get(upperCol)){
            lowerCol=upperCol;
            upperCol=loc1.getColumn();
        }
        int upperLim=columnKey.get(upperCol); //
        int lowerLim=columnKey.get(lowerCol)+1;
        int size=upperLim-lowerLim;
        int j =0;
        Square[] toReturn =new Square[size];
        for (int i =lowerLim ; i <upperLim ; i++) {
            toReturn[j]=squares[row-1][i];
            j++;
        }

        return toReturn;
    }

    public Square[] getDiagSquaresBetween(Square loc1, Square loc2) { //loc1 c1  loc2 h6 for example      //loc1 c8  loc2 f5
        if(loc1.isAtSameDiagonal(loc2)){
            boolean topLefttoBottomRight=false;
            char upperCol=loc2.getColumn(); // h  //f
            char lowerCol=loc1.getColumn(); // c  //c
            int upperRow= (loc2.getRow());

            int lowerRow= loc1.getRow(); // 1   //8
            if(upperRow<lowerRow){   // upper=5  lower =8
                lowerRow=upperRow;  //lower=8
                upperRow=loc1.getRow(); //upper=5
                topLefttoBottomRight=!topLefttoBottomRight;
            }
            upperRow=upperRow-1; //-1 is here because array starts with 0    6-1=5   8-1=7
            int upperLimCol=columnKey.get(upperCol); // h -> 7   // f->5
            int lowerLimCol=columnKey.get(lowerCol); // c -> 2   // c-> 2
            if (lowerLimCol>upperLimCol){ //lower=2 upper=5  -> false
                int temp=lowerLimCol;
                lowerLimCol=upperLimCol;
                upperLimCol=temp;
                topLefttoBottomRight=!topLefttoBottomRight;
            }

            Square []toReturn= new Square[Math.abs(upperRow-lowerRow)]; // 5-1 =4  //7-5=2
            int index=0;

            if(topLefttoBottomRight) {
                while (lowerLimCol/* 2 */ < upperLimCol/*5*/ && lowerRow/*5*/ < upperRow/*7*/) {
                    lowerLimCol++;
                    upperRow--;
                    toReturn[index/* 0 */] = squares[upperRow/* 6 */][lowerLimCol /* 3 */]; // gives [d7,e6]
                    index++;

                }
            }
            else {
                while (lowerLimCol < upperLimCol && lowerRow < upperRow) {
                    lowerLimCol++;
                    toReturn[index/* 0 */] = squares[lowerRow/* 1 */][lowerLimCol /* 3 */]; // gives [d2,e3,...,g5]
                    index++;
                    lowerRow++;
                }

            }

            return toReturn;
        }
        else return null;
    }

    public void nextPlayer() {

        whitePlaying= !whitePlaying;
    }


}