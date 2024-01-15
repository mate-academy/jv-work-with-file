package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        if (file.exists()) {
            file.delete();
        }
        final String[] arrayToFile = calculatingArrayToFile(fromFileName);
        writeToFile(arrayToFile, toFileName);
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
        try {
            List<String> list = Files.readAllLines(file.toPath());
            String[] result = new String[list.size()];
            for (int i = 0; i < result.length; i++) {
                result [i] = list.get(i);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("can't read from the file", e);
        }
    }

    private void writeToFile(String[] fromArray, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String array : fromArray) {
                bufferedWriter.write(array + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("can't write to the file", e);
        }
    }

    private String[] calculatingArrayToFile(String fromFileName) {
        final String supply = "supply,";
        final String buy = "buy,";
        final String result = "result,";
        String[] arrayFromFile = readFromFile(fromFileName);
        String[] arrayToFile = {supply, buy, result};
        int tempForSupply = 0;
        int tempForBuy = 0;

        for (int i = 0; i < arrayFromFile.length; i++) {
            if (arrayFromFile[i].substring(0, arrayFromFile[i].indexOf(",")).equals("supply")) {
                tempForSupply += Integer.parseInt(arrayFromFile[i]
                        .substring(arrayFromFile[i].indexOf(",") + 1, arrayFromFile[i].length()));
            } else {
                tempForBuy += Integer.parseInt(arrayFromFile[i]
                        .substring(arrayFromFile[i].indexOf(",") + 1, arrayFromFile[i].length()));
            }
        }
        arrayToFile[0] += String.valueOf(tempForSupply);
        arrayToFile[1] += String.valueOf(tempForBuy);
        arrayToFile[2] += String.valueOf(tempForSupply - tempForBuy);

        return arrayToFile;
    }
}
