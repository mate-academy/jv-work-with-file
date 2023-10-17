package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    static final String SEPARATOR = ",";
    static final int DESCRIPTION_OF_THE_LIST = 0;
    static final int VALUE_OF_THE_LIST = 1;
    static final String SUPPLY = "supply,";
    static final String BUY = "buy,";
    static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(makeReport(readFromFile(fromFileName)), toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        String content = null;
        try {
            content = Files.readString(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<String>(Arrays.asList(content.split("\n")));
    }

    private String makeReport(List<String> listFromFile) {
        int supply = 0;
        int buy = 0;
        for (String s : listFromFile) {
            String[] split = s.split(SEPARATOR);
            if (split[DESCRIPTION_OF_THE_LIST].startsWith("s")) {
                supply += Integer.parseInt(split[VALUE_OF_THE_LIST]);
            } else {
                buy += Integer.parseInt(split[VALUE_OF_THE_LIST]);
            }
        }
        return SUPPLY + supply + System.lineSeparator()
                + BUY + buy + System.lineSeparator()
                + RESULT + (supply - buy);
    }

    private void writeToFile(String dataToWrite, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file" + toFileName, e);
        }
    }
}
