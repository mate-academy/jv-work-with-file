package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int OPERATION = 0;
    private static final int VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writetoFile(report, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            File file = new File(fromFileName);
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private String createReport(List<String> readFromFile) {
        StringBuilder builder = new StringBuilder();
        int sumBuy = 0;
        int sumSupply = 0;
        for (String string : readFromFile) {
            String[] values = string.split(",");
            if (values[OPERATION].equals(BUY)) {
                sumBuy = sumBuy + Integer.parseInt(values[VALUE]);
            } else {
                sumSupply = sumSupply + Integer.parseInt(values[VALUE]);
            }
        }
        int sumResult = sumSupply - sumBuy;
        return builder.append(SUPPLY)
                .append(",")
                .append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY).append(",")
                .append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(",")
                .append(sumResult).toString();
    }

    private void writetoFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
