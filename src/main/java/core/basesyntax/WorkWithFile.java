package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_ARRAY = 0;
    private static final int SECOND_ARRAY = 1;
    private static final int REPORTSIZE = 3;

    public void getStatistic(String fromFileName, String toFileName) {
        File originalFile = new File(fromFileName);
        File reportFile = new File(toFileName);
        StringBuilder builder;
        if (!originalFile.exists()) {
            throw new RuntimeException("File does not exist");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile))) {
            builder = new StringBuilder();
            String content = reader.readLine();
            while (content != null) {
                builder.append(content).append(System.lineSeparator());
                content = reader.readLine();
            }
            String[] lineOfStartFile = builder.toString().split(System.lineSeparator());
            int buySum = 0;
            int supplySum = 0;
            for (String value : lineOfStartFile) {
                String[] elementOfLine = value.split(",");
                if (elementOfLine.length != 2) {
                    throw new RuntimeException("Invalid input file format");
                }
                String action = elementOfLine[FIRST_ARRAY];
                int quantity;
                try {
                    quantity = Integer.parseInt(elementOfLine[SECOND_ARRAY]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid input file format ", e);
                }
                if (action.equals("buy")) {
                    buySum += quantity;
                } else if (action.equals("supply")) {
                    supplySum += quantity;
                } else {
                    throw new RuntimeException("Invalid input");
                }
            }
            int result = supplySum - buySum;
            String[] report = new String[REPORTSIZE];
            report[0] = "supply," + supplySum;
            report[1] = "buy," + buySum;
            report[2] = "result," + result;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
                for (String s : report) {
                    bufferedWriter.write(s + System.lineSeparator());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
