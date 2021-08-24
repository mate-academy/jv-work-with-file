package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int NUMBERS_PART_OF_SPLITED_INFOLINE = 1;
    private StringBuilder shopReport = new StringBuilder("supply,");

    public void getStatistic(String fromFileName, String toFileName) {
        formReport(fromFileName);
        sendReport(toFileName);
    }

    public void formReport(String fromFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        File fromFile = new File(fromFileName);
        List<String> fromFileInfo;
        try {
            fromFileInfo = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("can`t read file", e);
        }
        for (int i = 0; i < fromFileInfo.size(); i++) {
            if (fromFileInfo.get(i).split(",")[0].contains("supply")) {
                supply = supply + Integer.parseInt(fromFileInfo.get(i).split(",")
                        [NUMBERS_PART_OF_SPLITED_INFOLINE]);
            } else if (fromFileInfo.get(i).split(",")[0].contains("buy")) {
                buy = buy + Integer.parseInt(fromFileInfo.get(i).split(",")
                        [NUMBERS_PART_OF_SPLITED_INFOLINE]);
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

    public void sendReport(String toFileName) {
        File toFile = new File(toFileName);
        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Io have been thrown", e);
        }
        try (BufferedWriter repotrWriter = new BufferedWriter(new FileWriter(toFile))) {
            repotrWriter.write(shopReport.toString());
        } catch (IOException e) {
            throw new RuntimeException("can`t write file", e);
        }
    }
}
