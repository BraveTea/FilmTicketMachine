import java.util.Scanner;

public class TicketMachine {

    String filmName;
    double cost;

    double amount;

    TicketMachine(){
        this.filmName = "Star Wars: A New Hope";
        this.cost = 12.63;
    }

    public void buyTicket(){
        showCost();
        abort();
        inputMoney();
        printTicket();
    }

    public void showCost(){
        System.out.printf("%s costs %.2f euros%n", filmName, cost);
    }

    public void abort(){
        System.out.println("Do you wish to continue? Type 'Y' for Yes and 'N' for No");
        Scanner userInput = new Scanner(System.in);
        String choice = userInput.next();
//        if (choice.equals("N")){ //I used ' == ' first but didn't work. Searched for it, got answer: In simple words, == checks if both objects point to the same memory location whereas . equals() evaluates to the comparison of values in the objects.
//            returnMoney();
//            System.out.println("Thank you for using this machine. Have a great day.");
//            System.exit(0);
//        }
//        else if (choice.equals("Y")){
//            }
//        else {
//            abort();
//            }
        switch (choice){
            case "N" :
                returnMoney();
                System.out.println("Thank you for using this machine. Have a great day.");
                System.exit(0);
            case "Y" :
            System.out.println("Please input coins.");
                break; //does "press any key" work like this where it simply falls through?
            default :
                abort();
        }
    }

    private void returnMoney() {
        //this method will return all money left to the user.
        //Either all of it in case of an abort or it return change after ticket is paid for
        if (amount > 0){
            System.out.printf("Here is your %.2f euros back%n", amount);
        }

    }

    private void returnChange(){ //Normally this should have a return type because it would actually have to do something. That would also make it easier to use it in a check on printTicket() as it is it is just a bunch of printlns :)
        double change = amount - cost;
        System.out.printf("%nHere is your %.2f euros in change%n", change);
        System.out.println("Thank you for using this machine. Have a great day.");
        System.exit(0);

    }

    public double inputMoney() { //this isn't aptly named. I reckon this should have been split between two methods: inputMoney and returnAmount. one to check and the other to return.
        System.out.printf("%.2f left to pay. Please input coins.%n", cost-amount);
        while (amount < cost) {
            Scanner userInput = new Scanner(System.in);

            if (userInput.hasNextDouble()) { //check to see input == double
                double coin = userInput.nextDouble();
                if (coin > 0) {
                    amount += coin;
                } else {
                    System.out.printf("You have tried to pay with %.2f.%nPlease pay with positive amounts.%n", coin);
                }
                System.out.printf("%.2f left to pay.%n", cost - amount);
                if (amount < cost) {
                    abort();
                }
            }
            else{ //if input isn't a double, let user know, give abort choice and have them try again if not aborted
                System.out.println("Your current input is incorrect. Please enter a numeral");
                abort();
            }
        }
        return amount; //right... this is never used. It is just an variable update. I could/should make this void.
    }

    public void printTicket(){
        if (amount >= cost){
            System.out.printf("%nOne ticket of admission to %s.%n" +
                    "Price: %.2f euros.%n" +
                    "Have a nice viewing!%n", filmName, cost);
            if(amount > cost){
                returnChange();
            }
        }
    }
}
