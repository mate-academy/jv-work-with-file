package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readData(fromFileName)), toFileName);
    }

    public String readData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String readData) {
        int allBuy = 0;
        int allSupply = 0;
        StringBuilder result = new StringBuilder();
        String[] dataFile = readData.split(LINE_SEPARATOR);
        for (String datum : dataFile) {
            String[] data = datum.split(SEPARATOR);
            int value = Integer.parseInt(data[INDEX]);
            String operation = data[0];
            if (operation.equals(BUY)) {
                allBuy += value;
            } else {
                allSupply += value;
            }
        }
        return result.append(SUPPLY).append(SEPARATOR).append(allSupply).append(LINE_SEPARATOR)
                .append(BUY).append(SEPARATOR).append(allBuy).append(LINE_SEPARATOR)
                .append(RESULT).append(SEPARATOR).append(allSupply - allBuy).toString();
    }

    public void writeToFile(String createdReport, String toFileName) {
        try {
            Files.writeString(Path.of(toFileName), createdReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
