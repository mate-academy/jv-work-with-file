package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DIFFERENCE = "result";
    private static final int RESULT_ARRAY_SIZE = 3;
    private static final int INDEX_SUPPLY = 0;
    private static final int INDEX_BUY = 1;
    private static final int INDEX_RESULT = 2;
    private static final int INDEX_NAMES = 0;
    private static final int INDEX_PRICE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        String[] resultData = getCountResult(getStringFile(inputFile));
        writeToFile(resultData, outputFile);
    }

    private List<String> getStringFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + file + e);
        }
    }

    private void writeToFile(String[] data, File fileName) {
        try {
            Files.writeString(
                    fileName.toPath(),
                    String.join(System.lineSeparator(), data)
            );
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + fileName + e);
        }
    }

    private String[] getCountResult(List<String> array) {
        String[] result = new String[RESULT_ARRAY_SIZE];
        int sumSupply = 0;
        int sumBuy = 0;
        for (String s : array) {
            String[] data = s.split(",");
            if (data[INDEX_NAMES].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(data[INDEX_PRICE]);
            }
            if (data[INDEX_NAMES].equals(BUY)) {
                sumBuy += Integer.parseInt(data[INDEX_PRICE]);
            }
        }
        result[INDEX_SUPPLY] = SUPPLY + "," + sumSupply;
        result[INDEX_BUY] = BUY + "," + sumBuy;
        result[INDEX_RESULT] = DIFFERENCE + "," + (sumSupply - sumBuy);
        return result;
    }
}
