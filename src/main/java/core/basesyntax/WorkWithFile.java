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

    public static void getStatistic(String fromFileName, String toFileName) {
        File fileForWrite = new File(toFileName);
        File fileForRead = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(fileForRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileForRead, e);
        }
        try {
            fileForWrite.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file" + fileForWrite, e);
        }
        int buy = 0;
        int supply = 0;
        int result;
        for (String string : strings) {
            String[] arrayWithInfoAboutProduct = string.split(",");
            if (arrayWithInfoAboutProduct[INDEX_OF_NAME_OF_PRODUCT].equals("buy")) {
                buy += Integer.parseInt(arrayWithInfoAboutProduct[INDEX_OF_COST_OF_PRODUCT]);
            } else {
                supply += Integer.parseInt(arrayWithInfoAboutProduct[INDEX_OF_COST_OF_PRODUCT]);
            }
        }
        result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForWrite))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileForWrite, e);
        }
    }
}
