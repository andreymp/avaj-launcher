package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private static FileWriter writer;

    static {
        try {
            writer = new FileWriter(new File("simulation.txt"));
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void write(String message) {
        try {
            writer.write(message);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            writer.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

}