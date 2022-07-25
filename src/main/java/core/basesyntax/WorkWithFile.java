package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] report = createReport(readFromFile(fromFileName));
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data = reader.readLine();
            while (data != null) {
                builder.append(data).append(" ");
                data = reader.readLine();
            }
            return builder.toString().split(" ");
        } catch (IOException e) {
            throw new RuntimeException("Cant read file ");
        }
    }

    private String[] createReport(String[] text) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        for (String lines : text) {
            String[] splittedLine = lines.split(",");
            if (splittedLine[OPERATION_TYPE_INDEX].equals("buy")) {
                buy = buy + Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
            if (splittedLine[OPERATION_TYPE_INDEX].equals("supply")) {
                supply = supply + Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
            result = supply - buy;
        }
        return new String[]{"supply," + supply, "buy," + buy, "result," + result};
    }

    private void writeToFile(String[] report, String toFileName) {
        File fileResult = new File(toFileName);
        for (String reportLines : report) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(fileResult, true))) {
                bufferedWriter.write(reportLines);
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException("Cant write to file: " + toFileName);
            }

        }
    }
}
