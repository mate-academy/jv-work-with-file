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
    private static final String BYE_WORD = "buy,";
    private static final String SUPPLY_WORD = "supply,";
    private static final String RESULT_WORD = "result,";

    public static void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        String report = generateReport(lines);
        writeToFile(report, toFileName);
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
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private static String generateReport(List<String> lines) {
        int buy = 0;
        int supply = 0;
        int result;
        for (String string : lines) {
            String[] arrayWithInfoAboutProduct = string.split(CSV_DIVIDER);
            if (arrayWithInfoAboutProduct[INDEX_OF_NAME_OF_PRODUCT].equals(BUY_ITEM)) {
                buy += Integer.parseInt(arrayWithInfoAboutProduct[INDEX_OF_COST_OF_PRODUCT]);
            } else {
                supply += Integer.parseInt(arrayWithInfoAboutProduct[INDEX_OF_COST_OF_PRODUCT]);
            }
        }
        result = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_WORD).append(supply).append(System.lineSeparator());
        report.append(BYE_WORD).append(buy).append(System.lineSeparator());
        report.append(RESULT_WORD).append(result);
        return report.toString();
    }
}
