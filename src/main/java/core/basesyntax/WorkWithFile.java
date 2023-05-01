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

    public void getStatistic(String fromFileName, String toFileName) {
        File startFile = new File(fromFileName);
        File finalFile = new File(toFileName);
        StringBuilder builder = null;
        if (!startFile.exists()) {
            throw new RuntimeException("File does not exist");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(startFile))) {
            builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String[] lineOfStartFile = builder.toString().split(System.lineSeparator());
            int sumOfBuy = 0;
            int sumOfSupply = 0;
            for (int i = 0; i < lineOfStartFile.length; i++) {
                String[] elementOfLine = lineOfStartFile[i].split(",");
                if (elementOfLine.length != 2) {
                    throw new RuntimeException("Invalid input file format at line " + (i + 1));
                }
                String action = elementOfLine[FIRST_HALF_ARRAY];
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(elementOfLine[SECOND_HALF_ARRAY]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid input file format at line " + (i + 1), e);
                }
                if (action.equals("buy")) {
                    sumOfBuy += quantity;
                } else if (action.equals("supply")) {
                    sumOfSupply += quantity;
                } else {
                    throw new RuntimeException("Invalid action in input file at line " + (i + 1));
                }
            }
            int result = sumOfSupply - sumOfBuy;
            String[] report = new String[SIZE_REPORT];
            report[0] = "supply," + Integer.toString(sumOfSupply);
            report[1] = "buy," + Integer.toString(sumOfBuy);
            report[2] = "result," + Integer.toString(result);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(finalFile))) {
                for (int k = 0; k < report.length; k++) {
                    bufferedWriter.write(report[k] + System.lineSeparator());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
