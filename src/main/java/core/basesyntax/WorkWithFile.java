package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String COMA = ",";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";
    public static final int BUY_INDEX = 1;
    public static final int SUPPLY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readFile(fromFileName));
        writeToFile(toFileName,report);
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
            bufferedReader.close();
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException exception) {
            throw new RuntimeException("Can't read data from the file " + fromFileName,exception);
        }
    }

    private String createReport(String[] data) {
        int buy = 0;
        int supply = 0;
        for (String s : data) {
            if (s.split(COMA)[0].equals(BUY)) {
                buy += Integer.parseInt(s.split(COMA)[BUY_INDEX]);
            } else {
                supply += Integer.parseInt(s.split(COMA)[SUPPLY_INDEX]);
            }
        }
        int result = supply - buy;
        return SUPPLY + COMA + supply + System.lineSeparator()
                + BUY + COMA + buy + System.lineSeparator()
                + RESULT + COMA + result;
    }

    private void writeToFile(String toFileName, String data) {
        try {
            FileWriter toFile = new FileWriter(toFileName);
            toFile.write(data);
            toFile.close();
        } catch (IOException exception) {
            throw new RuntimeException("Can't write to file" + toFileName, exception);
        }
    }
}
