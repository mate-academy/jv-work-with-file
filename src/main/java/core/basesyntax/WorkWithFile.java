package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readenData = readFile(new File(fromFileName));
        int[] report = calculateResult(readenData);

        writeToFile(new File(toFileName), report);
    }

    private void writeToFile(File file, int[] report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            String result = new WorkWithFile().formResult(report);
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + file, e);
        }
    }

    private String formResult(int[] report) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SUPPLY_NAME)
                .append(SEPARATOR)
                .append(report[0])
                .append(System.lineSeparator());

        stringBuilder.append(BUY_NAME)
                .append(SEPARATOR)
                .append(report[1])
                .append(System.lineSeparator());

        stringBuilder.append(RESULT_NAME)
                .append(SEPARATOR)
                .append(report[0] - report[1]);
        return stringBuilder.toString();
    }

    private String[] readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                stringBuilder.append(currentLine).append(System.lineSeparator());
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + file, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private int[] calculateResult(String[] arrOfData) {
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < arrOfData.length; i++) {
            String[] splittedData = arrOfData[i].split(",");
            if (splittedData[0].equals(SUPPLY_NAME)) {
                supply += Integer.parseInt(splittedData[1]);
            }
            if (splittedData[0].equals(BUY_NAME)) {
                buy += Integer.parseInt(splittedData[1]);
            }
        }
        return new int[]{supply, buy};
    }
}
