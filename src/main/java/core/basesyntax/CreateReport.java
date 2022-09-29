package core.basesyntax;

public class CreateReport {

    public String[] reportForFile(int allSupply, int allBuy, String res) {
        return new String[] {WorkWithFile.SUPPLY + WorkWithFile.COMMA
                + allSupply + System.lineSeparator(),
                WorkWithFile.BUY + WorkWithFile.COMMA + allBuy + System.lineSeparator(),
                res};
    }
}
