package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_DATA_0 = 0;
    private static final int INDEX_DATA_1 = 1;
    private static final String firstWord = "supply";
    private static final String secondWord = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAll = 0;
        int buyAll = 0;
        int result;
        Path pathFromFile = Path.of(fromFileName);
        String[] valuesFromFile;
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file",e);
        }
        try {
            List<String> lines = Files.readAllLines(pathFromFile);
            valuesFromFile = lines.toArray(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file",e);
        }
        for (String valueFromFile : valuesFromFile) {
            String[] value = valueFromFile.split(",");
            if (value[INDEX_DATA_0].equals(firstWord)) {
                supplyAll += Integer.parseInt(value[INDEX_DATA_1]);
            }

            if (value[INDEX_DATA_0].equals(secondWord)) {
                buyAll += Integer.parseInt(value[INDEX_DATA_1]);
            }
        }

        StringBuilder builder = new StringBuilder();

        builder.append(firstWord).append(",")
                .append(supplyAll)
                .append(System.lineSeparator())
                .append(secondWord)
                .append(",")
                .append(buyAll)
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(supplyAll - buyAll);
        try {
            Files.write(file.toPath(), builder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        }
    }
}
