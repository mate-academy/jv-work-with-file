package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName).split(" ");
        String report = createReport(data);
        writeData(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder dataBuilder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            value = bufferedReader.readLine();
            while (value != null) {
                dataBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file:" + fromFileName, e);
        }
        return dataBuilder.toString();
    }

    private String createReport(String[] data) {
        int sumSupply = 0;
        int sumBuy = 0;
        for (String line : data) {
            String[] splitterLine = line.split(",");
            if (splitterLine[OPERATION_TYPE_INDEX].equals("supply")) {
                sumSupply += Integer.parseInt(splitterLine[AMOUNT_INDEX]);
            } else {
                sumBuy += Integer.parseInt(splitterLine[AMOUNT_INDEX]);
            }
        }
        int result = sumSupply - sumBuy;
        StringBuilder reportBuilder = new StringBuilder();
        return reportBuilder.append("supply,").append(sumSupply)
                .append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    public void writeData(String report, String toFileName) {
        File resultFile = new File(toFileName);
        try {
            Files.write(resultFile.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write file.", e);
        }
    }
}
