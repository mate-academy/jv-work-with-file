//FIRST VARIANT
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

//SECOND VARIANT

package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Result output = readFile(fromFileName);
        String result2 = getResult(output);
        writeToFile(result2, toFileName);
    }

    public static Result readFile(String fileName) {
        int[] numbers;
        String[] variables;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                BufferedReader newReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int value = 0;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            variables = new String[count];
            numbers = new int[count];
            int i = 0;
            while ((line = newReader.readLine()) != null) {
                String[] parts = line.split(",");
                variables[i] = parts[0];
                numbers[i] = Integer.parseInt(parts[1]);
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException("File " + fileName + " was not found", e);
        }
        return new Result(variables, numbers);
    }

    public static String getResult(Result output) {
        String supply = "supply";
        String buy = "buy";
        int countSupply = 0;
        int countBuy = 0;
        int result = 0;
        StringBuilder builder = new StringBuilder();
        String[] variables = output.getVariables();
        int[] numbers = output.getNumbers();
        for (int i = 0; i < variables.length; i++) {
            if (variables[i].equals(supply)) {
                countSupply += numbers[i];
            } else {
                countBuy += numbers[i];
            }
        }
        result = countSupply - countBuy;
        builder.append("supply,").append(countSupply).append(System.lineSeparator());
        builder.append("buy,").append(countBuy).append(System.lineSeparator());
        builder.append("result,").append(result);
        return builder.toString();
    }

    public static void writeToFile(String text, String toFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can not write information to the file", e);
        }
    }
}
