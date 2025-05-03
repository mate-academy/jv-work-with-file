package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int INDEX_DATA = 0;
    private static final int INDEX_MONEY = 1;
    private static final String COMMA = ",";
    private static final String SEPARATOR = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);

    }

    public String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            String data = builder.toString();
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public String createReport(String data) {
        StringBuilder value = new StringBuilder();
        int amountSupply = 0;
        int amountBuy = 0;
        String[] firstSplitFile = data.split(SEPARATOR);
        for (String element : firstSplitFile) {
            String[] splitFile = element.split(COMMA);
            if (splitFile[INDEX_DATA].equals(SUPPLY)) {
                amountSupply += Integer.parseInt(splitFile[INDEX_MONEY]);
            }
            if (splitFile[INDEX_DATA].equals(BUY)) {
                amountBuy += Integer.parseInt(splitFile[INDEX_MONEY]);
            }
        }
        int totalAmount = amountSupply - amountBuy;
        return value.append(SUPPLY).append(COMMA)
                .append(amountSupply)
                .append(SEPARATOR)
                .append(BUY).append(COMMA)
                .append(amountBuy)
                .append(SEPARATOR)
                .append("result,")
                .append(totalAmount).toString();
    }

    public void writeToFile(String report, String toFileName) {
        File writeFile = new File(toFileName);
        try {
            Files.write(writeFile.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }
}
