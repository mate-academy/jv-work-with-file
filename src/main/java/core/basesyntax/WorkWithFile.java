package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final int ZERO_INDEX = 0;
    public static final int FIRST_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] stringFromFile = readFile(fromFileName);
        String reportAsString = makeReport(stringFromFile);
        writeFile(reportAsString,toFileName);
    }

    private String[] readFile(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String makeReport(String[] file) {
        int supplyAmount = ZERO_INDEX;
        int buyAmount = ZERO_INDEX;
        for (String line : file) {
            String[] element = line.split(COMMA);
            if (element[ZERO_INDEX].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(element[FIRST_INDEX]);
            }
            if (element[ZERO_INDEX].equals(BUY)) {
                buyAmount += Integer.parseInt(element[FIRST_INDEX]);
            }
        }
        int result = supplyAmount - buyAmount;
        return SUPPLY + COMMA + supplyAmount + System.lineSeparator()
                + BUY + COMMA + buyAmount + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private void writeFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file ", e);
        }
    }
}
