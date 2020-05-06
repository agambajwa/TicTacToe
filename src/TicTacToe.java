import java.util.*;

public class TicTacToe {
    public static Scanner S = new Scanner(System.in);
    public static Random R = new Random(); //To randomly generate CPU Opponent choices
    public static ArrayList<Integer> playerPositions = new ArrayList<>();
    public static ArrayList<Integer> cpuPositions = new ArrayList<>();
     //Making the game-board for the command line
     public static char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                                         {'-', '+', '-', '+', '-'},
                                         {' ', '|', ' ', '|', ' '},
                                         {'-', '+', '-', '+', '-'},
                                         {' ', '|', ' ', '|', ' '},};

    public static void main(String[] args) {
        System.out.println("---- Welcome to Tic-Tac-Toe! ----");
        printBoard();
        String result;
        while(true) {
            System.out.print("\nYour turn! Enter where you would like to place your mark (1-9): ");
            int playerChoice = S.nextInt();
            while(playerPositions.contains(playerChoice) || cpuPositions.contains(playerChoice)) {
                System.out.print("\nPosition already occupied! Enter different choice: ");
                playerChoice = S.nextInt();
            }
            placeMark(playerChoice, "player");
            printBoard();
            result = checkWinner();
            if(result.length() > 0) {
                System.out.println("\n ----- " + result + " ---- ");
                break;
            }
            System.out.println("\nCPU's Turn!");
            int cpuChoice = R.nextInt(9) + 1;//Generates random choice from the CPU Opponent
            while(playerPositions.contains(cpuChoice) || cpuPositions.contains(cpuChoice)) {
                cpuChoice = R.nextInt(9) + 1;
            }
            placeMark(cpuChoice, "cpu");
            printBoard();
            result = checkWinner();
            if(result.length() > 0) {
                System.out.println("\n ----- " + result + " ---- ");
                break;
            }
        }


    }

    //Function to print the game-board
    public static void printBoard() {
        System.out.println();
        for(char[] row: gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    //Function to place user's mark at the choice
    public static void placeMark(int choice, String user) {

        char mark = 'X';
        if(user.equals("cpu")) {
            mark = 'O';
            cpuPositions.add(choice);
        } else {
            playerPositions.add(choice);
        }

        switch (choice) {
            case 1 -> gameBoard[0][0] = mark;
            case 2 -> gameBoard[0][2] = mark;
            case 3 -> gameBoard[0][4] = mark;
            case 4 -> gameBoard[2][0] = mark;
            case 5 -> gameBoard[2][2] = mark;
            case 6 -> gameBoard[2][4] = mark;
            case 7 -> gameBoard[4][0] = mark;
            case 8 -> gameBoard[4][2] = mark;
            case 9 -> gameBoard[4][4] = mark;
            default -> System.out.println("Invalid choice!");
        }
    }

    //Function to check the winner
    public static String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List diag1 = Arrays.asList(1,5,9);
        List diag2 = Arrays.asList(7,5,3);

        List<List> winningList = new ArrayList<>();
        winningList.add(topRow);
        winningList.add(midRow);
        winningList.add(botRow);
        winningList.add(leftCol);
        winningList.add(midCol);
        winningList.add(rightCol);
        winningList.add(diag1);
        winningList.add(diag2);

        for(List l: winningList) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations! You won the game!";
            } else if(cpuPositions.containsAll(l)) {
                return "CPU Wins! You lost.";
            } else if(playerPositions.size() + cpuPositions.size() == 9) {
                return "It is a tie!";
            }
        }
        return "";
    }
}
