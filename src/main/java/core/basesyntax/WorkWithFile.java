package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            List<String> list = new ArrayList<>();
            Set<String> set = new HashSet<>();

            while ((line = br.readLine()) != null) {
                String [] values = line.split(",");
                set.add(values[0]); // set imion
                list.add(line); // lista lin
            }
            int sum = 0;
            int supplyMoney = 0;
            int buyMoney = 0;
            List<String> firstList = new ArrayList<>(set);
            List<String> secondList = new ArrayList<>();
            if (firstList.get(0).equals("buy")) {
                for (int i = firstList.size() - 1; i >= 0; i--) {
                    secondList.add(firstList.get(i));
                }
            }
            for (String s: secondList) {
                for (String info: list) {
                    String [] insideValues = info.split(",");
                    if (s.equals(insideValues[0])) {
                        sum += Integer.parseInt(insideValues[1]);
                    }
                }
                if (s.equals("supply")) {
                    supplyMoney = sum;
                } else {
                    buyMoney = sum;
                }
                sb.append(s).append(",").append(sum).append(System.lineSeparator());
                sum = 0;
            }
            int difference = supplyMoney - buyMoney;
            sb.append("result").append(",").append(difference).append(System.lineSeparator());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(toFileName);
    }

}

