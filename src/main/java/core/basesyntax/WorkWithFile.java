package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File checkFile = new File(fromFileName);
        File newFile = new File(toFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(checkFile))) {
            String reader = bufferedReader.readLine();
            while (reader != null) {
                builder.append(reader).append(System.lineSeparator());
                reader = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find file" + checkFile.getName());
        }
        String[] allText = builder.toString().split("\\W");
        int countSupply = 0;
        int countBuy = 0;
        for (int i = 0; i < allText.length; i++) {
            if (allText[i].equals("supply")) {
                countSupply += Integer.parseInt(allText[i + 1]);
            }
            if (allText[i].equals("buy")) {
                countBuy += Integer.parseInt(allText[i + 1]);
            }
        }
        int result = countSupply - countBuy;
        String report = "supply," + countSupply + System.lineSeparator()
                + "buy," + countBuy + System.lineSeparator()
                + "result," + result;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile,false))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't find file" + e);
        }
    }
}
