package core.basesyntax;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        GetDateFromFile getDateFromFile = new GetDateFromFile();
        String[] dateFromFile = getDateFromFile.getDate(fromFileName).split(" ");
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String date : dateFromFile) {
            String[] splitDate = date.split(",");
            switch (splitDate[0]) {
                case "supply":
                    supplyAmount += Integer.parseInt(splitDate[1]);
                    break;
                case "buy":
                    buyAmount += Integer.parseInt(splitDate[1]);
                    break;
                default:
                    System.out.println("Something went wrong");
                    break;
            }
        }
        WriteDateToFile writeDateToFile = new WriteDateToFile();
        writeDateToFile.writeDte(supplyAmount, buyAmount, toFileName);
    }
}
