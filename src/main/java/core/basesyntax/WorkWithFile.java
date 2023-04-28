package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        data = logicForResult(data);
        writeFile(toFileName, data);
    }

    private String readFile(String fromFileName) {
        String line;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read the file: " + fromFileName,e);
        }
        return builder.toString();
    }

    private void writeFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write this ti the file" + toFileName,e);
        }
    }

    private String logicForResult(String data) {
        int boughtSum = 0;
        int supplySum = 0;
        String[] splitedData;
        StringBuilder result = new StringBuilder();
        String[] arrayWithData = data.split(System.lineSeparator());
        for (String arrayWithDatum : arrayWithData) {
            splitedData = arrayWithDatum.split(",");
            if (splitedData[0].equals("supply")) {
                supplySum = supplySum + Integer.parseInt(splitedData[1]);
            } else {
                boughtSum = boughtSum + Integer.parseInt(splitedData[1]);
            }
        }
        result.append("supply,").append(supplySum)
                .append(System.lineSeparator()).append("buy,")
                .append(boughtSum).append(System.lineSeparator())
                .append("result,").append(supplySum - boughtSum);
        return result.toString().trim();
    }
}
