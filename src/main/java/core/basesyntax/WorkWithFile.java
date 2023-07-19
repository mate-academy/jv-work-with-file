package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String[] dataFromFile = readDataFromFile(fromFileName);
        for (int i = 0; i < dataFromFile.length; i += 2) {
            switch (dataFromFile[i]) {
                case OPERATION_SUPPLY:
                    supply += Integer.parseInt(dataFromFile[i + 1]);
                    break;
                case OPERATION_BUY:
                    buy += Integer.parseInt(dataFromFile[i + 1]);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + dataFromFile[i]);
            }
        }
        String statisticData = OPERATION_SUPPLY + "," + supply + System.lineSeparator()
                + OPERATION_BUY + "," + buy + System.lineSeparator()
                + OPERATION_RESULT + "," + (supply - buy);
        writeDataToFile(toFileName, statisticData);
    }

    private String[] readDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String stringLine = bufferedReader.readLine();
            while (stringLine != null) {
                stringBuilder.append(stringLine).append(System.lineSeparator());
                stringLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringBuilder.toString().split("\\W+");
    }

    private void writeDataToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
