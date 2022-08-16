package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
        String dataFromFile = stringBuilder.toString();
        String[] arrayData = dataFromFile.split(",");
        for (int i = 0; (i) < (arrayData.length - 1); i++) {
            if (arrayData[i].equals("supply")) {
                supply += Integer.parseInt(arrayData[i + 1]);
            } else if (arrayData[i].equals("buy")) {
                buy += Integer.parseInt(arrayData[i + 1]);
            }
        }
        int result = supply - buy;
        String report = "" + "supply," + supply
                + System.lineSeparator() + "buy,"
                + buy + System.lineSeparator()
                + "result," + result;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
    }
}
