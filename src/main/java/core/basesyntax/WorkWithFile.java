package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFile(fromFileName);
        StringBuilder report = createReport(data);
        writeToFile(report, toFileName);
    }

    private int[] readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder readData = new StringBuilder();
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                readData.append(value).append(System.lineSeparator());
            }
            String[] splitReadData = readData.toString().split(System.lineSeparator());
            int supply = 0;
            int buy = 0;
            for (String spitData : splitReadData) {
                String[] findStatistic = spitData.split(SEPARATOR);
                if (findStatistic[OPERATION_INDEX].equals("supply")) {
                    supply += Integer.parseInt(findStatistic[AMOUNT_INDEX]);
                } else if (findStatistic[OPERATION_INDEX].equals("buy")) {
                    buy += Integer.parseInt(findStatistic[AMOUNT_INDEX]);
                }
            }
            return new int[]{supply, buy};
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open the file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private StringBuilder createReport(int[] data) {
        int supply = data[SUPPLY_INDEX];
        int buy = data[BUY_INDEX];
        int result = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator());
        report.append(BUY).append(COMMA).append(buy).append(System.lineSeparator());
        report.append(RESULT).append(COMMA).append(result);
        return report;
    }

    private void writeToFile(StringBuilder report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file " + toFileName, e);
        }
    }
}
