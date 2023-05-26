package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_DATA = 1;
    private static final String LIMIT_FIELD = ",";
    private String reportText;

    public void getStatistic(String fromFileName, String toFileName) {
        String nameSupply = "supply";
        String nameBuy = "buy";
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while ((value != -1)) {
                stringBuilder.append((char)value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file " + toFileName,e);
        }
        if (stringBuilder.length() != 0) {
            reportText = makeReport(stringBuilder.toString(),nameSupply,nameBuy);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportText);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName,e);
        }
    }

    private String makeReport(String inputInfo,String nameSupply,String nameBuy) {

        StringBuilder resultRepotr = new StringBuilder();
        String[] inputArr = inputInfo.split(System.lineSeparator());
        int countSupply = 0;
        int countBuy = 0;
        for (String lines:inputArr) {
            if (nameSupply.equals(lines.split(LIMIT_FIELD)[INDEX_OPERATION])) {
                countSupply = countSupply
                             + Integer.parseInt(lines.split(LIMIT_FIELD)[INDEX_DATA]);
            } else {
                countBuy = countBuy
                            + Integer.parseInt(lines.split(LIMIT_FIELD)[INDEX_DATA]);
            }
        }
        return (resultRepotr.append(nameSupply).append(LIMIT_FIELD)
                .append(countSupply).append(System.lineSeparator())
                .append(nameBuy).append(LIMIT_FIELD).append((countBuy))
                .append(System.lineSeparator())
                .append("result").append(LIMIT_FIELD).append(countSupply - countBuy)
                .append(System.lineSeparator()).toString());
    }
}
