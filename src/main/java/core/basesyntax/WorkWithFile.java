package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY = 0;
    private static final int BUY = 1;
    private static final int RESULT = 2;
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, calculateData(readFile(fromFileName)));
    }

    private StringBuilder readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder fromFileNameReader = new StringBuilder();
            String readLine = reader.readLine();
            while (readLine != null) {
                String[] arrReadLine = readLine.split(DELIMITER);
                for (String wordFromLine : arrReadLine) {
                    fromFileNameReader.append(wordFromLine).append(" ");
                }
                readLine = reader.readLine();
            }
            return fromFileNameReader;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private String[] calculateData(StringBuilder fromFileNameReader) {
        String[] strFromFileName = fromFileNameReader.toString().split(" ");
        Integer[] values = new Integer[]{0, 0, 0};
        for (int i = 0; i < strFromFileName.length; i++) {
            if (strFromFileName[i].equals("supply")) {
                values[SUPPLY] += Integer.valueOf(strFromFileName[i + 1]);
            } else if (strFromFileName[i].equals("buy")) {
                values[BUY] += Integer.valueOf(strFromFileName[i + 1]);
            }
        }
        values[RESULT] = values[SUPPLY] - values[BUY];
        String[] generalInformation = new String[3];
        String[] titles = new String[]{"supply", "buy", "result"};
        for (int i = 0; i < generalInformation.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(titles[i]).append(",").append(values[i]);
            generalInformation[i] = stringBuilder.toString();
        }
        return generalInformation;
    }

    private void writeFile(String toFileName, String[] generalInformation) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String generalInfo : generalInformation) {
                bufferedWriter.write(generalInfo + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
