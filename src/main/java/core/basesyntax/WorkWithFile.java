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

    private void appendStringBuilder(StringBuilder stringBuilder,
                                     String text, int number) {
        stringBuilder.append(text).append(number).append(System.lineSeparator());
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> stringList = null;
        try {
            stringList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return stringList;
    }

    private String calculateData(List<String> stringList) {
        int buy = 0;
        int supply = 0;
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
        appendStringBuilder(stringBuilder, TXT_SUPPLY + TXT_SEPARATOR, supply);
        appendStringBuilder(stringBuilder, TXT_BUY + TXT_SEPARATOR, buy);
        appendStringBuilder(stringBuilder, TXT_RESULT + TXT_SEPARATOR, result);
        return stringBuilder.toString();
    }

    private void writeToFileResult(String toFileName, String data) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
            Files.write(file.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFileResult(toFileName, calculateData(readFromFile(fromFileName)));
    }
}
