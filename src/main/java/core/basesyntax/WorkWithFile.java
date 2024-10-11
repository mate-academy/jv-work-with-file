package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

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
        Stream<String> lines = null;
        try {
            lines = Files.lines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return lines.toList();
    }

    private int addElementIfContains(String line, String isContained,
                                      String[] fromArray, int element) {
        if (line.contains(isContained)) {
            int value = Integer.parseInt(fromArray[element]);
            return value;
        }
        return 0;
    }

    private String calculateData(List<String> stringList) {
        int buy = 0;
        int supply = 0;
        for (String s : stringList) {
            buy += addElementIfContains(s, TXT_BUY,
                    s.split(TXT_SEPARATOR), SECOND_ELEMENT);
            supply += addElementIfContains(s, TXT_SUPPLY,
                    s.split(TXT_SEPARATOR), SECOND_ELEMENT);
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
            Files.write(file.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFileResult(toFileName, calculateData(readFromFile(fromFileName)));
    }
}
