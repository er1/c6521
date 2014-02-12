
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("freeMemory(): " + Long.toString(Runtime.getRuntime().freeMemory()));

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

        File file = new File(filename);
        
        try {
            TPMMS.TPMMS(file, readBlocks);
        } catch (IOException ex) {
        }

        System.out.println("freeMemory(): " + Long.toString(Runtime.getRuntime().freeMemory()));
    }
}
