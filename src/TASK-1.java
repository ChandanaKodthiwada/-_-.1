import java.util.Scanner;
class ATMInterface {
    public static int takenIntegerInput(int limit) {
        boolean flag = false;
        int input = 0;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if (input > limit || input < 1) {
                    flag = false;
                    System.out.println("Choose the number between 1 to " + limit);
                }
            }
            catch (Exception e) {
                System.out.println("Enter only integer value.");
            }
        }
        return input;
    }
    public static void main(String[] args) {
        System.out.println("WELCOME!!!!\n" + "********************ATM INTERFACE*******************");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose one option: ");
        int choose = takenIntegerInput(2);
        if (choose == 1) {
            ATMBankAccount b = new ATMBankAccount();
            b.register();
            while (true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("Enter your choice: ");
                int ch = takenIntegerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("\n********************WELCOME BACK" + b.name + "*******************");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1.withdraw \n2.Deposit \n3.Transfer \n4.check balance \n5.Transaction History \n6.Exit");
                            System.out.println("Enter your choice: ");
                            int c = takenIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transactionHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }

            }
        } else {
            System.exit(0);
        }
    }
}

/*do not forget add a feature like when registered the system will ask you to deposit 10000 rupees */
class ATMBankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    String transactionHistory = "";
    float balance = 0;
    int transactions = 0;

    public void  register(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name:");
        this.name = sc.nextLine();
        System.out.println("Enter UserName");
        this.userName = sc.nextLine();
        System.out.println("Enter password");
        this.password = sc.nextLine();
        System.out.println("Enter Account no.");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration Successful. Please Log in to your Bank Account");
    }
    public boolean login(){
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while(!isLogin){
            System.out.println("Enter UserName");
            String UserName = sc.nextLine();
            if(userName.equals(UserName)) {
                System.out.print("Enter Password");
                String Password = sc.nextLine();
                if( password.equals(Password)){
                    isLogin = true;
                    System.out.println("Login Successful");
                    System.out.println("please deposit a certain amount to start ATM");
                }
                else{
                    System.out.println("Invalid Password");
                }
            }
            else{
                System.out.println("Invalid UserName");
            }
        }
        return isLogin;
    }
    public void deposit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount");
        float amount = sc.nextFloat();
        try{
            if(amount<=10000f){
                transactions++;
                balance+=amount;
                System.out.println("Deposit successful");
                String str = "Rs"+ amount + " deposited"+"\n";
                transactionHistory = transactionHistory.concat(str);
            }
            else{
                System.out.println("Amount limit is 10000");
            }
        }
        catch (Exception _){

        }
    }
    public void withdraw(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount");
        float amount = sc.nextFloat();
        try{
            if(balance>=amount){
                transactions++;
                balance-=amount;
                System.out.println("amount withdrawn successful");
                String str = "Rs"+ amount + " withdrawn"+"\n";
                transactionHistory = transactionHistory.concat(str);
            }
            else{
                System.out.println("Insufficient balance");
            }
        }
        catch (Exception _){

        }

    }
    public void checkBalance(){
        System.out.println("balance amount:" + balance);
    }
    public void transfer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter recipient");
        String recipient = sc.nextLine();
        System.out.println("Enter amount");
        float amount = sc.nextFloat();
        try{
            if(amount<=balance && amount<=50000f){
                transactions++;
                balance-=amount;
                System.out.println("Transaction successful");
                String str = "Rs" + amount + " transferred to" +" "+recipient+"\n";
                transactionHistory = transactionHistory.concat(str);

            }
            else{
                System.out.println("check whether you have sufficient balance and also check whether the transferred amount is less than Rs.50000");
            }

        }
        catch (Exception _){

        }
    }
    public void transactionHistory(){
        if(transactions==0){
            System.out.println("No Transactions happened");
        }
        else {
            System.out.print(transactionHistory);
        }
    }

}

