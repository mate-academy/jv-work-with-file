package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create new file ", e);
        }
        String[] result = countResult(readFromFile(fromFileName));
        for (int i = 0;i < result.length;i++) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(result[i]);
                if (i < result.length - 1) {
                    bufferedWriter.write(System.lineSeparator());
                }
            } catch (IOException e) {
                throw new RuntimeException("Can`t write to file ", e);
            }
        }
    }

    public String[] readFromFile(String fileName) {
        File file = new File(fileName);
        List<String> arrayList = new ArrayList<>();
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            for (int i = 0; i < strings.size(); i++) {
                arrayList.add(strings.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file ", e);
        }
        return arrayList.toArray(String[]::new);
    }

    private String[] countResult(String[] array) {
        int buy = 0;
        int result = 0;
        int supply = 0;
        String[] finalArray = new String[3];
        for (int i = 0;i < array.length;i++) {
            String[] splitPieces = array[i].split(",");
            for (int j = 0;j < splitPieces.length;j++) {
                if (splitPieces[j].equals("buy")) {
                    buy += Integer.valueOf(splitPieces[1]);
                }
                if (splitPieces[j].equals("supply")) {
                    supply += Integer.valueOf(splitPieces[1]);
                }
            }
        }
        result = supply - buy;
        finalArray[0] = "supply" + "," + supply;
        finalArray[1] = "buy" + "," + buy;
        finalArray[2] = "result" + "," + result;
        return finalArray;
    }
}
