package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

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
        String[] report = {"supply,", "buy,", "result,"};
        int supplyValue = 0;
        int buyValue = 0;
        int resultValue = 0;

        for (String string : dataList) {
            String[] split = string.split(",");
            if (split[0].equals("supply")) {
                supplyValue = supplyValue + Integer.parseInt(split[1]);
            }
            if (split[0].equals("buy")) {
                buyValue = buyValue + Integer.parseInt(split[1]);
            }
        }
        resultValue = supplyValue - buyValue;
        report[0] = report[0] + supplyValue;
        report[1] = report[1] + buyValue;
        report[2] = report[2] + resultValue;
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
