package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_ACTION = 0;
    private static final int INDEX_AMOUNT = 1;
    private List<String> listFromFile;
    private int buy = 0;
    private int supply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        String[] str;

        try {
            listFromFile = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read fromFile", e);
        }
        for (String s : listFromFile) {
            str = s.split(",");
            if (str[INDEX_ACTION].equals("buy")) {
                buy += Integer.parseInt(str[INDEX_AMOUNT]);
            } else {
                supply += Integer.parseInt(str[INDEX_AMOUNT]);
            }
        }
        writeToFile(toFileName);
    }

    public void writeToFile(String toFileName) {
        StringBuilder result = new StringBuilder();
        File toFile = new File(toFileName);

        result.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,")
                .append(supply - buy);

        try {
            Files.write(toFile.toPath(), result.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
