package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUT_INDEX = 1;
    
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName).split("\n");
        String report = createReport(dataFromFile);
        writeFile(report, toFileName);
    }
    
    private void writeFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file ", e);
        }
    }
    
    private String readFile(String fromFileName) {
        String readLineFIle;
        StringBuilder stringBuilder = new StringBuilder();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((readLineFIle = bufferedReader.readLine()) != null) {
                stringBuilder.append(readLineFIle).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file", e);
        }
        return stringBuilder.toString();
    }
    
    private String createReport(String[] dataFromFile) {
        int summaSuply = 0;
        int summaBuy = 0;
        
        for (String s : dataFromFile) {
            String[] dropLine = s.split(",");
            if ((SUPPLY.equals(dropLine[OPERATION_INDEX]))) {
                summaSuply = summaSuply + Integer.parseInt(dropLine[AMOUT_INDEX]);
            } else {
                summaBuy = summaBuy + Integer.parseInt(dropLine[AMOUT_INDEX]);
            }
        }
        return new StringBuilder().append(SUPPLY).append(",").append(summaSuply)
                .append("\n").append(BUY).append(",").append(summaBuy).append("\n")
                .append("result").append(",").append(summaSuply - summaBuy).toString();
    }
}

