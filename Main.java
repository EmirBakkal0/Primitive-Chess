import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader=new Scanner(System.in);
        Chessboard board= new Chessboard();

        board.printBoard();
        System.out.println("Welcome to primitive chess!");
        while (!board.isGameEnded()) {
            System.out.println("It\'s " + (board.isWhitePlaying() ? "White" : "Black") + "\'s turn.");
            Piece piece = null;
            do {
                System.out.println("Enter coordinates of piece (Example: a1 or A1) : ");
                try {
                    String coord = reader.next();
                    coord = coord.strip();
                    piece = board.getPieceAt(coord.toLowerCase()); // converting the string to lower case,otherwise it will give error
                } catch (InvalidCoordinateException ice) {// this is to make sure the program won't crash when entered wrong characters, etc.
                    System.out.println(ice.getMessage());
                }
                catch (Exception e){
                    System.out.println("You've entered a wrong coordinate. Please try again..");
                }


            }
            while (piece == null || piece.getColor() == (board.isWhitePlaying() ? Chessboard.BLACK : Chessboard.WHITE));


            String newLocation = null;
            boolean canPieceMove = false;
            do {
                System.out.println("Enter the new location for the piece (Example: a1 or A1): ");
                try {
                    newLocation = reader.next();
                    newLocation = newLocation.strip();
                    newLocation = newLocation.toLowerCase();

                    canPieceMove = piece.canMove(newLocation);
                } catch (Exception e) {// this is to make sure the program won't crash when entered wrong characters, etc.
                    //System.out.println(e.getMessage());
                    System.out.println("You've entered a wrong coordinate. Please try again..");
                }


            } while (!canPieceMove);

            piece.move(newLocation);
            board.nextPlayer();
            board.printBoard();

        }
        System.out.println((!board.isWhitePlaying()? "White": "Black")+" wins!!");
        reader.close();
    }
}
