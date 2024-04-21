package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SYMBOL = ",";
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        readCalculateFile(fromFileName);
        String report = createReport();
        writeFile(toFileName, report);
    }

    private void readCalculateFile(String fromFileName) {
        supply = 0;
        buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentString = bufferedReader.readLine();
            while (currentString != null) {
                String[] splitedLine = currentString.split(COMMA_SYMBOL);
                int operationAmount = Integer.parseInt(splitedLine[1]);
                String operation = splitedLine[0];
                if (operation.equals(SUPPLY)) {
                    supply += operationAmount;
                } else {
                    buy += operationAmount;
                }
                currentString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
    }

    private String createReport() {
        StringBuilder report = new StringBuilder();

        int result = supply - buy;
        report.append(SUPPLY)
                .append(COMMA_SYMBOL)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA_SYMBOL)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA_SYMBOL)
                .append(result);

        return report.toString();
    }

    private void writeFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
