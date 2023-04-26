package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataToRead = getDataFromFile(fromFileName);
        String dataToWrite = dataProcessing(dataToRead);
        writeDataToFile(toFileName, dataToWrite);
    }

    private String[] getDataFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(",");
    }

    private String dataProcessing(String[] data) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < data.length; i += 2) {
            if (data[i].equals("supply")) {
                supply += Integer.parseInt(data[i + 1]);
            } else {
                buy += Integer.parseInt(data[i + 1]);
            }
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeDataToFile(String fileName, String data) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
