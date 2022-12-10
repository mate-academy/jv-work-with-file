
package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final String SUPPLY_PATTERN = "supply";
    private static final String BUY_PATTERN = "buy";
    private static final String SPLIT_PATTERN = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        writeFile(toFileName, createReport(dataFromFile));
    }

    public String[] readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        String[] context = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            context = stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("File can't read", e);
        }
        return context;
    }

    private void writeFile(String nameFile, String result) {
        File file = new File(nameFile);
        try {
            file.createNewFile();
            Files.write(file.toPath(), result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("File can't creating", e);
        }
    }

    private String createReport(String[] dataFromFile) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataFromFile.length; i++) {
            String[] splitData = dataFromFile[i].split(SPLIT_PATTERN);
            if (splitData[FIRST_INDEX].equals(SUPPLY_PATTERN)) {
                supply += Integer.parseInt(splitData[SECOND_INDEX]);
            }
            if (splitData[FIRST_INDEX].equals(BUY_PATTERN)) {
                buy += Integer.parseInt(splitData[SECOND_INDEX]);
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }
}
