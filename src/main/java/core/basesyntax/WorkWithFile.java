package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String line = "";
        int sumSupply = 0;
        int sumBuy = 0;
        int diffSumSupplySumBuy = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                String[] dataArray = line.split(",");
                int numbers = Integer.parseInt(dataArray[1]);
                if (dataArray[0].equals("supply")) {
                    sumSupply += numbers;
                } else if (dataArray[0].equals("buy")) {
                    sumBuy = sumBuy + numbers;
                }
                diffSumSupplySumBuy = sumSupply - sumBuy;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read this file");
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write("supply," + sumSupply
                                    + "\nbuy," + sumBuy
                                    + "\nresult," + diffSumSupplySumBuy);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can not write file");
        }
    }
}
