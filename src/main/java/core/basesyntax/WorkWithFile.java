package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String COMMA_SEPARATED = ",";
    private static final String SPACE_SEPARATED = " ";
    private static final String OPERATION_NAME = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] data = readFile(fromFileName);
        String report = getReport(data);
        writeFile(toFileName, report);
    }

    private String [] readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(SPACE_SEPARATED);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return builder.toString().split(SPACE_SEPARATED);
    }

    private String getReport(String [] data) {
        int supplySum = 0;
        int buySum = 0;
        for (String element: data) {
            String[] stringSeparated = element.split(COMMA_SEPARATED);
            if (stringSeparated[OPERATION_INDEX].equals(OPERATION_NAME)) {
                supplySum += Integer.parseInt(stringSeparated[AMOUNT_INDEX]);
            } else {
                buySum += Integer.parseInt(stringSeparated[AMOUNT_INDEX]);
            }
        }
        return "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum) + System.lineSeparator();
    }

    private void writeFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
