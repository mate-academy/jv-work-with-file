package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = processingData(getFileContent(fromFileName));
        writeFile(toFileName, report);
    }

    private List<String> getFileContent(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private String processingData(List<String> strings) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] parseString;
        for (String string : strings) {
            parseString = string.split(",");
            if (parseString[0].equals("supply")) {
                supply += + Integer.parseInt(parseString[1]);
            } else {
                buy += + Integer.parseInt(parseString[1]);
            }
        }
        result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply)
                .append(System.lineSeparator())
                .append("buy,").append(buy)
                .append(System.lineSeparator())
                .append("result,").append(result);
        return builder.toString();
    }

    private void writeFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file " + toFileName, e);
        }
        try {
            Files.write(file.toPath(), report.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
