
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class C6521test {

    public static void main(String[] args) {
        String filename = "sample1";

        try {
            System.out.println("Opening file " + filename);

            Path file = Paths.get(filename);
            System.out.println(file.toAbsolutePath());
            BlockAccess ba = new BlockAccess(file);
            int count = ba.getBlockCount();

            System.out.println("File opened with " + Integer.toString(count) + " blocks");

            System.out.println("Sorting Blocks");
            Block b;
            for (int i = 0; i < count; i++) {
                b = ba.read(i);
                b.sort();
                ba.write(i, b);
                System.out.print(".");
            }
            System.out.println(" Done!");

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
