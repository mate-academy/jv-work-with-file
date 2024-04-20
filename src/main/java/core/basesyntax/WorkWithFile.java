package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String STR_SUPPLY = "supply,";
    private static final String STR_BUY = "buy,";
    private static final String STR_RESULT = "result,";
    private static final String COMMA_SYMBOL = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder report = new StringBuilder();

        readCalculateFile(fromFileName, report);
        writeFile(toFileName, report);
    }

    public void readCalculateFile(String fromFileName, StringBuilder report) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentString = bufferedReader.readLine();
            while (currentString != null) {
                String[] splitedLine = currentString.split(COMMA_SYMBOL);
                int operationAmount = Integer.parseInt(splitedLine[1]);
                String operation = splitedLine[0];
                if (operation.equals("supply")) {
                    supply += operationAmount;
                } else {
                    buy += operationAmount;
                }
                currentString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        createReport(supply, buy, report);
    }

    public void createReport(int supply, int buy, StringBuilder report) {
        int result = supply - buy;
        report.append(STR_SUPPLY)
                .append(supply)
                .append(System.lineSeparator())
                .append(STR_BUY)
                .append(buy)
                .append(System.lineSeparator())
                .append(STR_RESULT)
                .append(result);
    }

    public void writeFile(String toFileName, StringBuilder report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
