package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SENTENCE_SPLITTER = "next sentence";

    private String[] getDataFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
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

    private String[] createReport(String[] someData) {
        int supplyPrice = 0;
        int buyPrice = 0;
        String[] report = new String[3];

        for (String someDatum : someData) {
            if (someDatum.contains("supply")) {
                supplyPrice += Integer.parseInt(someDatum.substring(someDatum
                        .indexOf(",") + 1, someDatum.length()));

            } else {
                buyPrice += Integer.parseInt(someDatum.substring(someDatum
                        .indexOf(",") + 1, someDatum.length()));
            }

            report[0] = "supply" + "," + supplyPrice;
            report[1] = "buy" + "," + buyPrice;
            report[2] = "result," + (supplyPrice - buyPrice);
        }
        return report;
    }

    private void writeToFile(String[] someReport, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String s : someReport) {
                bufferedWriter.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String[] information = getDataFromFile(fromFileName);
        String[] reportAboutInformation = createReport(information);
        writeToFile(reportAboutInformation, toFileName);
    }
}
