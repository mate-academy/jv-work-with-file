package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final byte AMOUNT_INDEX = 1;
    private static final byte TYPE_OF_OPERATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName);
    }

    public void writeToFile(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        String data = createReport(fromFileName);
        try {
            file.createNewFile();
            Files.write(file.toPath(), data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }

    public String[][] readFromFile(String nameFile) {
        File file = new File(nameFile);
        List<String> lines;
        String[][] arrayData;
        try {
            lines = Files.readAllLines(file.toPath());
            arrayData = new String[lines.size()][2];
            lines = Files.readAllLines(file.toPath());
            for (int i = 0; i < lines.size(); i++) {
                arrayData[i] = lines.get(i).split(",");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return arrayData;
    }

    public String createReport(String nameFile) {
        String[][] data = readFromFile(nameFile);
        int buy = 0;
        int supply = 0;
        StringBuilder reportBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (data[i][TYPE_OF_OPERATION_INDEX].equals("supply")) {
                supply = supply + Integer.parseInt(data[i][AMOUNT_INDEX]);
            } else {
                buy = buy + Integer.parseInt(data[i][AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;
        reportBuilder.append("supply,")
                .append(supply).append(System.lineSeparator())
                .append("buy,").append(buy)
                .append(System.lineSeparator())
                .append("result,").append(result);
        return reportBuilder.toString();
    }
}
