package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public int[] readFromFile(String fileName) {
        int supply = 0;
        int buy = 0;
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String row = bufferedReader.readLine();
            while (row != null) {
                String[] data = row.split(",");
                if (data[0].equals("supply")) {
                    supply += Integer.parseInt(data[1]);
                } else {
                    buy += Integer.parseInt(data[1]);
                }
                row = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }
        return new int[]{supply, buy};
    }
}
