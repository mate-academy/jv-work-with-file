package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int SECOND_ELEMENT = 1;
    private static final String TXT_SUPPLY = "supply";
    private static final String TXT_BUY = "buy";
    private static final String TXT_RESULT = "result";
    private static final String TXT_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            File firstFile = new File(fromFileName);
            File secondFile = new File(toFileName);

            int buy = 0;
            int supply = 0;

            List<String> stringList = Files.readAllLines(firstFile.toPath());
            for (String s : stringList) {
                if (s.contains(TXT_BUY)) {
                    String[] str = s.split(TXT_SEPARATOR);
                    buy += Integer.parseInt(str[SECOND_ELEMENT]);
                }
                if (s.contains(TXT_SUPPLY)) {
                    String[] str = s.split(TXT_SEPARATOR);
                    supply += Integer.parseInt(str[SECOND_ELEMENT]);
                }
            }

            int result = supply - buy;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(TXT_SUPPLY).append(TXT_SEPARATOR).append(supply).append(System.lineSeparator())
                    .append(TXT_BUY).append(TXT_SEPARATOR).append(buy).append(System.lineSeparator())
                    .append(TXT_RESULT).append(TXT_SEPARATOR).append(result).append(System.lineSeparator());

            secondFile.createNewFile();
            Files.write(secondFile.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
    }
}
