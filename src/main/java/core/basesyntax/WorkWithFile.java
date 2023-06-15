package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataToAnalyze = readInputFile(fromFileName);
        String results = getStatisticValuesFromRetrievedData(dataToAnalyze, fromFileName);
        writeStatisticToFile(results, toFileName);
    }

    private String getStatisticValuesFromRetrievedData(List<String> data, String inputFileName) {
        int buyAmount = 0;
        int supplyAmount = 0;

        for (String str : data) {
            if (str.startsWith("buy")) {
                buyAmount += Integer.parseInt(str.replaceFirst("\\w+,", ""));
            } else {
                supplyAmount += Integer.parseInt(str.replaceFirst("\\w+,", ""));
            }
        }
        int result = supplyAmount - buyAmount;
        return String.format("supply,%d%nbuy,%d%nresult,%d", supplyAmount, buyAmount, result);
    }

    private void writeStatisticToFile(String statistic, String outputFileName) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            bfWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to" + outputFileName, e);
        }
    }

    private List<String> readInputFile(String inputFileName) {
        List<String> retrievedData = new ArrayList<>();
        try (BufferedReader bfReader = new BufferedReader(new FileReader(inputFileName))) {
            String str = bfReader.readLine();
            while (str != null) {
                retrievedData.add(str);
                str = bfReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read the file" + inputFileName, e);
        }
        return retrievedData;
    }
}
