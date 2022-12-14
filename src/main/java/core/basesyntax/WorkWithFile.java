package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String[] NAMES = {"supply", "buy", "result"};
    private static final int INDEX_NAME = 0;
    private static final int INDEX_AMOUNT_MONEY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File files = new File(toFileName);
        int[] results = new int[3];
        if (files.exists()) {
            files.delete();
        }
        readFile(fromFileName, results);
        results[2] = results[0] - results[1];
        createFile(files);
        writeFile(files, results);

    }

    private static void createFile(File files) {
        try {
            files.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a new file", e);
        }
    }

    private static void writeFile(File files, int[] results) {
        for (int i = 0; i < NAMES.length; i++) {
            try {
                Files.write(files.toPath(), (NAMES[i] + "," + results[i] + System.lineSeparator())
                        .getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            }
        }
    }

    private static void readFile(String fromFileName, int[] results) {
        try {
            String data = Files.readAllLines(Path.of(fromFileName)).toString();
            String newData = data.replaceAll("[\\[\\]]", "");
            String[] dataListUnsorted = newData.split(" ");
            for (String element : dataListUnsorted) {
                if (element.split(",")[INDEX_NAME].equals(NAMES[0])) {
                    results[0] += Integer.parseInt(element.split(",")[INDEX_AMOUNT_MONEY]);
                } else {
                    results[1] += Integer.parseInt(element.split(",")[INDEX_AMOUNT_MONEY]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file");
        }
    }
}
