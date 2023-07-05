package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA_SEPARATOR = ",";
    private static final int VALUE_INDEX = 1;
    private static final int OPERATION_TYPE_INDEX = 0;

    protected void getStatistic(String fromFileName, String toFileName) {
        String[] fileInfo = this.readFromFile(fromFileName);
        int supplySum = 0;
        int buySum = 0;
        for (String oneLine : fileInfo) {
            String[] stringInfo = oneLine.split(COMA_SEPARATOR);
            if (stringInfo[OPERATION_TYPE_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(stringInfo[VALUE_INDEX]);
            } else {
                buySum += Integer.parseInt(stringInfo[VALUE_INDEX]);
            }
        }
        String toFileInfo = createReport(supplySum, buySum);
        this.writeToFile(toFileName, toFileInfo);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new java.io.FileReader(fromFileName))) {
            String fromText = bufferedReader.readLine();
            while (fromText != null) {
                stringBuilder.append(fromText).append(System.lineSeparator());
                fromText = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(int supplySum, int buySum) {
        return "supply," + supplySum + System.lineSeparator() + "buy,"
                + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum) + System.lineSeparator();
    }

    private void writeToFile(String toFileName, String toFileInfo) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(toFileInfo);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
