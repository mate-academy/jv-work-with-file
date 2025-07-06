package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        String inputString;
        String csvSplitBy = ",";
        ArrayList<StatisticsElement> listElement = new ArrayList<>();
        StatisticsElement elem;
        //read

        try {

            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));

            while ((inputString = reader.readLine()) != null) {

                String[] strData = inputString.split(csvSplitBy);

                String name = strData[0];
                String strSum = strData[1];

                int sum = 0;
                if (!strSum.isEmpty()) {
                    sum = Integer.parseInt(strSum);
                }

                elem = getStatElem(name,listElement);
                if (elem != null && sum != 0) {
                    elem.setSum(sum);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t find file",e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a line from file",e);
        }

        //System.out.println("write to file " + listElement.size() + " elements");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            StatisticsElement elemSupply;
            StatisticsElement elemBuy;

            if (!listElement.isEmpty()) {

                if (listElement.get(1).getName().equals("supply")) {
                    elemSupply = listElement.get(1);
                    elemBuy = listElement.get(0);
                } else {
                    elemSupply = listElement.get(0);
                    elemBuy = listElement.get(1);
                }

                writer.write(elemSupply.getName() + ","
                                 + elemSupply.getSum()
                                 + System.lineSeparator());
                writer.write(elemBuy.getName() + ","
                                 + elemBuy.getSum()
                                 + System.lineSeparator());
                writer.write("result," + (elemSupply.getSum() - elemBuy.getSum()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public StatisticsElement getStatElem(String str,ArrayList<StatisticsElement> listElement) {

        StatisticsElement elem = null;

        for (StatisticsElement elemArr : listElement) {
            String name = elemArr.getName();

            if (name.equals(str)) {
                return elemArr;
            }
        }

        if (elem == null) {
            elem = new StatisticsElement(str);
            listElement.add(elem);
        }

        return elem;
    }

}
