package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile implements Reportable {
    public static final String SPLIT_BY = ",";
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = getReport(fromFileName);
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.append(report);
        } catch (IOException e) {
            throw new RuntimeException("This file can't be write", e);
        }
    }

    @Override
    public String getReport(String fromFileName) {
        String line;
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] suppAndBuy = line.split(SPLIT_BY);
                if (suppAndBuy[FIRST_INDEX].equals("supply")) {
                    supply += Integer.parseInt(suppAndBuy[SECOND_INDEX]);
                } else {
                    buy += Integer.parseInt(suppAndBuy[SECOND_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("This file can't be read", e);
        }
        return new StringBuilder().append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator()).toString();
    }
}
