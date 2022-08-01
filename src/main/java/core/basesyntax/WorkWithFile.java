package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = getDataFromFile(fromFileName);
        String report = calculate(data.split("\\W+"));
        writeReportToFile(report, toFileName);
    }

    private String calculate(String[] cells) {
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < cells.length; i += 2) {
            switch (cells[i]) {
                case SUPPLY: {
                    supply += Integer.parseInt(cells[i + 1]);
                    break;
                }
                case BUY: {
                    buy += Integer.parseInt(cells[i + 1]);
                    break;
                }
                default: {
                    System.out.println("Incorrect file data.");
                    break;
                }
            }
        }
        return createReport(supply, buy);
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(SEPARATOR).append(supply).append(System.lineSeparator());
        builder.append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator());
        builder.append(RESULT).append(SEPARATOR).append(result);
        return builder.toString();
    }

    private String getDataFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String value = br.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
