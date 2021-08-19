package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public static void main(String[] args) {// for TEST and Debug
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "text.txt");
    }

    public void getStatistic(String fromFileName, String toFileName) {
        final String SUPPLY = "supply";
        final String BUY = "buy";
        int addSupply = 0;
        int addBay = 0;
        int result = 0;

        File fileIn = new File(fromFileName);
        File fileOut = new File(toFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileIn))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[0].equals(SUPPLY)) {
                    addSupply += Integer.parseInt(split[1]);
                } else if (split[0].equals(BUY)) {
                    addBay += Integer.parseInt(split[1]);
                }
                value = bufferedReader.readLine();
            }
            result = addSupply - addBay;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut, true))){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(",").append(addSupply).append(System.lineSeparator())
                            .append(BUY).append(",").append(addBay).append(System.lineSeparator())
                            .append("result").append(",").append(result);
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fromFileName, e);
        }
    }
}
