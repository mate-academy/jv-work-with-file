package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io. FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFileName(toFileName, optimizationTextCsv(getTextFromFile(fromFileName)));
    }

    public String getTextFromFile(String fromFileName) {
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
        return String.valueOf(readTextBuilder);
    }

    public String optimizationTextCsv(String getTextFromFile) {
        StringBuilder readTextBuilder = new StringBuilder(getTextFromFile);
        long supply = 0;
        long buy = 0;
        long result = 0;

        String[] optimizationText = String.valueOf(readTextBuilder).split(System.lineSeparator());
        for (String cloud : optimizationText) {
            final int CutIndexText = 0;
            final int CutIndexNumber = 1;
            String[] cutText = cloud.split(" ");
            if (cutText[CutIndexText].equals("supply")) {
                supply = supply + Integer.parseInt(cutText[CutIndexNumber]);
            } else if (cutText[CutIndexText].equals("buy")) {
                buy = buy + Integer.parseInt(cutText[CutIndexNumber]);
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
        return String.valueOf(readTextBuilder);
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

