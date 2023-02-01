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
    private static int buy = 0;
    private static int supply = 0;
    private static int result;

    public static void getStatistic(String fromFileName, String toFileName) {
        generateReport(fromFileName, toFileName);
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

    private static void writeToFile(String toFileName) {
        File fileForWrite = new File(toFileName);
        try {
            fileForWrite.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file" + fileForWrite, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForWrite))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileForWrite, e);
        }

    }

    private static void generateReport(String fromFileName, String toFileName) {
        List<String> listWithInfoAboutProducts = readFile(fromFileName);
        for (String string : listWithInfoAboutProducts) {
            String[] arrayWithInfoAboutProduct = string.split(CSV_DIVIDER);
            if (arrayWithInfoAboutProduct[INDEX_OF_NAME_OF_PRODUCT].equals(BUY_ITEM)) {
                buy += Integer.parseInt(arrayWithInfoAboutProduct[INDEX_OF_COST_OF_PRODUCT]);
            } else {
                supply += Integer.parseInt(arrayWithInfoAboutProduct[INDEX_OF_COST_OF_PRODUCT]);
            }
        }
        result = supply - buy;
        writeToFile(toFileName);
        buy = 0;
        supply = 0;
    }
}
