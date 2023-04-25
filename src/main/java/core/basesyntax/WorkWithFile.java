package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] infoFromFileName = getInfoFromFileName(fromFileName);
        String reportSupplyAndBuy = getInfoSupplyAndBuy(infoFromFileName);
        writeToFile(toFileName, reportSupplyAndBuy);
    }

    public String[] getInfoFromFileName(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file - " + fromFileName, e);
        }
        String sentence = stringBuilder.toString();
        return sentence.split(System.lineSeparator());
    }

    public String getInfoSupplyAndBuy(String[] split) {
        int index;
        int sumSupply = 0;
        int sumBuy = 0;
        StringBuilder builder = new StringBuilder();
        for (String word : split) {
            index = word.indexOf(COMMA);
            if (word.substring(0, index).equals(SUPPLY)) {
                sumSupply += Integer.parseInt(word.substring(index + 1));
            } else {
                sumBuy += Integer.parseInt(word.substring(index + 1));
            }
        }
        builder.append(SUPPLY).append(COMMA).append(sumSupply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(sumBuy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(sumSupply - sumBuy);
        return builder.toString();
    }

    public void writeToFile(String toFileName, String recordingInfo) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(recordingInfo);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file - " + toFileName, e);
        }
    }
}
