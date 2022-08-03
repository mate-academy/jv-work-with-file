package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String OPERATION_BUY = "buy";
    private static final String DEMILITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalBuy = 0;
        int totalSupply = 0;
        File file = new File(fromFileName);

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String [] data;

            while ((line = bufferedReader.readLine()) != null) {
                data = line.split(DEMILITER);
                if (data[0].equals(OPERATION_BUY)) {
                    totalBuy += Integer.parseInt(data[0]);
                } else {
                    totalSupply += Integer.parseInt(data[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant get data from file - " + fromFileName, e);
        }
        writeInResult(toFileName, totalSupply, totalBuy);
    }

    private String createReport(int totalSupply, int totalBuy) {
        int result = totalSupply - totalBuy;

        return "supply," + totalSupply
                + System.lineSeparator()
                + "buy," + totalBuy
                + System.lineSeparator()
                + "result," + result;
    }

    private void writeInResult(String toFile, int totalSupply, int totalBuy) {
        String reportToWrite = createReport(totalSupply, totalBuy);
        File inputFile = new File(toFile);

        try (FileWriter fileWriter = new FileWriter(inputFile)) {
            fileWriter.write(reportToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFile, e);
        }
    }
}
