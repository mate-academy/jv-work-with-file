package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = "\\W+";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SPLIT = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder fileContent = readFile(fromFileName);
        String[] report = getInformationForReport(fileContent);
        writeReport(report, toFileName);
    }

    private StringBuilder readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return stringBuilder;
    }

    private String[] getInformationForReport(StringBuilder fileContent) {
        String[] content = fileContent.toString().split(REGEX);
        int sumSupply = 0;
        int sumBuy = 0;
        for (int i = 0; i < content.length; i += 2) {
            if (content[i].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(content[i + 1]);
            } else {
                sumBuy += Integer.parseInt(content[i + 1]);
            }
        }
        int result = sumSupply > sumBuy ? sumSupply - sumBuy : sumBuy - sumSupply;
        String reportString = SUPPLY + "," + sumSupply + " " + BUY + "," + sumBuy + " "
                + RESULT + "," + result;
        return reportString.split(SPLIT);
    }

    private void writeReport(String[] report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String word : report) {
                bufferedWriter.write(word + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write this report", e);
        }
    }
}
