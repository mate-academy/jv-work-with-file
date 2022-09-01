package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder firstFileData = new StringBuilder();
        int supply = 0;
        int buy = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String value = br.readLine();
            while (value != null) {
                firstFileData.append(value).append("|");
                value = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        for (String str : firstFileData.toString().split("\\|")) {
            String[] a = str.split(",");
            if (a[0].equals("buy")) {
                buy += Integer.parseInt(a[1]);
            } else {
                supply += Integer.parseInt(a[1]);
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + supply);
            bw.newLine();
            bw.write("buy," + buy);
            bw.newLine();
            bw.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
