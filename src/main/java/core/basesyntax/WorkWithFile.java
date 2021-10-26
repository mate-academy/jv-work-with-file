package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private final int dataIndex = 0;
    private final int countIndex = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        writeToFile(toFileName,createReport(data));
    }

    private String[] readFromFile(String fileName) {
        File fromFile = new File(fileName);
        StringBuilder data = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String reader = bufferedReader.readLine();
            while (reader != null) {
                data.append(reader).append(" ");
                reader = bufferedReader.readLine();
            }
            return data.toString().split(" ");
        } catch (Exception e) {
            throw new RuntimeException("File read failed " + fromFile, e);
        }
    }

    private void writeToFile(String fileName, String data) {
        File toFile = new File(fileName);
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

    private String createReport(String[] data) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        StringBuilder newFileWrite = new StringBuilder();
        for (String filedata : data) {
            String[] sortingData = filedata.split(",");
            if (sortingData[dataIndex].equals("supply")) {
                supply += Integer.valueOf(sortingData[countIndex]);
            }
            if (sortingData[dataIndex].equals("buy")) {
                buy += Integer.valueOf(sortingData[countIndex]);
            }
        }
        result = supply - buy;
        newFileWrite.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,").append(result);
        return newFileWrite.toString();
    }
}
