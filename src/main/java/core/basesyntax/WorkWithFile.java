package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringFromFile = readData(fromFileName);
        String[] data = createReport(stringFromFile);
        writeReport(data, toFileName);
    }

    private List<String> readData(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private String[] createReport(List<String> dataList) {
        int supplyValue = 0;
        int buyValue = 0;
        int resultValue = 0;

        for (String string : dataList) {
            String[] split = string.split(",");
            if (split[OPERATION_INDEX].equals("supply")) {
                supplyValue = supplyValue + Integer.parseInt(split[VALUE_INDEX]);
            }
            if (split[OPERATION_INDEX].equals("buy")) {
                buyValue = buyValue + Integer.parseInt(split[VALUE_INDEX]);
            }
        }
        resultValue = supplyValue - buyValue;

        String[] report = {"supply,", "buy,", "result,"};
        StringBuilder strBuild = new StringBuilder();
        report[0] = strBuild.append(report[0]).append(supplyValue).toString();
        strBuild.setLength(0);
        report[1] = strBuild.append(report[1]).append(buyValue).toString();
        strBuild.setLength(0);
        report[2] = strBuild.append(report[2]).append(resultValue).toString();
        return report;
    }

    private void writeReport(String[] report, String toFileName) {
        File file = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();

        for (String reportLine : report) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(stringBuilder.append(reportLine)
                        .append(System.lineSeparator()).toString());
                stringBuilder.setLength(0);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file" + toFileName, e);
            }
        }
    }
}
