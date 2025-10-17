public class ScrollArtV1 {
    static final int BOAT_WIDTH = 34;
    static final int BOAT_HEIGHT = 10;
    static final int WIDTH = getTerminalWidth() - BOAT_WIDTH;

   
public static void main(String[] args) throws InterruptedException {
    char[][] boat = getBoat();
    int x = 0;                  // boat’s horizontal position
    int direction = 1;          // 1 = right, -1 = left

    while (true) {
        // print the boat
        for (int y = 0; y < BOAT_HEIGHT; y++) {
            // print x spaces to move the boat around
            for (int i = 0; i < x; i++) System.out.print(' ');
            System.out.println(new String(boat[y]));
        }
        // move the boat
        x += direction;
        // if it hits a wall, reverse direction
        if (x >= WIDTH || x <= 0) {
            direction *= -1;
        }
        Thread.sleep(60);
    }
}

    // rename your function here
   
    static char[][] getBoat() {
        char[][] img = new char[BOAT_HEIGHT][BOAT_WIDTH];
        // fill with empty space
        for (int y = 0; y < BOAT_HEIGHT; y++) {
            for (int x = 0; x < BOAT_WIDTH; x++) {
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
