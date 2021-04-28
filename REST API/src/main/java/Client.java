public class Client {
    public enum accountType {single,joint}
    private String name;
    private int balance;
    private accountType type;
    private String password;

    Client(String name, String password, int balance,accountType type){
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public accountType getType() {
        return type;
    }

    public void setType(accountType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(int value) { balance += value; }

    public void withdraw(int value) throws Exception {
        if(balance-value >= 0){
            balance -= value;
        } else {
            throw new Exception("Error: insufficient funds.");
        }
    }
}
