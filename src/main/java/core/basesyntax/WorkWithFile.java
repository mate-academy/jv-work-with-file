package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_OF_CATEGORY = 0;
    private static final int INDEX_OF_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File baseFile = new File(fromFileName);
        File reportFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile, true))) {
            List<String> data = Files.readAllLines(baseFile.toPath());
            writer.write(makeReport("supply","buy",data));
        } catch (IOException e) {
            throw new RuntimeException("Can't read/write data");
        }
    }

    private int calculateAmount(List<String> data, String category) {
        int sum = 0;
        for (String value : data) {
            String[] values = value.split(",");
            if (values[INDEX_OF_CATEGORY].equals(category)) {
                sum += Integer.parseInt(values[INDEX_OF_VALUE]);
            }
        }
        return sum;
    }

    private String makeReport(String supply, String buy, List<String> data) {
        int sumOfSupply = calculateAmount(data, supply);
        int sumOfBuy = calculateAmount(data, buy);
        StringBuilder report = new StringBuilder(supply)
                .append(",")
                .append(sumOfSupply)
                .append(System.lineSeparator())
                .append(buy)
                .append(",")
                .append(sumOfBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(sumOfSupply - sumOfBuy);
        return report.toString();
    }
}
