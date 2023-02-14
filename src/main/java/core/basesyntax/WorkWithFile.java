package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        for (String s : readFile(fromFileName)) {
            if (s.split(",")[0].equals(BUY)) {
                buy += Integer.parseInt(s.split(",")[1]);
            } else {
                supply += Integer.parseInt(s.split(",")[1]);
            }
        }
        int result = supply - buy;
        String data = SUPPLY + "," + supply + "\n" + BUY + "," + buy + "\n" + RESULT + "," + result;
        writeFile(toFileName,data);
    }

    private String[] readFile(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char)value);
                value = bufferedReader.read();
            }
            return stringBuilder.toString().split("\n");
        } catch (IOException exception) {
            throw new RuntimeException("Can't read data from the file " + fromFileName,exception);
        }
    }

    private void writeFile(String toFileName, String data) {
        try {
            FileWriter toFile = new FileWriter(toFileName);
            toFile.write(data);
            toFile.close();
        } catch (IOException exception) {
            throw new RuntimeException("Can't write to file" + toFileName,exception);
        }
    }
}
