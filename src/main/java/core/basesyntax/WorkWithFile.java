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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String fullLine = bufferedReader.readLine();
            while (fullLine != null) {
                stringBuilder.append(fullLine).append(System.lineSeparator());
                fullLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
        String[] devidedData = stringBuilder.toString().split("\n");
        for (int i = 0; i < devidedData.length; i++) {
            String [] elements = devidedData[i].trim().split(",");
            if (elements[0].equals("supply")) {
                supply += Integer.parseInt(elements[1]);
            } else if (elements[0].equals("buy")) {
                buy += Integer.parseInt(elements[1]);
            } else {
                throw new RuntimeException("Incorrect input data");
            }
        }
        int result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                        + "buy," + buy + System.lineSeparator()
                        + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
