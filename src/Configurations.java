public class Configurations {
    private int board_size;
    private int lengthToWin;
    private int max_levels;
    char[][] board;
    // private instance variables, board size, of length to win, with the max level


    public Configurations(int board_size, int lengthToWin, int max_levels) {
        this.board_size = board_size;
        this.lengthToWin = lengthToWin;
        this.max_levels = max_levels;
        board = new char[this.board_size][this.board_size];
        //initialize all our variables with our parameters in the constructor
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
                // initialize every spot in our board to be an empty space
            }
        }
    }

    public HashDictionary createDictionary() {
        HashDictionary hashTable = new HashDictionary(7573);
        return hashTable;
        // return a hashDictionary we make and initialize to have a size of 6000-10000, I chose 7573
    }
    public int repeatedConfiguration(HashDictionary hashTable) {
        String storingString = "";
        //create our empty string to store all the spots in the board to this string
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                storingString = storingString + board[i][j];
            }
        }
        //we add every position in the board to our empty 'storingString'
        return hashTable.get(storingString);
        //return the get with our string of characters from the board since it will tell us if this overall string
        //configuration has a place in the hashDictionary. This return statement either returns -1 to indicate
        //there is not this record in the board or it returns the value record score to indicate it is indeed in the
        //hashDictionary
    }

    public void addConfiguration(HashDictionary hashDictionary, int score) {
        String storingString = "";
        //initialize our empty string
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                storingString = storingString + board[i][j];
                //collect and add all the positions on the board to our empty string
            }
        }
        Data record = new Data(storingString, score);
        //initialize this data record as a new record with the storing string we just made and 'score'
        hashDictionary.put(record);
        // add this record to our hashDictionary using 'put'
    }
    public void savePlay(int row, int col, char symbol){
            board[row][col] = symbol;
            //storing our symbol at that position in the board
    }
    public boolean squareIsEmpty (int row, int col) {
        if (board[row][col] == ' ') {
            //check if the spot is a space, therefore it is empty and we return true
            return true;
        }else {
            return false;
            // else it is not a space, and it is not empty therefore we return false
        }
    }
    public boolean wins(char symbol) {
        if (isPlusShape(symbol) || isXShape(symbol)){
            return true;
        }
        return false;
        // returns win when either the shape is x or plus due to our private helper functions
    }
    private boolean isXShape(char symbol) {
        int numTiles = 0;
        // initialize numTiles to keep track of our tiles for x and plus shapes
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == symbol) {
                    // go through the board positions and if a symbol is detected we stop and enter the if statement
                    numTiles = 1;
                    // add 1 to numTiles since we have found 1 symbol and must check around it for a shape
                    for (int k = 1; k < lengthToWin; k++) {
                        // we use a k for loop which starts at 1 since if we subtract starting from 0 it would do nothing
                        // we initialize to be less than length to win since it can only go up to that until we either
                        //return true or false for shape found or not found
                        if (!((i - k) < 0) && !((j - k) < 0) && board[i - k][j - k] == symbol) {
                            numTiles++;
                            // this checks for top left. also checks if the top left both positions i and j are in the board
                            // if the shift in positions is out of the board we do not add a tile and we skip to the next
                            // if statement
                        }
                        if (!((i - k) < 0) && !((j + k) > board.length-1) && board[i - k][j + k] == symbol) {
                            numTiles++;
                            // this checks for top right
                        }
                        if (!((i + k) > board.length-1) && !((j - k) < 0) && board[i + k][j - k] == symbol) {
                            numTiles++;
                            // this checks for bottom right
                        }
                        if (!((i + k) > board.length-1) && !((j + k) > board.length-1) && board[i + k][j + k] == symbol) {
                            numTiles++;
                            // this checks for bottom left
                        }
                        if (numTiles >= lengthToWin)
                        {
                            return true;
                            // if the length is 5 from the numTiles = 1 and our 4 incrementation all being true, we
                            // return true right away since we won
                        }
                        if (k == 1 && numTiles < 5) {
                            break;
                            // if in our first iteration of k (when k = 1) we do not have 5 tiles this means not all
                            // four corners were symbols and therefore a shape was not formed, and we would break from
                            // for loop right away since it would be pointless to keep going with no shape
                        }
                        if (k == lengthToWin-1) {
                            break;
                            // if we reach the last position in the for loop without hitting one of the statements above
                            // we break. this statement is a precaution
                        }
                    }
                }
            }
        }return false;
        // if we reach here that means we went through the whole board and did not find a win, then we return false for win
    }
    private boolean isPlusShape(char symbol) {
        int numTiles = 0;
        // initialize numTiles to keep track of our tiles for x and plus shapes
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == symbol) {
                    numTiles = 1;
                    // go through the board positions and if a symbol is detected we stop and enter the if statement
                    // add 1 to numTiles since we have found 1 symbol and must check around it for a shape
                    for (int k = 1; k < lengthToWin; k++) {

                        // we use a k for loop which starts at 1 since if we subtract starting from 0 it would do nothing
                        // we initialize it to be less than length to win since it can only go up to that until we either
                        // return true or false for shape found true or not found false
                        if (!((i - k) < 0) && board[i - k][j] == symbol) {
                            numTiles++;
                            // this checks for top. also checks if the top positions for both i and j are in the board
                            // if the shift in positions is out of the board we do not add a tile, and we skip to the next
                            // if statement
                        }
                        if (!((i + k) > board.length - 1) && board[i + k][j] == symbol) {
                            numTiles++;
                            // this checks for bottom

                        }
                        if (!((j - k) < 0) && board[i][j - k] == symbol) {
                            numTiles++;
                            // this checks for left
                        }
                        if (!((j + k) > board.length - 1) && board[i][j + k] == symbol) {
                            numTiles++;
                            // this checks for right
                        }
                        if (numTiles >= lengthToWin) {
                            return true;
                            // if the length is 5 from the numTiles = 1 and our 4 incrementation all being true, we
                            // return true right away since we won
                        }
                        if (k == 1 && numTiles < 5) {
                            break;
                            // if in our first iteration of k (when k = 1) we do not have 5 tiles this means not all
                            // four positions were symbols and therefore a shape was not formed, and we would break from
                            // for loop right away since it would be pointless to keep going with no shape
                        }
                        if (k == lengthToWin - 1) {
                            break;
                            // if we reach the last position in the for loop without hitting one of the statements above
                            // we break. this statement is a precaution
                        }
                    }
                }
            }
        }return false;
        // if we reach here that means we went through the whole board and did not find a win, then we return false for win
    }


        public boolean isDraw () {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == ' ') {
                        return false;
                    // for draw, we go through the whole board to check for a space, this means the board has not been fully
                    // filled up and therefore, it is not a draw yet until all positions are filled
                    }
                    if (wins(board[i][j])) {
                        return false;
                        // if wins is true then it is not~draw there fore it is false
                    }
                }
            }
            return true;
            // return true if it not a win, and it is completely full, this means it is full with no outcome, therefore
            //player and compute drew.
        }
        public int evalBoard () {
            if (wins('O')) return 3;
            if (wins('X')) return 0;
            if (isDraw()) return 2;
            else{
                return 1;
                // if 'O' wins (the computer's symbol) return 3, if 'X' (the player's symbol) wins return 0 and if
                // it's a draw between the two return 2, else return 0
            }
        }
    }
