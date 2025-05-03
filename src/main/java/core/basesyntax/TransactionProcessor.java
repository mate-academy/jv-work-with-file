package core.basesyntax;

public class TransactionProcessor {
    private int totalSupply = 0;
    private int totalBuy = 0;

    public void process(String transactionLine) {
        // проверяю на наличие транзакций
        if (transactionLine == null || transactionLine.isEmpty()) {
            throw new RuntimeException("There are no transactions in the file");
        }

        String[] parts = transactionLine.split(",");
        if (parts.length != 2) {
            throw new RuntimeException("Transactional more than two " + transactionLine);

        }
        //делю файл на две части
        String operationType = parts[0].trim();
        int amount = Integer.parseInt(parts[1].trim());

        if ("supply".equals(operationType)) {
            totalSupply += amount;
        } else if ("buy".equals(operationType)) {
            totalBuy += amount;
        }
    }

    public int getTotalSupply() {
        return totalSupply;
    }

    public int getTotalBuy() {
        return totalBuy;
    }

    public int getResult() {
        return totalSupply - totalBuy;
    }
}
