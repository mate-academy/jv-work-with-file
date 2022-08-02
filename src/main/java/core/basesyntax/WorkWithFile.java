package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final int AMMOUNT = 1;
    private static final String DELIMITER = ",";
    private static final int OPERATION_TYPE = 0;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        List<String> fileContent = read(fromFileName);
        String[] tempArray;
        for (int i = 0; i < fileContent.size(); i++) {
            tempArray = fileContent.get(i).split(DELIMITER);
            if (tempArray[OPERATION_TYPE].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(tempArray[AMMOUNT]);
            } else {
                totalBuy += Integer.parseInt(tempArray[AMMOUNT]);
            }
        }
        String report = createReport(totalSupply, totalBuy, totalSupply - totalBuy);
        writeStatistic(toFileName, report);
    }

    private List<String> read(String fileName) {
        File file = new File(fileName);
        try {
            List<String> list = Files.readAllLines(file.toPath());
            return list;
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
    }

    private void writeStatistic(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file " + toFileName + " ", e);
        }
    }

    private String createReport(int totalSupply, int totalBuy, int totalResult) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(SUPPLY).append(DELIMITER).append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(totalBuy).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(totalResult).toString();
    }
}
