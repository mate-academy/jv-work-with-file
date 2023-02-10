package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_OF_NAME_OF_PRODUCT = 0;
    private static final int INDEX_OF_COST_OF_PRODUCT = 1;
    private static final String CSV_DIVIDER = ",";
    private static final String BUY_ITEM = "buy";

    public static void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName);
    }

    private static List<String> readFile(String fromFileName) {
        File fileForRead = new File(fromFileName);
        List<String> listWithInfoAboutProducts;
        try {
            listWithInfoAboutProducts = Files.readAllLines(fileForRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileForRead, e);
        }
        return listWithInfoAboutProducts;
    }

    private static void writeToFile(String report, String toFileName) {
        String string = generateReport(report);
        generateReport(report);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private static String generateReport(String report) {
        int buy = 0;
        int supply = 0;
        int result;
        List<String> listWithInfoAboutProducts = readFile(report);
        for (String string : listWithInfoAboutProducts) {
            String[] arrayWithInfoAboutProduct = string.split(CSV_DIVIDER);
            if (arrayWithInfoAboutProduct[INDEX_OF_NAME_OF_PRODUCT].equals(BUY_ITEM)) {
                buy += Integer.parseInt(arrayWithInfoAboutProduct[INDEX_OF_COST_OF_PRODUCT]);
            } else {
                supply += Integer.parseInt(arrayWithInfoAboutProduct[INDEX_OF_COST_OF_PRODUCT]);
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buy).append(System.lineSeparator());
        stringBuilder.append("result,").append(result);
        return stringBuilder.toString();
    }
}
