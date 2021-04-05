package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SENTENCE_SPLITTER = "next sentence";
    private static final String SUBSTRING_REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] information = getDataFromFile(fromFileName);
        String[] reportAboutInformation = createReport(information);
        writeToFile(reportAboutInformation, toFileName);
    }

    private String[] getDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(SENTENCE_SPLITTER);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(SENTENCE_SPLITTER);
    }

    private String[] createReport(String[] records) {
        int supplyPrice = 0;
        int buyPrice = 0;
        String[] report = new String[3];

        for (String someDatum : records) {
            if (someDatum.contains("supply")) {
                supplyPrice += Integer.parseInt(someDatum.substring(someDatum
                        .indexOf(SUBSTRING_REGEX) + 1));

            } else {
                buyPrice += Integer.parseInt(someDatum.substring(someDatum
                        .indexOf(SUBSTRING_REGEX) + 1));
            }

            report[0] = "supply" + "," + supplyPrice;
            report[1] = "buy" + "," + buyPrice;
            report[2] = "result," + (supplyPrice - buyPrice);
        }
        return report;
    }

    private void writeToFile(String[] reports, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String s : reports) {
                bufferedWriter.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}
