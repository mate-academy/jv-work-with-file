package core.basesyntax;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        GetInformationFromFile getDateFromFile = new GetInformationFromFile();
        String dateFromFileStr = getDateFromFile.getInformation(fromFileName);
        int supplyAmount = 0;
        int buyAmount = 0;
        if (dateFromFileStr != null) {
            String[] dateFromFile = dateFromFileStr.split(" ");
            for (String date : dateFromFile) {
                if (date == null || date.trim().isEmpty()) {
                    continue;
                }
                String[] splitDate = date.split(",");
                if (splitDate.length != 2) {
                    continue;
                }
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
        }
        WriteInformationToFile writeDateToFile = new WriteInformationToFile();
        writeDateToFile.writeInformation(supplyAmount, buyAmount, toFileName);
    }
}
