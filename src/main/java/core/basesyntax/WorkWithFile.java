package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String results = getStatisticValuesFromInputFile(fromFileName);
        writeStatisticToFile(results, toFileName);
    }

    private String getStatisticValuesFromInputFile(String inputFileName) {
        int buyAmount = 0;
        int supplyAmount = 0;
        try (BufferedReader bfReader = new BufferedReader(new FileReader(inputFileName))) {
            String str = bfReader.readLine();
            while (str != null) {
                if (str.startsWith("b")) {
                    buyAmount += Integer.parseInt(str.replaceFirst("\\w+,", ""));
                } else {
                    supplyAmount += Integer.parseInt(str.replaceFirst("\\w+,", ""));
                }
                str = bfReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int result = supplyAmount - buyAmount;
        return String.format("supply,%d%nbuy,%d%nresult,%d", supplyAmount, buyAmount, result);
    }

    private void writeStatisticToFile(String statistic, String outputFileName) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            bfWriter.write(statistic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
