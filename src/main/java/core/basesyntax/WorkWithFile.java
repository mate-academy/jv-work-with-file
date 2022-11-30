package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> listFromFile;
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder result = new StringBuilder();
        String[] str;
        int buy = 0;
        int supply = 0;

        try {
            listFromFile = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read fromFile", e);
        }
        for (String s : listFromFile) {
            str = s.split(",");
            if (str[0].equals("buy")) {
                buy += Integer.parseInt(str[1]);
            } else {
                supply += Integer.parseInt(str[1]);
            }
        }
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
