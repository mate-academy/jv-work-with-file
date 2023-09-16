package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String ROW_SEPARATOR = " ";
    private static final String WORD_SEPARATOR = ",";
    private String[] readData;
    private String report;

    public void getStatistic(String fromFileName, String toFileName) {
        readData(fromFileName);
        makeReport(readData);
        writeData(toFileName);
    }

    public void readData(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(ROW_SEPARATOR);
                value = reader.readLine();
            }
            this.readData = builder.toString().split(ROW_SEPARATOR);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
    }

    public void makeReport(String[] rodeData) {
        int supply = 0;
        int buy = 0;
        for (String row : readData) {
            String[] splitedRow = row.split(WORD_SEPARATOR);
            switch (splitedRow[0]) {
                case "supply":
                    supply += Integer.parseInt(splitedRow[1]);
                    break;
                case "buy":
                    buy += Integer.parseInt(splitedRow[1]);
                    break;
                default:
                    break;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        this.report = builder.toString();
    }

    public void writeData(String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }
}
