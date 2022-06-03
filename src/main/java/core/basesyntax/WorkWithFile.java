package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SPLITTER = "&";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] tableRow;
        String[] data = readFile(new File(fromFileName));
        for (int i = 0; i < data.length; i++) {
            tableRow = data[i].split(COMMA);
            if (tableRow[OPERATION_INDEX].equals("supply")) {
                totalSupply += Integer.parseInt(tableRow[AMOUNT_INDEX]);
            } else {
                totalBuy += Integer.parseInt(tableRow[AMOUNT_INDEX]);
            }
        }
        buildReport(totalSupply,totalBuy,toFileName);
    }

    private String buildReport(int totalSupply, int totalBuy, String toFileName) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply").append(COMMA).append(totalSupply).append(LINE_SEPARATOR)
                .append("buy").append(COMMA).append(totalBuy).append(LINE_SEPARATOR)
                .append("result").append(COMMA).append(totalSupply - totalBuy);
        writeFile(new File(toFileName), builder.toString());
        return builder.toString();
    }

    private String[] readFile(File file) {
        StringBuilder builder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(SPLITTER);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + file, e);
        }
        return builder.toString().split(SPLITTER);
    }

    private void writeFile(File file,String buildReport) {
        try {
            Files.write(file.toPath(), buildReport.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + file, e);
        }
    }
}
