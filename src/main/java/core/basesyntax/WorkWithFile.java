package core.basesyntax;


import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                bufferedWriter.write(supplyResult);
                bufferedWriter.write(buyResult);
                bufferedWriter.write(resultResult);
            }
            String value = bufferedReader.readLine();
            int supplySum = 0;
            int buySum = 0;
            int result = 0;
            String[] parts = new String[0];
            while (value != null) {
                parts = value.split(",");
                if (parts[0].equals("supply")) {
                    supplySum += Integer.parseInt(parts[1]);
                }
                if (parts[0].equals("buy")) {
                    buySum += Integer.parseInt(parts[1]);
                }
                value = bufferedReader.readLine();
            }
            result = supplySum - buySum;
            String supplyResult = "supply," + supplySum + "\n";
            String buyResult = "buy," + buySum + "\n";
            String resultResult = "result," + result + "\n";
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t found the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
    }
}
