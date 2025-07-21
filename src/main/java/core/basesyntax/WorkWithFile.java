package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {

    private static final String APPLE_RESULT_FILE = "appleResult.csv";

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", APPLE_RESULT_FILE);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operations = new HashMap<>();
        BufferedReader bufferedReader = null;
        try {
            File inputFile = new File(fromFileName);
            bufferedReader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open the file", e);
        }
        try {
            String tempLine = bufferedReader.readLine();
            while (tempLine != null) {
                String[] data = new String[2];
                data = tempLine.split(",");
                Integer num = Integer.parseInt(data[1]);

                operations.compute(data[0], (key, val) -> val == null ? num : val + num);

                tempLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        File toFile = new File(toFileName);
        try {
            if (toFile.exists()) {
                toFile.delete();
            }
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create to file", e);
        }

        String supply = "supply," + operations.get("supply") + "\n";
        String buy = "buy," + operations.get("buy") + "\n";
        String result = "result," + (operations.get("supply") - operations.get("buy"));

        try {
            Files.write(toFile.toPath(), supply.getBytes(), StandardOpenOption.APPEND);
            Files.write(toFile.toPath(), buy.getBytes(), StandardOpenOption.APPEND);
            Files.write(toFile.toPath(), result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }

    }

}
