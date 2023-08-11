package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int NUMBER_ONE = 1;
    private String processingResult = "";
    private StringBuilder builderReadMethod;

    public void getStatistic(String fromFileName, String toFileName) {
        readWithFile(fromFileName);
        dataProcessing(builderReadMethod);
        writeReportToFile(processingResult, toFileName);
    }

    private StringBuilder readWithFile(String fileName) {
        builderReadMethod = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            try {
                String value = "";
                while (value != null) {
                    builderReadMethod.append(value).append(",");
                    value = bufferedReader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        return builderReadMethod;
    }

    private void dataProcessing(StringBuilder stringBuilder) {
        String[] dataArray = stringBuilder.toString().split(",");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals("supply")) {
                supply += Integer.parseInt(dataArray[i + NUMBER_ONE]);
            } else if (dataArray[i].equals("buy")) {
                buy += Integer.parseInt(dataArray[i + NUMBER_ONE]);
            }
        }
        StringBuilder builderProcessingMethod = new StringBuilder();
        int result = supply - buy;

        processingResult = builderProcessingMethod.append("supply," + supply)
                 .append(System.lineSeparator())
                .append("buy," + buy).append(System.lineSeparator())
                .append("result," + result).toString();
    }

    private void writeReportToFile(String processingResult, String toFile) {
        File file = new File(toFile);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.write(file.toPath(), processingResult.getBytes());

        } catch (IOException e) {
            throw new RuntimeException("don't write to file", e);
        }
    }
}

