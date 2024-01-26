package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public static final String SUPPLY_STR = "supply";
    public static final String BUY_STR = "buy";
    public static final String RESULT_STR = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] input = readStringsFromFile(fromFileName);

        int supplyAmount = 0;
        int buyAmount = 0;
        for (String inputEntry : input) {
            String[] inputEntrySplited = inputEntry.split(",");
            if (inputEntrySplited[0].equals("supply")) {
                supplyAmount += Integer.valueOf(inputEntrySplited[1]);
            } else {
                buyAmount += Integer.valueOf(inputEntrySplited[1]);
            }
        }

        File toFile = createFile(toFileName);
        writeToFile(toFile, createAReport(supplyAmount, buyAmount).getBytes());
    }

    private String[] readStringsFromFile(String filePath) {
        try {
            return Files.readAllLines(new File(filePath).toPath())
                    .toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private File createFile(String filePath) {
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        return file;
    }

    private void writeToFile(File file, byte[] data) {
        try {
            Files.write(file.toPath(), data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String createAReport(int supplyAmount, int buyAmount) {
        StringBuilder sb = new StringBuilder();
        return sb.append(SUPPLY_STR).append(",").append(supplyAmount)
                .append(System.lineSeparator()).append(BUY_STR).append(",").append(buyAmount)
                .append(System.lineSeparator()).append(RESULT_STR).append(",")
                .append(supplyAmount - buyAmount).toString();
    }
}
