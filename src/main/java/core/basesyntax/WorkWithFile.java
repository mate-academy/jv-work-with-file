package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder readTextBuilder = getTextFromFile(fromFileName);
        long supply = 0;
        long buy = 0;
        long result = 0;
        String[] optimizationText = String.valueOf(readTextBuilder).split(System.lineSeparator());
        for (String cloud : optimizationText) {
            String[] cutText = cloud.split(" ");
            if (cutText[0].equals("supply")) {
                supply = supply + Integer.parseInt(cutText[1]);
            } else if (cutText[0].equals("buy")) {
                buy = buy + Integer.parseInt(cutText[1]);
            }
        }
        readTextBuilder = new StringBuilder();
        if (supply > buy) {
            result = supply - buy;
        } else if (supply < buy) {
            result = buy - supply;
        }
        readTextBuilder
                .append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        String text = String.valueOf(readTextBuilder);
        writeToFileName(toFileName, text);
    }

    public StringBuilder getTextFromFile(String fromFileName) {
        StringBuilder readTextBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readText = bufferedReader.readLine();
            while (readText != null) {
                String newReadText = readText.replace(",", " ");
                readTextBuilder.append(newReadText).append(System.lineSeparator());
                readText = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("not is good read file", e);
        }
        return readTextBuilder;
    }

    public void writeToFileName(String toFileName, String textWriteFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(textWriteFile));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("not is good write to file", e);
        }
    }
}

