//package core.basesyntax;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class WorkWithFile {
//    public void getStatistic(String fromFileName, String toFileName) {
//        String supply = "supply";
//        String buy = "buy";
//        int index;
//        int value;
//        int supplyCount = 0;
//        int buyCount = 0;
//        int result;
//        String example;
//        StringBuilder builder = new StringBuilder();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
//                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                index = line.indexOf(',');
//                example = line.substring(0, index);
//                value = Integer.parseInt(line.substring(index + 1));
//
//                if (example.equals(supply)) {
//                    supplyCount += value;
//                } else if (example.equals(buy)) {
//                    buyCount += value;
//                }
//            }
//
//            result = supplyCount - buyCount;
//            builder.append("supply,").append(supplyCount).append("\n");
//            builder.append("buy,").append(buyCount).append("\n");
//            builder.append("result,").append(result);
//
//            writer.write(builder.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String supply = "supply";
        String buy = "buy";
        int supplyCount = 0;
        int buyCount = 0;
        int result;
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int value = Integer.parseInt(parts[1]);

                if (operation.equals(supply)) {
                    supplyCount += value;
                } else if (operation.equals(buy)) {
                    buyCount += value;
                }
            }

            result = supplyCount - buyCount;
            builder.append("supply,").append(supplyCount).append("\n");
            builder.append("buy,").append(buyCount).append("\n");
            builder.append("result,").append(result);

            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

