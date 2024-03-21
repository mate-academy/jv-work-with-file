package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    private static final int ARRAY_VALUE_INDEX = 1;
    private static final int ARRAY_NAME_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File fileCopy = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(fileCopy)) {
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.next()).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    private String generateReport(String string) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] lines = string.split(System.lineSeparator());
        for (String item : lines) {
            String[] array = item.split(",");
            if (array[ARRAY_NAME_INDEX].equals("supply")) {
                totalSupply += Integer.parseInt(array[ARRAY_VALUE_INDEX]);
            } else {
                totalBuy += Integer.parseInt(array[ARRAY_VALUE_INDEX]);
            }
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(totalSupply).append(System.lineSeparator());
        reportBuilder.append("buy,").append(totalBuy).append(System.lineSeparator());
        reportBuilder.append("result,").append(totalSupply - totalBuy)
                .append(System.lineSeparator());
        return reportBuilder.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
