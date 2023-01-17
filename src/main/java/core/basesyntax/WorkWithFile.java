package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        File file1 = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(file1.toPath());
        } catch (IOException e) {
            throw new RuntimeException("can't read file" + file1, e);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("can't create new file" + file, e);
        }
        int buy = 0;
        int supply = 0;
        int result;
        for (String string : strings) {
            String[] tmpArray = string.split(",");
            if (tmpArray[0].equals("buy")) {
                buy += Integer.parseInt(tmpArray[1]);
            } else {
                supply += Integer.parseInt(tmpArray[1]);
            }
        }
        result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("can't write file" + file, e);
        }
    }
}
