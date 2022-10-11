package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    
    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        int summaSuply = 0;
        int summaBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] string = line.split(",");
                stringBuilder.append(string[0]).append(" ").append(string[1]).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] newarray = stringBuilder.toString().split("\n");
        stringBuilder = new StringBuilder();
        
        for (String s : newarray) {
            String[] drop = s.split(" ");
            if ((SUPPLY.equals(drop[0]))) {
                summaSuply = summaSuply + Integer.parseInt(drop[1]);
            } else {
                summaBuy = summaBuy + Integer.parseInt(drop[1]);
            }
            
        }
        rewriterule(stringBuilder.append(SUPPLY).append(",").append(summaSuply)
                .append("\n").append(BUY).append(",").append(summaBuy).append("\n")
                .append("result").append(",").append(summaSuply - summaBuy).toString(), toFileName);
    }
    
    private void rewriterule(String goods, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(goods);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file ", e);
        }
    }
}

