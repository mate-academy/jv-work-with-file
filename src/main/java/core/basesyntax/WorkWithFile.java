package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(calculateStatistic(readFromFile(fromFileName)));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + toFileName + "!", e);
        }
    }

    public String[] readFromFile(String fromFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String row = reader.readLine();
            while (row != null) {
                stringBuilder.append(row).append(";");
                row = reader.readLine();
            }
            return stringBuilder.toString().split(";");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file " + fromFileName + "!", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName + "!", e);
        }
    }

    public String calculateStatistic(String[] operations) {
        int supply = 0;
        int buy = 0;
        for (String operation : operations) {
            if (operation.substring(0, operation.indexOf(",")).equals("supply")) {
                supply += Integer.parseInt(operation.substring(7));
            } else {
                buy += Integer.parseInt(operation.substring(4));
            }
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }
}
