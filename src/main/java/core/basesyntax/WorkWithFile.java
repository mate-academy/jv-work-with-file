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
    private static final int OPERATION_POSITION = 0;
    private static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textLines = getInputText(fromFileName).split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : textLines) {
            String[] lineInfo = line.split(DELIMITER);
            if (lineInfo[OPERATION_POSITION].equals(FIRST_OPERATION_NAME)) {
                supplyAmount += Integer.parseInt(lineInfo[AMOUNT_POSITION]);
            } else {
                buyAmount += Integer.parseInt(lineInfo[AMOUNT_POSITION]);
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
            throw new RuntimeException("Can't create the file " + fileName, e);
        }
        try {
            Files.write(file.toPath(), inputText.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file" + fileName, e);
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
            throw new RuntimeException("Can't read the file " + fileName, e);
        }
    }
}
