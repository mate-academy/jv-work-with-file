package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int INDEX_NAME = 0;
    private static final int INDEX_PRICE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        String[] array = getStringFile(inputFile).split("\r\n");
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        writeToFile(getCountResult(array), outputFile);
    }

    private String getStringFile(File file) {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(Files.readString(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + e);
        }
        return builder.toString();
    }

    private void writeToFile(String[] data, File fileName) {
        for (String str : data) {
            try {
                Files.write(fileName.toPath(),
                        (str + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

    private String[] getCountResult(String[] array) {
        String[] result = new String[3];
        int sumSupply = 0;
        int sumBuy = 0;
        for (String s : array) {
            String[] data = s.split(",");
            if (data[INDEX_NAME].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(data[INDEX_PRICE]);
            }
            if (data[INDEX_NAME].equals(BUY)) {
                sumBuy += Integer.parseInt(data[INDEX_PRICE]);
            }
        }
        result[0] = "supply," + sumSupply;
        result[1] = "buy," + sumBuy;
        result[2] = "result," + (sumSupply - sumBuy);
        return result;
    }

}
