import java.nio.file.*;
import java.io.*;

public class MyDir {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: Java MyDir 目录 [扩展名]");
            System.exit(0);
        }
        Path dirPath = Paths.get(args[0]);
        DirectoryStream<Path> directory = null;
        try {
            if (args.length == 1) {
                directory = Files.newDirectoryStream(dirPath);
            } else {
                directory = Files.newDirectoryStream(dirPath, "*{" + args[1] + "}");
            }
            for (Path p : directory) {
                System.out.println(p.getFileName());
            }
        } catch (Exception e) {
            System.out.println("Invalid path specified:" + args[0]);
        } finally {
            try {
                if (directory != null) {
                    directory.close();
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }
}