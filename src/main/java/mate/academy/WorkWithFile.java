package mate.academy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(System.lineSeparator()).append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file, e");
        }
        String content = stringBuilder.toString();
        String resultReport = createReport(content);
        writeToFIle(resultReport, toFileName);
    }

    public String createReport(String input) {
        String[] splitInput = input.split("\\W+");
        int ammountSupply = 0;
        int ammountBuy = 0;
        int result = 0;
        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("supply")) {
                ammountSupply = ammountSupply + Integer.parseInt(splitInput[i + 1]);
            }
            if (splitInput[i].equals("buy")) {
                ammountBuy = ammountBuy + Integer.parseInt(splitInput[i + 1]);
            }
            result = ammountSupply - ammountBuy;
        }
        String resultReport = "supply," + ammountSupply + System.lineSeparator()
                + "buy," + ammountBuy + System.lineSeparator() + "result," + result;
        System.out.println(resultReport);
        return resultReport;
    }

    public void writeToFIle(String report, String file) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file, e");
        }
    }
}

