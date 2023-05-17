import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader=new Scanner(System.in);
        Chessboard board= new Chessboard();
        board.printBoard();
        //System.out.println(board.getPieceAt("a2"));
        while (!board.isGameEnded()){
            System.out.println("It\'s "+(board.isWhitePlaying()? "White": "Black")+"\'s turn");
            Piece piece =null;
            do {
                System.out.println("Enter coordinates of piece: ");//TODO make it so it doesnt crash when you enter "aaa" for exp
                String coord=reader.next();
                coord=coord.strip();
                piece = board.getPieceAt(coord.toLowerCase());

            }
            while (piece==null || piece.getColor() == (board.isWhitePlaying() ? Chessboard.BLACK : Chessboard.WHITE) );


            String newLocation= null;
            do{
                System.out.println("Enter the new location for the piece: ");
                newLocation =reader.next();
            }while (!piece.canMove(newLocation));

            piece.move(newLocation);
            board.nextPlayer();
            board.printBoard();

        }
        reader.close();
    }
}
