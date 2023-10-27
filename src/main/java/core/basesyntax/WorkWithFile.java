package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private String[] readFromFile(String fileName) {
             File file = new File(fileName);
            try {
                List<String> stringList = Files.readAllLines(file.toPath());
                String[] inputFileToString = stringList.toString().split("\\W+");
                return inputFileToString;
            } catch (IOException e) {
                throw new RuntimeException("Can`t read file " + fileName, e);
            }
    }
    private String createReport(String[] strings) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (int i = 1; i < strings.length; i++) {
            if (strings[i].equals("supply")) {
                supply += Integer.parseInt(strings[i + 1]);
            } else if (strings[i].equals("buy")) {
                buy += Integer.parseInt(strings[i + 1]);
            }
            result = supply - buy;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,").append(result);
        return builder.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fromFile = readFromFile(fromFileName);
        String toFile = createReport(fromFile);
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), toFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }
}
