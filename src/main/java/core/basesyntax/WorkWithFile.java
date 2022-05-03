package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] operationTypeArray = {"supply", "buy", "result"};

    public void getStatistic(String fromFileName, String toFileName) {
        int[] reportNumberArray;
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        String[] splitStatistic = readFromFile(fromFileName).split("\\W");
        for (int i = 0; i < splitStatistic.length / 2; i++) {
            if (splitStatistic[i * 2].equals("supply")) {
                sumOfSupply += Integer.parseInt(splitStatistic[i * 2 + 1]);
            } else {
                sumOfBuy += Integer.parseInt(splitStatistic[i * 2 + 1]);
            }
        }
        int result = sumOfSupply - sumOfBuy;
        reportNumberArray = new int[]{sumOfSupply, sumOfBuy, result};
        String report = createReport(operationTypeArray, reportNumberArray);
        createFile(toFileName);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private void createFile(String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file: " + fileName, e);
        }
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }

    private String createReport(String[] operationTypeArray, int[] reportNumberArray) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < operationTypeArray.length; i++) {
            builder.append(operationTypeArray[i])
                    .append(",")
                    .append(reportNumberArray[i])
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
