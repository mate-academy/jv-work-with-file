package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String stringList = getCalculatedResult(getListFromFile(new File(fromFileName)));
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(stringList);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Exception 2", e);
        }
    }

    public static ArrayList<String[]> getListFromFile(File file) {
        String line;
        ArrayList<String[]> lineList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                lineList.add(line.split(","));
            }
            return lineList;
        } catch (IOException e) {
            throw new RuntimeException("Exception 1", e);
        }
    }

    public static String getCalculatedResult(ArrayList<String[]> arrayList) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] list: arrayList) {
            if (list[0].equals("supply")) {
                supply += Integer.parseInt(list[1].trim());
            }
            if (list[0].equals("buy")) {
                buy += Integer.parseInt(list[1].trim());
            }
        }
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return stringBuilder.toString();
    }
}
