package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private int countSupply;
    private int countBuy;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    public String readFromFile(String fileName) {
        StringBuilder infoToFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] info = value.split(",");
                if (info[0].equals("supply")) {
                    countSupply += Integer.parseInt(info[1]);
                } else {
                    countBuy += Integer.parseInt(info[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read this file!", e);
        }
        return infoToFile.append("supply,").append(countSupply).append(System.lineSeparator())
                .append("buy,").append(countBuy).append(System.lineSeparator())
                .append("result,").append(countSupply - countBuy).toString();
    }

    public void writeToFile(String fileName, String inFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));) {
            bufferedWriter.write(inFile);
        } catch (Exception e) {
            throw new RuntimeException("Can't write information to this file!", e);
        }
    }
}
