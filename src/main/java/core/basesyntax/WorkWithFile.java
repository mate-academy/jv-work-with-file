package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io. FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OPERATION = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        String readFromFile = getTextFromFile(fromFileName);
        String dataRepot = createReport(readFromFile);
        writeToFile(toFileName, dataRepot);

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
        return readTextBuilder.toString();
    }

    public String createReport(String data) {
        long supply = 0;
        long buy = 0;
        long result = 0;

        String[] dataArray = data.split(System.lineSeparator());
        for (String cloud : dataArray) {
            String[] cutText = cloud.split(" ");
            if (cutText[INDEX_OPERATION].equals("supply")) {
                supply = supply + Integer.parseInt(cutText[AMOUNT_INDEX]);
            } else if (cutText[INDEX_OPERATION].equals("buy")) {
                buy = buy + Integer.parseInt(cutText[AMOUNT_INDEX]);
            }
        }
        StringBuilder readTextBuilder = new StringBuilder();
        if (supply > buy) {
            result = supply - buy;
        } else if (supply < buy) {
            result = buy - supply;
        }
        readTextBuilder
                .append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        return readTextBuilder.toString();
    }

    public void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(data));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("not is good write to file", e);
        }
    }
}

