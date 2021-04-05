package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readDataFromFile = readDataFromFile(fromFileName);
        writeDataFromFile(readDataFromFile, toFileName);
    }

    private void writeDataFromFile(String dataToFile, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(dataToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fileName, e);
        }
    }

    private String readDataFromFile(String fileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] value = readLine.split(COMMA);
                if (value[0].equals(BUY)) {
                    buy += Integer.parseInt(value[1]);
                } else {
                    supply += Integer.parseInt(value[1]);
                }
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }
}
