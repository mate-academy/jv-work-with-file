package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_BY_THIS_STRING = "\\W+";
    private static final int EMPTY_STRING_LENGTH = 0;
    private final StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        readInformationFromFile(fromFileName);
        createReport();
        writeInformationToFile(toFileName);
    }

    private void readInformationFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentStringInFile = bufferedReader.readLine();
            while (currentStringInFile != null) {
                stringBuilder.append(currentStringInFile).append(" ");
                currentStringInFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createReport() {
        int buyCounter = 0;
        int supplyCounter = 0;
        String[] buyAndSupplyArray = stringBuilder.toString().split(SPLIT_BY_THIS_STRING);
        for (int i = 0; i < buyAndSupplyArray.length; i += 2) {
            if (buyAndSupplyArray[i].equals("supply")) {
                supplyCounter += Integer.parseInt(buyAndSupplyArray[i + 1]);
            } else if (buyAndSupplyArray[i].equals("buy")) {
                buyCounter += Integer.parseInt(buyAndSupplyArray[i + 1]);
            }
        }
        int result = supplyCounter - buyCounter;
        stringBuilder.setLength(EMPTY_STRING_LENGTH);
        stringBuilder.append("supply,").append(supplyCounter).append(System.lineSeparator())
                .append("buy,").append(buyCounter).append(System.lineSeparator())
                .append("result,").append(result);
    }

    private void writeInformationToFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
