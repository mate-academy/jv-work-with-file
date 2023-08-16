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

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int supplyResult = 0;
        int buyResult = 0;
        int result = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                if (value.contains(SUPPLY)) {
                    supplyResult += Integer.parseInt(value.substring(value.indexOf(',') + 1));
                }
                if (value.contains(BUY)) {
                    buyResult += Integer.parseInt(value.substring(value.indexOf(',') + 1));
                }
                result = supplyResult - buyResult;
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(supplyResult).append(System.lineSeparator())
                .append(BUY).append(",").append(buyResult).append(System.lineSeparator())
                .append(RESULT).append(",").append(result);
        String output = builder.toString();

        File file2 = new File(toFileName);
        try {
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }

        try {
            Files.write(file2.toPath(), output.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into the file", e);
        }
    }
}
