
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String filename = "sample1";
        int readBlocks = 10;

        Scanner s = new Scanner(System.in);
        String in;

        // Get file to manipulate
        System.out.print("File to sort [" + filename + "]:");
        in = s.nextLine().trim();

        if (in.length() > 0) {
            filename = in;
        }

        // Get Number of ReaderBlocks
        System.out.print("File t [" + Integer.toString(readBlocks) + "]:");
        in = s.nextLine().trim();

        try {
            readBlocks = Integer.parseInt(in);
        } catch (NumberFormatException e) {
        }

        Path file = Paths.get(filename);

        try {
            TPMMS.TPMMS(file, 10);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
