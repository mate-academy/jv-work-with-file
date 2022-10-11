package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;
    private int summaSuply = 0;
    private int summaBuy = 0;
    
    public void getStatistic(String fromFileName, String toFileName) {
        
        String[] readToArray = readFile(fromFileName).split("\n");
        
        for (String s : readToArray) {
            String[] dropLine = s.split(" ");
            if ((SUPPLY.equals(dropLine[FIRST_ELEMENT]))) {
                summaSuply = summaSuply + Integer.parseInt(dropLine[SECOND_ELEMENT]);
            } else {
                summaBuy = summaBuy + Integer.parseInt(dropLine[SECOND_ELEMENT]);
            }
        }
        writeFile(getString(), toFileName);
    }
    
    private void writeFile(String goods, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(goods);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file ", e);
        }
    }
    
    private String readFile(String fromFileName) {
        String readLineFIle;
        StringBuilder stringBuilder = new StringBuilder();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((readLineFIle = bufferedReader.readLine()) != null) {
                String[] string = readLineFIle.split(",");
                stringBuilder.append(string[FIRST_ELEMENT]).append(" ")
                        .append(string[SECOND_ELEMENT])
                        .append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file", e);
        }
        return stringBuilder.toString();
    }
    
    private String getString() {
        return new StringBuilder().append(SUPPLY).append(",").append(summaSuply)
                .append("\n").append(BUY).append(",").append(summaBuy).append("\n")
                .append("result").append(",").append(summaSuply - summaBuy).toString();
    }
}

