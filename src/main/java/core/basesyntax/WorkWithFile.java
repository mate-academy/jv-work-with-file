package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SPECIFIC_SYMBOL = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] infoFromFile = readFile(fromFileName).split("\\W+");
        int supplyCount = 0;
        int buyCount = 0;
        for (int i = 0; i < infoFromFile.length - 1; i++) {
            if (infoFromFile[i].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(infoFromFile[i + 1]);
            } else if (infoFromFile[i].equals(BUY)) {
                buyCount += Integer.parseInt(infoFromFile[i + 1]);
            }
        }
        int resultCount = supplyCount - buyCount;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SUPPLY).append(SPECIFIC_SYMBOL).append(supplyCount)
                .append(System.lineSeparator())
                .append(BUY).append(SPECIFIC_SYMBOL).append(buyCount)
                .append(System.lineSeparator())
                .append(RESULT).append(SPECIFIC_SYMBOL).append(resultCount)
                .append(System.lineSeparator());

        writeFile(toFileName, stringBuilder.toString());
    }

    public String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }

        return stringBuilder.toString();
    }

    public void writeFile(String toFileName, String dataAfterWorkingDay) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }
        try {
            Files.write(file.toPath(), dataAfterWorkingDay.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write a data in file", e);
        }
    }
}
