package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] valuesFromFile = readFromFile(fromFileName);
        writeInFile(toFileName, valuesFromFile[0], valuesFromFile[1]);
    }

    private int[] readFromFile(String fromFileName) {
        int[] readValuesFromFile = new int[2];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            int supply = 0;
            int buy = 0;
            while (value != null) {
                String[] strings = value.split(",");
                switch (strings[0]) {
                    case "supply":
                        supply = supply + Integer.parseInt(strings[1]);
                        break;
                    default :
                        buy = buy + Integer.parseInt(strings[1]);
                        break;
                }
                stringBuilder.append(value);
                value = bufferedReader.readLine();
            }
            readValuesFromFile[0] = supply;
            readValuesFromFile[1] = buy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName,e);
        }
        return readValuesFromFile;
    }

    private void writeInFile(String toFileName, int supply, int buy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int result = supply - buy;
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file " + toFileName,e);
        }
    }
}
