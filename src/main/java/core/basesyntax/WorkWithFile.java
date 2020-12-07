package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            writeFile(toFileName,prepareFile(getFile(fromFileName)));
        } catch (IOException e) {
            System.out.println("File was not found");
        }
    }

    private String getFile(String fromFileName) throws IOException {
        StringBuilder file = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(new FileInputStream(fromFileName)));
        String newLine = bufferedReader.readLine();
        while (newLine != null) {
            file.append(newLine.toLowerCase(Locale.ROOT)).append(" ");
            newLine = bufferedReader.readLine();
        }
        return file.toString();
    }

    private String prepareFile(String file) {
        int sumOfSupply = 0;
        int sumOfbuy = 0;
        String[] fileToArr = file.replace(',', ' ').split(" ");
        for (int i = 0; i < fileToArr.length - 1; i += 2) {
            if (fileToArr[i].equals("buy")) {
                sumOfbuy += Integer.parseInt(fileToArr[i + 1]);
            } else if (fileToArr[i].equals("supply")) {
                sumOfSupply += Integer.parseInt(fileToArr[i + 1]);
            }
        }
        return "supply," + sumOfSupply + System.lineSeparator()
                + "buy," + sumOfbuy + System.lineSeparator() + "result,"
                + (sumOfSupply - sumOfbuy);
    }

    private boolean writeFile(String fileName, String fileInPut) {
        File file = new File(fileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(fileInPut);
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("Can't write to this file");
            return false;
        }
    }
}
