package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder sb = new StringBuilder();
        int buy = 0;
        int supply = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line = br.readLine();
            while (line != null) {
                String operation = line.split(",")[0];
                int quantity = Integer.parseInt(line.split(",")[1]);
                line = br.readLine();
                switch (operation) {
                    case "buy":
                        buy += quantity;
                        break;
                    case "supply":
                        supply += quantity;
                        break;
                    default:
                        System.out.println("Invalid operation");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + fromFileName);
        }
        sb.append("supply,").append(supply).append(System.lineSeparator());
        sb.append("buy,").append(buy).append(System.lineSeparator());
        sb.append("result,").append(supply - buy).append(System.lineSeparator());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Error writing into file " + toFileName);
        }
    }
}
