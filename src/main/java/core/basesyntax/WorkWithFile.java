package core.basesyntax;

import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
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

                if (SUPPLY.equals(supplyOrBuy)) {
                    totalSupply += price;
                } else if (BUY.equals(supplyOrBuy)) {
                    totalBuy += price;
                }
            }

            int result = totalSupply - totalBuy;

            StringBuilder builder = new StringBuilder();
            builder.append("supply,").append(totalSupply).append("\n");
            builder.append("buy,").append(totalBuy).append("\n");
            builder.append("result,").append(result).append("\n");

            Files.writeString(Path.of(toFileName), builder.toString());

        } catch (Exception e) {
            throw new RuntimeException("Can't work with file", e);
            //e.printStackTrace();
        }
    }

}

