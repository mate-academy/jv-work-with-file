package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder dataFromFile = readWithFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private StringBuilder readWithFile(String fileName) {
        StringBuilder builderReadMethod = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String value = "";
            while (value != null) {
                builderReadMethod.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        return builderReadMethod;
    }

    private String createReport(StringBuilder stringBuilder) {
        String[] dataArray = stringBuilder.toString().split(",");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals("supply")) {
                supply += Integer.parseInt(dataArray[i + 1]);
            } else if (dataArray[i].equals("buy")) {
                buy += Integer.parseInt(dataArray[i + 1]);
            }
        }
        StringBuilder builderProcessingMethod = new StringBuilder();
        int result = supply - buy;
        String report = "";
        return report = builderProcessingMethod.append("supply," + supply)
                .append(System.lineSeparator())
                .append("buy," + buy).append(System.lineSeparator())
                .append("result," + result).toString();
    }

    private void writeReportToFile(String processingResult, String toFile) {
        File file = new File(toFile);
        try {
            file.createNewFile();
            Files.write(file.toPath(), processingResult.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("don't write to file", e);
        }
    }
}

