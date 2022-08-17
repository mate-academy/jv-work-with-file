package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int ELEMENT_PREVIOUSLY_COMMA = 0;
    private static final int ELEMENT_AFTER_COMMA = 1;
    private static final String REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(writeStatistic(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String [] getReadingFiles(String fromFileName) {
        File file = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] readingData = new String[strings.size()];
        for (int i = 0; i < readingData.length; i++) {
            readingData[i] = strings.get(i);
        }
        return readingData;
    }

    private int getSupplySum(String fromFileName) {
        String[] fullData = getReadingFiles(fromFileName);
        int supplySum = 0;
        for (String fullDatum : fullData) {
            String[] split = fullDatum.split(REGEX);
            if (split[ELEMENT_PREVIOUSLY_COMMA].equals("supply")) {
                supplySum += Integer.parseInt(split[ELEMENT_AFTER_COMMA]);
            }
        }
        return supplySum;
    }

    private int getBuySum(String fromFileName) {
        String[] fullData = getReadingFiles(fromFileName);
        int buySum = 0;
        for (String fullDatum : fullData) {
            String[] split = fullDatum.split(REGEX);
            if (split[ELEMENT_PREVIOUSLY_COMMA].equals("buy")) {
                buySum += Integer.parseInt(split[ELEMENT_AFTER_COMMA]);
            }
        }
        return buySum;
    }

    private String writeStatistic(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(getSupplySum(fromFileName)).append(System.lineSeparator())
                .append("buy," + getBuySum(fromFileName)).append(System.lineSeparator())
                .append("result," + (getSupplySum(fromFileName) - getBuySum(fromFileName)));
        return builder.toString();
    }
}
