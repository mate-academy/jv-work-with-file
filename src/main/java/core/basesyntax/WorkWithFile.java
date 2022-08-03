package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String REGULAR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataToFile = createReport(readFromFile(fromFileName));
        writeToFile(toFileName, dataToFile);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            for (String string: strings) {
                stringBuilder.append(string).append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String dataToFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(dataToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }

    private String createReport(String content) {
        String[] dataFromFile = content.split(System.lineSeparator());
        StringBuilder stringBuilder = new StringBuilder();
        int supplyCount = 0;
        int buyCount = 0;
        for (String data : dataFromFile) {
            String [] eachData = data.split(REGULAR);
            if (eachData[0].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(eachData[1]);
            }
            if (eachData[0].equals(BUY)) {
                buyCount += Integer.parseInt(eachData[1]);
            }
        }
        int result = supplyCount - buyCount;
        return stringBuilder.append(SUPPLY).append(REGULAR).append(supplyCount)
                .append(System.lineSeparator())
                .append(BUY).append(REGULAR).append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(REGULAR).append(result).toString();
    }
}

