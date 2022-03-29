package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String string : readerService(fromFileName)) {
            String[] s = string.split(",");
            if (s[0].equals("supply")) {
                supplyCounter += Integer.parseInt(s[1]);
            } else {
                buyCounter += Integer.parseInt(s[1]);
            }
        }
        stringBuilder.setLength(0);
        stringBuilder.append("supply,").append(supplyCounter).append(System.lineSeparator())
                .append("buy,").append(buyCounter).append(System.lineSeparator())
                .append("result,").append(supplyCounter - buyCounter);
        writerService(stringBuilder.toString(), toFileName);
    }

    private String[] readerService(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found: " + fromFileName, e);
        }
        return stringBuilder.toString().split(" ");
    }

    private void writerService(String result, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file", e);
        }
    }

}
