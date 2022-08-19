package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int NEXT_INDEX = 1;
    static final String SUPPLY = "supply";
    static final String BUY = "buy";
    static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {

        String[] arrayData = getArrayData(fromFileName);
        String report = makeReport(arrayData);
        writeToFile(report, toFileName);
    }

    private String[] getArrayData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
        return stringBuilder.toString().split(",");
    }

    private String makeReport(String[] arrayData) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < (arrayData.length - NEXT_INDEX); i++) {
            if (arrayData[i].equals(SUPPLY)) {
                supply += Integer.parseInt(arrayData[i + NEXT_INDEX]);
            } else if (arrayData[i].equals(BUY)) {
                buy += Integer.parseInt(arrayData[i + NEXT_INDEX]);
            }
        }
        return SUPPLY + "," + supply + System.lineSeparator() + BUY + ","
                + buy + System.lineSeparator() + RESULT + "," + (supply - buy);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
    }
}
