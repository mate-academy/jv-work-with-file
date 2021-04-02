package core.basesyntax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class WorkWithFile {
    private static final String FIRST_OPERATION_NAME = "supply";
    private static final String SECOND_OPERATION_NAME = "buy";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textLines = getInputText(fromFileName).split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : textLines) {
            String[] lineInfo = line.split(DELIMITER);
            if (lineInfo[0].equals(FIRST_OPERATION_NAME)) {
                supplyAmount += Integer.parseInt(lineInfo[1]);
            } else {
                buyAmount += Integer.parseInt(lineInfo[1]);
            }
        }
        String inputText = getReportText(supplyAmount, buyAmount);
        writeToFile(toFileName, inputText);
    }

    private void writeToFile(String fileName, String inputText) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }
        try {
            Files.write(file.toPath(), inputText.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write a file", e);
        }
    }

    private String getReportText(int supplyAmount, int buyAmount) {
        StringBuilder report = new StringBuilder();
        report.append(FIRST_OPERATION_NAME)
                .append(DELIMITER)
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append(SECOND_OPERATION_NAME)
                .append(DELIMITER)
                .append(buyAmount)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyAmount - buyAmount);
        return report.toString();
    }

    private String getInputText(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            StringBuilder sourceTextBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                sourceTextBuilder.append(scanner.nextLine())
                        .append(System.lineSeparator());
            }
            return sourceTextBuilder.toString().trim();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read a file", e);
        }
    }
}
