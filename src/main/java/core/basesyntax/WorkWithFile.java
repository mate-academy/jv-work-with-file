package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, readFileAndCalculate(fromFileName));

    }

    private String[] readFileAndCalculate(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            StringBuilder fromFileNameReader = new StringBuilder();
            int value = reader.read();
            while (value != -1) {
                if ((char) value == ',' || value == 10) {
                    fromFileNameReader.append(" ");
                } else if (value == 13) {
                    fromFileNameReader.append("");
                } else {
                    fromFileNameReader.append((char) value);
                }
                value = reader.read();
            }
            String[] strFromFileName = fromFileNameReader.toString().split(" ");
            Integer[] values = new Integer[]{0, 0, 0};
            for (int i = 0; i < strFromFileName.length; i++) {
                if (strFromFileName[i].equals("supply")) {
                    values[0] += Integer.valueOf(strFromFileName[i + 1]);
                } else if (strFromFileName[i].equals("buy")) {
                    values[1] += Integer.valueOf(strFromFileName[i + 1]);
                }
            }
            values[2] = values[0] - values[1];
            String[] generalInformation = new String[]{"", "", ""};
            String[] titles = new String[]{"supply", "buy", "result"};
            for (int i = 0; i < generalInformation.length; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(titles[i]).append(",").append(values[i]);
                generalInformation[i] = stringBuilder.toString();
            }
            return generalInformation;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
    }

    private void writeFile(String toFileName, String[]generalInformation) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String generalInfo : generalInformation) {
                bufferedWriter.write(generalInfo + System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file");
        }
    }
}
