package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_HALF_ARRAY = 0;
    private static final int SECOND_HALF_ARRAY = 1;
    private static final int SIZE_REPORT = 3;
    private static final String AMOUND_INDEX = "buy";
    private static final String ACTION_INDEX = "supply";
    private static final String SYMBOL_THAT_SAPARATE_ELEMENTS = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String doneData = readFile(fromFileName);
        String readyReport = operationWithFile(doneData);
        writeToFile(readyReport, toFileName);
    }

    private String readFile(String fromFileName) {
        File startFile = new File(fromFileName);
        StringBuilder builder = null;
        if (!startFile.exists()) {
            throw new RuntimeException("File fromFileName does not exist");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(startFile))) {
            builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read fromFileName", e);
        }
        return builder.toString();
    }

    private String operationWithFile(String builder) {
        String[] line = builder.toString().split(System.lineSeparator());
        int sumOfBuy = 0;
        int sumOfSupply = 0;
        for (int i = 0; i < line.length; i++) {
            if (Character.isDigit(line[i].charAt(0))) {
                String[] elementsStartWithNumber = line[i].split("(?<=\\d)(?=\\D)");
                if (elementsStartWithNumber[SECOND_HALF_ARRAY].equals(AMOUND_INDEX)) {
                    sumOfBuy += Integer.parseInt(elementsStartWithNumber[FIRST_HALF_ARRAY]);
                } else if (elementsStartWithNumber[SECOND_HALF_ARRAY].equals(ACTION_INDEX)) {
                    sumOfSupply += Integer.parseInt(elementsStartWithNumber[FIRST_HALF_ARRAY]);
                }
            }
            String[] elementOfLine = line[i].split(SYMBOL_THAT_SAPARATE_ELEMENTS);
            String action = elementOfLine[FIRST_HALF_ARRAY];
            int quantity = 0;
            try {
                quantity = Integer.parseInt(elementOfLine[SECOND_HALF_ARRAY]);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid input file format at line " + (i + 1), e);
            }
            if (action.equals(AMOUND_INDEX)) {
                sumOfBuy += quantity;
            } else if (action.equals(ACTION_INDEX)) {
                sumOfSupply += quantity;
            } else {
                throw new RuntimeException("Invalid action in input file at line " + (i + 1));
            }
        }
        int result = sumOfSupply - sumOfBuy;
        String report = "supply," + Integer.toString(sumOfSupply) + System.lineSeparator()
                + "buy," + Integer.toString(sumOfBuy) + System.lineSeparator()
                + "result," + Integer.toString(result) + System.lineSeparator();
        return report;
    }

    private void writeToFile(String report, String toFileName) {
        File finalFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(finalFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("can't close file", e);
        }
    }
}
