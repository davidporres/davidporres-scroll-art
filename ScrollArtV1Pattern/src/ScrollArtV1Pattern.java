import java.util.Random;

public class ScrollArtV1Pattern {
    static final int WIDTH = getTerminalWidth() - 1;
    static final Random rand = new Random();
   
    public static void main(String[] args) throws InterruptedException {

        int x = WIDTH / 2;   //start inthe middle

        while (true) {
            // build one row
            for (int i = 0; i < WIDTH; i++) {
                if (i == x) System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();

            // delay so you can see it
            Thread.sleep(60);

            // either left, right, or stay
            double r = Math.random();
            if (r < 0.33333 && x > 0) x--;
            else if (r > 0.66666 && x < WIDTH - 1) x++;
        }
    
    }



    public static int getNullIndex(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return i;
            }
        }
        return -1; // no null found
    }


    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else if (os.contains("win")) {
            return getWindowsTerminalWidth();
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

    private static int getWindowsTerminalWidth() {
        // Github Copilot (Claude Sonnet 4) attempted to get columns.
        // Did not work on parallels.
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Try using PowerShell with different approaches
            // Method 1: Try to get console window size directly
            ProcessBuilder pb = new ProcessBuilder("powershell", "-Command",
                    "[Console]::WindowWidth");
            // also (Get-Host).UI.RawUI.WindowSize.Width didn't work
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                try {
                    return Integer.parseInt(output.trim());
                } catch (NumberFormatException ignored) {
                    // Continue to next method if parsing fails
                }
            }

        } catch (Exception e) {
            // If PowerShell fails, try using cmd with mode command
            try {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "mode con");
                pb.redirectErrorStream(true);
                Process process = pb.start();
                java.io.BufferedReader reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Columns:")) {
                        String[] parts = line.split(":");
                        if (parts.length > 1) {
                            try {
                                return Integer.parseInt(parts[1].trim());
                            } catch (NumberFormatException ignored) {
                                // Continue to fallback
                            }
                        }
                    }
                }
            } catch (Exception ignored) {
                // Silently ignore errors and fall back to default
            }
        }
        return 80; // fallback
    }

}