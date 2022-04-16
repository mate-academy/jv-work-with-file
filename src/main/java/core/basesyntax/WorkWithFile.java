package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
    }

    public String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        String string;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))){
            string = reader.readLine();
            while (string!= null) {
                builder.append(string).append(System.lineSeparator());
                string = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return builder.toString();
    }

    public String[][] separateData(String stringFromFile) {
        String[] splitTable = stringFromFile.split(System.lineSeparator());
        String[][] splitLine = new String[splitTable.length][2];
        for (int i = 0; i < splitLine.length; i++) {
            splitLine[i] = splitTable[i].split("\\W+");
        }
        return splitLine;
    }

    public int[] calculateData(String[][] separatedData) {
        int supply = 0;
        int buy = 0;
        int result;
        for (String[] separatedDatum : separatedData) {
            if (separatedDatum[0].equals("supply")) {
                supply += Integer.parseInt(separatedDatum[1]);
            } else if (separatedDatum[0].equals("buy")) {
                buy += Integer.parseInt(separatedDatum[1]);
            }
        }
        result = supply - buy;
        return new int[] {supply, buy, result};
    }
}
