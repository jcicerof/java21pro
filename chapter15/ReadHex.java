import java.io.*;

public class ReadHex {
    public static void main(String[] arguments) {
        ReadHex hex = new ReadHex();
        hex.readFile();
    }

    void readFile() {
        try {
            FileReader file = new
                FileReader("hexfile.dat");
            BufferedReader buff = new
                BufferedReader(file);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null)
                   eof = true;
                else
                   readLine(line);
            }
            buff.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }

    void readLine(String code) {
        try {
            for (int j = 0; j + 1 < code.length(); j += 2) {
                String sub = code.substring(j, j+2);
                int num = Integer.parseInt(sub, 16);
                if (num == 255)
                    return;
                System.out.print(num + " ");
            }
        } finally {
            System.out.println("**");
        }
        return;
    }
}
