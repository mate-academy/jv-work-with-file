package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY = 0;
    private static final int SUPPLY_ELEMENT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        StringBuilder builder = new StringBuilder();
        String[] file = readFile(new File(fromFileName));
        for (String files : file) {
            String[] reportFile = files.split(",");
            if (reportFile[SUPPLY].equals("supply")) {
                supplyCount += Integer.parseInt(reportFile[SUPPLY_ELEMENT]);
            } else {
                buyCount += Integer.parseInt(reportFile[SUPPLY_ELEMENT]);
            }
        }
        builder.append("supply").append(",").append(supplyCount).append(System.lineSeparator())
                .append("buy").append(",").append(buyCount).append(System.lineSeparator())
                .append("result").append(",").append(supplyCount - buyCount);
        writeReportFile(new File(toFileName), builder.toString());
    }

    private String[] readFile(File file) {
        StringBuilder builder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((value = bufferedReader.readLine()) != null) {
                builder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + file, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private void writeReportFile(File file, String writeToFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
            bufferedWriter.write(writeToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + writeToFile, e);
        }
    }
}