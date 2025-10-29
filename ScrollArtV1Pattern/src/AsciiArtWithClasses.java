import java.util.Random;

public class AsciiArtWithClasses {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int BUNNY_WIDTH = 8;
    static final int BUNNY_HEIGHT = 6;
    static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[34][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }
        char[] prevRow = nextRows[0];

        while (true) {
            // At each lane now on the top row, 25% chance to add a new image
            for (int x = 0; x < WIDTH - 34; x += 34) {
                if (isBlank(prevRow, x) && rand.nextDouble() < 0.25) {
                    loadNextRowsWithImage(nextRows, x);
                }
            }
            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            prevRow = nextRows[0]; // update prev row
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(40); // Delay in ms
            long time = System.currentTimeMillis() - startTime;
            iterations++;

        }
    }

    //check if row is blank
    static boolean isBlank(char[] row, int x) {
        for (int i = x; i < x + 34; i++) {
            if (row[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    //check whether an image can be placed w/o colliding
    private static boolean canPlace(char[][] nextRows, char[][] img, int x) {
        if (x < 0 || x + img[0].length > WIDTH) return false;     // out of bounds
        for (int iy = 0; iy < img.length; iy++) {
            for (int ix = 0; ix < img[iy].length; ix++) {
                char c = img[iy][ix];
                if (c != ' ' && nextRows[iy][x + ix] != ' ') {
                    return false; // would collide
                }
            }
        }
        return true;
    }

    private static void loadNextRowsWithImage(char[][] nextRows, int x) {
        // triple array version to better manage all images, it's getting a little bit out of hand!! and it is not like they are all the same size anyway.
        char[][][] images = {getBunny(), getCarrot(), getButterfly(), getBoat(), getBurger(), getGem()};
        char[][] img = images[rand.nextInt(images.length)];

        //only place if it won't collide
        if (!canPlace(nextRows, img, x)) return;

        //don't overwrite w spaces
        for (int iy = 0; iy < img.length; iy++) { // height
            for (int ix = 0; ix < img[iy].length; ix++) { // width
                char c = img[iy][ix];
                if (c != ' ') {
                    nextRows[iy][x + ix] = c;
                }
            }
        }
    }

    static void shiftRowsUp(char[][] nextRows) {
        for (int i = 1; i < nextRows.length; i++) {
            nextRows[i - 1] = nextRows[i];
        }
        nextRows[nextRows.length - 1] = emptyRow();
    }

    static char[] emptyRow() {
        char[] row = new char[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            row[i] = ' ';
        }
        return row;
    }

    //Rohan's gem
    static char[][] getGem () {
        char[][] img = new char[8][10];
        for (int y = 0; y < 8; y = y + 1) {
            for (int x = 0; x < 10; x = x + 1) {
                img[y][x] = ' ';
            }
        }
        img[0][2] = '_';
        img[0][3] = '_';
        img[0][4] = '_';
        img[0][5] = '_';
        img[0][6] = '_';
        img[0][7] = '_';

        img[1][1] = '/';
        img[1][2] = '_';
        img[1][3] = '_';
        img[1][4] = '_';
        img[1][5] = '_';
        img[1][6] = '_';
        img[1][7] = '_';
        img[1][8] = '\\';

        img[2][0] = '/';
        img[2][1] = '/';
        img[2][3] = 'R';
        img[2][4] = 'R';
        img[2][8] = '\\';
        img[2][9] = '\\';

        img[3][0] = '\\';
        img[3][1] = '\\';
        img[3][8] = '/';
        img[3][9] = '/';

        img[4][1] = '\\';
        img[4][2] = '\\';
        img[4][5] = 'R';
        img[4][6] = 'R';
        img[4][7] = '/';
        img[4][8] = '/';

        img[5][2] = '\\';
        img[5][3] = '\\';
        img[5][6] = '/';
        img[5][7] = '/';

        img[6][3] = '\\';
        img[6][4] = '\\';
        img[6][5] = '/';
        img[6][6] = '/';

        img[7][4] = '\\';
        img[7][5] = '/';




        return img;
    }


    //Brendan's burger
    static char[][] getBurger() {
        char[][] img = new char[13][13];
        for (int y = 0; y < 13; y++)
            for (int x = 0; x < 13; x++)
                img[y][x] = ' ';

        img[1][3]  = '-'; img[1][4]  = '-'; img[1][5]  = '-'; img[1][6]  = '-';
        img[1][7]  = '-'; img[1][8]  = '-'; img[1][9]  = '-'; img[1][10] = '-';
        img[2][2]  = '/'; img[2][11] = '\\';
        img[3][1]  = '/'; img[3][12] = '\\';
        img[4][1]  = '|';
        img[4][2]  = '-'; img[4][3]  = '-'; img[4][4]  = '-'; img[4][5]  = '-';
        img[4][6]  = '-'; img[4][7]  = '-'; img[4][8]  = '-'; img[4][9]  = '-';
        img[4][10] = '-'; img[4][11] = '-'; img[4][12] = '|';
        img[6][1]  = '/'; img[6][2]  = '\\'; img[6][3]  = '/'; img[6][4]  = '\\';
        img[6][5]  = '/'; img[6][6]  = '\\'; img[6][7]  = '/'; img[6][8]  = '\\';
        img[6][9]  = '/'; img[6][10] = '\\'; img[6][11] = '/'; img[6][12] = '\\';
        img[7][2]  = '-'; img[7][3]  = '-'; img[7][4]  = '-'; img[7][5]  = '-';
        img[7][6]  = '-'; img[7][7]  = '-'; img[7][8]  = '-'; img[7][9]  = '-';
        img[7][10] = '-'; img[7][11] = '-';
        img[8][1]  = '<'; img[8][12] = '>';
        img[9][2]  = '-'; img[9][3]  = '-'; img[9][4]  = '-'; img[9][5]  = '-';
        img[9][6]  = '-'; img[9][7]  = '-'; img[9][8]  = '-'; img[9][9]  = '-';
        img[9][10] = '-'; img[9][11] = '-';
        img[10][2] = '-'; img[10][3] = '-'; img[10][4] = '-'; img[10][5] = '-';
        img[10][6] = '-'; img[10][7] = '-'; img[10][8] = '-'; img[10][9] = '-';
        img[10][10] = '-'; img[10][11] = '-';
        img[11][1] = '|'; img[11][12] = '|';
        img[12][2] = '-'; img[12][3] = '-'; img[12][4] = '-'; img[12][5] = '-';
        img[12][6] = '-'; img[12][7] = '-'; img[12][8] = '-'; img[12][9] = '-';
        img[12][10] = '-'; img[12][11] = '-';

        return img;
    }

    //my boat
     static char[][] getBoat() {
        char[][] img = new char[10][34];
        // fill with empty space
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 34; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][15] = '|';
        img[0][16] = '_';
        img[0][17] = '_';
        img[0][18] = '_';
        img[0][19] = '|';

        img[1][4] = '*';
        img[1][15] = '|';
        img[1][16] = '_';
        img[1][17] = '_';
        img[1][18] = '_';
        img[1][19] = '|';
        img[1][21] = '|';
        img[1][22] = '\\';
        img[1][26] = '*';
        img[1][28] = '*';

        img[2][3] = '*';
        img[2][9] = '_';
        img[2][10] = '_';
        img[2][11] = '|';
        img[2][12] = '_';
        img[2][13] = '_';
        img[2][15] = '|';
        img[2][16] = '_';
        img[2][17] = '_';
        img[2][18] = '_';
        img[2][19] = '|';
        img[2][21] = '|';
        img[2][23] = '\\';
        img[2][27] = '*';

        img[3][5] = '*';
        img[3][9] = '|';
        img[3][10] = 'o';
        img[3][11] = '_';
        img[3][12] = '_';
        img[3][13] = '|';
        img[3][15] = '|';
        img[3][16] = '_';
        img[3][17] = '_';
        img[3][18] = '_';
        img[3][19] = '|';
        img[3][21] = '|';
        img[3][22] = 'o';
        img[3][24] = '\\';

        img[4][9] = '|';
        img[4][10] = '_';
        img[4][11] = '_';
        img[4][12] = '_';
        img[4][13] = '|';
        img[4][15] = '|';
        img[4][16] = '_';
        img[4][17] = '_';
        img[4][18] = '_';
        img[4][19] = '|';
        img[4][21] = '|';
        img[4][22] = '_';
        img[4][23] = '_';
        img[4][24] = 'o';
        img[4][25] = '\\';

        img[5][1] = '^';
        img[5][2] = '^';
        img[5][8] = '_';
        img[5][9] = '|';
        img[5][10] = '_';
        img[5][11] = '_';
        img[5][12] = '_';
        img[5][13] = '|';
        img[5][17] = '|';
        img[5][21] = '|';
        img[5][30] = '^';
        img[5][31] = '^';
        img[5][32] = '^';
        
        img[6][2] = '^';
        img[6][3] = '^';
        img[6][4] = '^';
        img[6][7] = '/';
        img[6][8] = '.';
        img[6][9] = '.';
        img[6][10] = '.';
        img[6][11] = '\\';
        img[6][12] = '_';
        img[6][13] = '_';
        img[6][14] = '_';
        img[6][15] = '|';
        img[6][16] = '_';
        img[6][17] = '|';
        img[6][18] = '_';
        img[6][19] = '_';
        img[6][20] = '_';
        img[6][21] = '|';
        img[6][22] = '_';
        img[6][23] = '_';
        img[6][24] = '_';
        img[6][25] = '_';
        img[6][26] = '\\';
        img[6][27] = '_';
        img[6][28] = '/';
        img[6][32] = '^';
        img[6][33] = '^';

        img[7][0] = '~';
        img[7][1] = '~';
        img[7][2] = '~';
        img[7][3] = '~';
        img[7][4] = '~';
        img[7][5] = '~';
        img[7][6] = '~';
        img[7][7] = '\\';
        img[7][11] = 'o';
        img[7][13] = '*';
        img[7][15] = 'o';
        img[7][17] = '*';
        img[7][19] = '*';
        img[7][21] = 'o';
        img[7][23] = 'o';
        img[7][26] = '/';
        img[7][27] = '~';
        img[7][28] = '~';
        img[7][29] = '~';
        img[7][30] = '~';
        img[7][31] = '~';
        img[7][32] = '~';
        img[7][33] = '~';

        img[8][17] = '^';
        img[8][18] = '^';
        img[8][19] = '^';
        img[8][20] = '^';
        img[8][21] = '^';
        img[8][22] = '^';
        img[8][23] = '^';

        img[9][3] = '^';
        img[9][4] = '^';
        img[9][5] = '^';
        img[9][11] = '^';
        img[9][12] = '^';
        img[9][13] = '^';
        img[9][27] = '^';
        img[9][28] = '^';
        img[9][29] = '^';

        return img;
    }
    // Catherine's butterfly
     static char[][] getButterfly() {
        char[][]img=new char [15][15];
        for(int y=0; y<15; y++ ){
            for(int x=0;x<15;x++){
                img[y][x]=' ';
            }
        }
        //left wing
        img[1][5]='♥';
        img[1][6]='♥';
        img[1][10]='♥';
        img[1][11]='♥';

        img[2][4]='♥';
        img[2][7]='♥';
        img[2][9]='♥';
        img[2][12]='♥';

        img[3][4]='♥';
        img[3][6]='o';
        img [3][10]='o';
        img[3][12]='♥';
        img[3][8]='♥';

        img [4][5]='♥';
        img[4][8]='-';
        img[4][11]='♥';

        img[5][6]='♥';
        img[5][10]='♥';

        img [6][7]='♥';
        img [6][9]='♥';

        img[7][8]='♥';

        //right wing

        img[14][5]='♥';
        img[14][6]='♥';
        img[14][10]='♥';
        img[14][11]='♥';

        img[13][4]='♥';
        img[13][7]='♥';
        img[13][9]='♥';
        img[13][12]='♥';

        img[12][4]='♥';
        img[12][6]='o';
        img [12][10]='o';
        img[12][12]='♥';
        img[12][8]='♥';

        img [11][5]='♥';
        img[11][8]='-';
        img[11][11]='♥';

        img[10][6]='♥';
        img[10][10]='♥';

        img [9][7]='♥';
        img [9][9]='♥';

        img[8][8]='♥';

        return img;
    }

    // ms. feng's bunny
    static char[][] getBunny() {
        char[][] img = new char[BUNNY_HEIGHT][BUNNY_WIDTH];
        // fill with empty space
        for (int y = 0; y < BUNNY_HEIGHT; y++) {
            for (int x = 0; x < BUNNY_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][0] = '(';
        img[0][1] = '\\';
        img[0][2] = '(';
        img[0][3] = '\\';
        img[1][0] = '(';
        img[1][1] = '-';
        img[1][2] = '.';
        img[1][3] = '-';
        img[1][4] = ')';
        img[2][0] = 'o';
        img[2][1] = '_';
        img[2][2] = '(';
        img[2][3] = '"';
        img[2][4] = ')';
        img[2][5] = '(';
        img[2][6] = '"';
        img[2][7] = ')';
        img[3][1] = '/';
        img[3][4] = '\\';
        img[4][1] = '\\';
        img[4][2] = '\\';
        img[4][3] = '/';
        img[4][4] = '/';
        img[5][2] = '\\';
        img[5][3] = '/';

        return img;
    }


    // ms. feng's carrot
    static char[][] getCarrot() {
        char[][] img = new char[BUNNY_HEIGHT][BUNNY_WIDTH];
        // fill with empty space
        for (int y = 0; y < BUNNY_HEIGHT; y++) {
            for (int x = 0; x < BUNNY_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][5] = '\\';
        img[0][6] = ')';
        img[0][7] = '/';
        img[1][4] = '-';
        img[1][5] = '-';
        img[1][6] = 'v';

        img[2][3] = '/';
        img[2][4] = 'r';
        img[2][6] = ')';

        img[3][2] = '>';
        img[3][3] = 'r';
        img[3][4] = '.';
        img[3][5] = '/';

        img[4][1] = '/';
        img[4][3] = '\'';

        img[5][0] = 'c';
        img[5][1] = '_';
        img[5][2] = '/';

        return img;
    }

    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80; // fallback for unknown OS
        }
    }

    private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    }

}


