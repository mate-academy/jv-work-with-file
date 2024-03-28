package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] separatedLine = line.split(",");
                if (separatedLine[0].equals("supply")) {
                    supply += Integer.parseInt(separatedLine[1]);
                } else if (separatedLine[0].equals("buy")) {
                    buy += Integer.parseInt(separatedLine[1]);
                }
                line = reader.readLine();
            }
            result = supply - buy;
            writer.write("supply," + supply + "\n");
            writer.write("buy," + buy + "\n");
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = supply - buy;
        System.out.println("Supply: " + supply + "\n" + "Buy: " + buy + "\n" + "Result: " + result);
    }
}
