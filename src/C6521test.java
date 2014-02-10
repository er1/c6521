
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class C6521test {

    public static void main(String[] args) {
        try {
            Path file = Paths.get("sample1");
            System.out.println(file.toAbsolutePath());
            BlockAccess ba = new BlockAccess(file);
            int count = ba.getBlockCount();

            Block b;

            for (int i = 0; i < count; i++) {
                b = ba.read(i);
                b.sort();
                ba.write(i, b);
            }

        } catch (IOException ex) {
        }
        System.out.println("...");
    }
}
