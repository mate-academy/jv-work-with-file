package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        String[] data = fileReader(fromFile);
        createNewFile(toFile,writeData(data));
    }

    private String[] fileReader(File fromFile) {
        String readFromFile = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String reader = bufferedReader.readLine();
            while (reader != null) {
                readFromFile = readFromFile + reader + " ";
                reader = bufferedReader.readLine();
            }
            return readFromFile.split(" ");
        } catch (Exception e) {
            throw new RuntimeException("File read failed " + fromFile, e);
        }
    }

    private void createNewFile(File toFile, String data) {
        try {
            toFile.createNewFile();
        } catch (Exception e) {
            throw new RuntimeException("Create failed", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(data);
        } catch (Exception e) {
            throw new RuntimeException("File write failed " + toFile, e);
        }
    }

    private String writeData(String[] fileData) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        StringBuilder newFileWrite = new StringBuilder();
        for (String data : fileData) {
            String[] sortingData = data.split(",");
            if (sortingData[0].equals("supply")) {
                supply += Integer.valueOf(sortingData[1]);
            }
            if (sortingData[0].equals("buy")) {
                buy += Integer.valueOf(sortingData[1]);
            }
        }
        result = supply - buy;
        newFileWrite.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,").append(result);
        return newFileWrite.toString();
    }
}
