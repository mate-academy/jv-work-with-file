package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final int VALUE_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";


    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        int BuySum = 0;
        int SupplySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader(dataFromFile))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String [] arrayFile = value.split(SEPARATOR);
                if (arrayFile[VALUE_INDEX].equals(BUY)) {
                    BuySum += Integer.parseInt(arrayFile[BUY_INDEX]);
                } else {
                    SupplySum += Integer.parseInt(arrayFile[BUY_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't create file" + dataFromFile, e);
        }
        return stringBuilder.append(SUPPLY).append(SEPARATOR).append(SupplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(BuySum)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(SupplySum - BuySum)
                .toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }
}



