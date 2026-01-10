package core.basesyntax;

import java.nio.file.Files;
import java.nio.file.Path;


public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String readFromFile = Files.readString(Path.of(fromFileName));
            String[] itemsFromFile = readFromFile.split("\n");

            int totalSupply = 0;
            int totalBuy = 0;

            for (int i = 0; i < itemsFromFile.length; i++) {
                String items = itemsFromFile[i].trim();

                if (items.isEmpty()) {
                    continue;
                }

                String[] parts = items.split(",");
                String supplyOrBuy = parts[0].trim();
                String priceStr = parts[1].trim();
                int price = Integer.parseInt(priceStr);

                if (supplyOrBuy.equals("supply")) {
                    totalSupply += price;
                } else if (supplyOrBuy.equals("buy")) {
                    totalBuy += price;
                }
            }

            int result = totalSupply - totalBuy;

            StringBuilder builder = new StringBuilder();
            builder.append("supply: ").append(totalSupply).append("\n");
            builder.append("buy: ").append(totalBuy).append("\n");
            builder.append("result: ").append(result).append("\n");

            Files.writeString(Path.of(toFileName), builder.toString());

        } catch (Exception e) {
            System.out.println("Error reading file " + e);
            e.printStackTrace();
        }
    }

}

