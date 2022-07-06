package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_AMOUNT_INDEX = 1;
    private static final int OPERATION_NAME_INDEX = 0;
    private StringBuilder shopReport = new StringBuilder("supply,");

    public void getStatistic(String fromFileName, String toFileName) {
        generateReport(fromFileName);
        writeReport(toFileName);
    }

    public List<String> readInfo(String fromFileName) {
        File fromFile = new File(fromFileName);
        List<String> fromFileInfo;
        try {
            fromFileInfo = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("can`t read file", e);
        }
        return fromFileInfo;
    }

    public void generateReport(String fromFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (int i = 0; i < readInfo(fromFileName).size(); i++) {
            if (readInfo(fromFileName).get(i).split(",")[OPERATION_NAME_INDEX].contains("supply")) {
                supply = supply + Integer.parseInt(readInfo(fromFileName).get(i).split(",")
                        [OPERATION_AMOUNT_INDEX]);
            } else if (readInfo(fromFileName).get(i).split(",")
                    [OPERATION_NAME_INDEX].contains("buy")) {
                buy = buy + Integer.parseInt(readInfo(fromFileName).get(i).split(",")
                        [OPERATION_AMOUNT_INDEX]);
            }
        }
        result = supply - buy;
        shopReport.append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result)
                .append(System.lineSeparator());
    }

    public void writeReport(String toFileName) {
        File toFile = new File(toFileName);
        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }
        try (BufferedWriter repotrWriter = new BufferedWriter(new FileWriter(toFile))) {
            repotrWriter.write(shopReport.toString());
        } catch (IOException e) {
            throw new RuntimeException("can`t write file", e);
        }
    }
}
