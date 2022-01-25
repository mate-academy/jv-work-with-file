package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    private static final String COMMA_SEPARATOR = ",";
    private int supply = 0;
    private int buy = 0;
    private int result = 0;
    private StringBuilder stringBuilder = new StringBuilder();
    private String[] res;
    private StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName, createReport());
    }

    private void readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            res = strings.toArray(new String[strings.size()]);
            for (String a: res) {
                String[] temporary = a.split(",");
                if (temporary[0].equals("supply")) {
                    supply += Integer.valueOf(temporary[1]);;
                } else if (temporary[0].equals("buy")) {
                    buy += Integer.valueOf(temporary[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("cant read file", e);
        }
    }

    private String createReport() {
        result += supply - buy;
        builder.append("supply").append(COMMA_SEPARATOR).append(supply
        ).append(System.lineSeparator()).append("buy").append(COMMA_SEPARATOR
        ).append(buy).append(System.lineSeparator()
        ).append("result").append(COMMA_SEPARATOR
        ).append(result).append(System.lineSeparator());
        return builder.toString();
    }

    private void writeToFile(String fileName, String report) {
        File fileOne = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOne, true));) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file...", e);
        }
    }
}
