package core.basesyntax;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;

public class WorkWithFile {
    private static final String FIRST_OPERATION_NAME = "supply";
    private static final String SECOND_OPERATION_NAME = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textLines = getInputText(fromFileName).split(System.lineSeparator());
        int[] amountCounter = new int[2]; //supply - index 0, buy - index 1
        for (String line : textLines) {
            String[] lineInfo = line.split(",");
            if (lineInfo[0].equals(FIRST_OPERATION_NAME)) {
                amountCounter[0] += Integer.parseInt(lineInfo[1]);
            } else {
                amountCounter[1] += Integer.parseInt(lineInfo[1]);
            }
        }
        String inputText = getReportText(amountCounter);
        writeToFile(toFileName, inputText);
    }

    private static void writeToFile(String fileName, String inputText) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (java.io.IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }
        try {
            Files.write(file.toPath(), inputText.getBytes());
        } catch (java.io.IOException e) {
            throw new RuntimeException("Can't write a file", e);
        }
    }

    private String getReportText(int[] amountCounter) {
        StringBuilder report = new StringBuilder();
        report.append(FIRST_OPERATION_NAME)
                .append(",")
                .append(amountCounter[0])
                .append(System.lineSeparator())
                .append(SECOND_OPERATION_NAME)
                .append(",")
                .append(amountCounter[1])
                .append(System.lineSeparator())
                .append("result,")
                .append(amountCounter[0] - amountCounter[1]);
        return report.toString();
    }

    private static String getInputText(String fileName) {
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
