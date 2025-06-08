package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String result = readFromFile(fromFileName);
        writeToFile(toFileName, result);
    }
    
    public static String readFromFile(String fromFileName) {
        StringBuilder newCv = new StringBuilder();
        Path path = Paths.get(fromFileName);
        
        try {
            List<String> lines = Files.readAllLines(path);
            int supluyTotal = 0;
            int buyTotal = 0;
            for (String line : lines) {
                String[] parts = line.split(",");
                String type = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                
                if (type.equals("buy")) {
                    buyTotal += value;
                } else if (type.equals("supply")) {
                    supluyTotal += value;
                }
            }
            int result = supluyTotal - buyTotal;
            newCv.append("supply,").append(supluyTotal).append(System.lineSeparator());
            newCv.append("buy,").append(buyTotal).append(System.lineSeparator());
            newCv.append("result,").append(result).append(System.lineSeparator());
            return newCv.toString();
            
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName + "\n", e);
        }
    }
    
    public static void writeToFile(String toFileName, String content) {
        Path path = Paths.get(toFileName);
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName + "\n", e);
        }
    }
}
