package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        int totalNumberOfSeats = rows * seats;
        int numberOfPurchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = calculateTotalIncome(rows, seats);

        String[][] seatsMap = new String[rows + 1][seats + 1];

        initializeSeats(rows, seats, seatsMap);
        printMenu();

        int menuSelection = scanner.nextInt();

        while(menuSelection != 0) {
            switch(menuSelection) {
                case 1: 
                    printSeats(rows, seats, seatsMap);
                    break;
                case 2:
                    System.out.println("Enter a row number:");
                    int rowNumber = scanner.nextInt();

                    System.out.println("Enter a seat number in that row:");
                    int seatNumber = scanner.nextInt();

                    boolean isValid = setSeat(rowNumber, seatNumber, seats, rows, seatsMap);

                    while(!isValid) {
                        System.out.println("Enter a row number:");
                        rowNumber = scanner.nextInt();

                        System.out.println("Enter a seat number in that row:");
                        seatNumber = scanner.nextInt();

                        isValid = setSeat(rowNumber, seatNumber, seats, rows, seatsMap);
                    }
                    currentIncome += calculateTicketPrice(rows, totalNumberOfSeats, rowNumber);
                    numberOfPurchasedTickets += 1;
                    break;
                case 3:
                    float percentage = numberOfPurchasedTickets / (float) totalNumberOfSeats;
                    printStatistics(numberOfPurchasedTickets, percentage * 100, currentIncome, totalIncome);
                    break;
                default:
                    break;
            }
            printMenu();
            menuSelection = scanner.nextInt();
        }
    }

    public static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void initializeSeats(int rows, int seats, String[][] seatsMap) {
        for(int i = 0; i <= rows; i++) {
            for(int j = 0; j <= seats; j++) {
                if(i != 0 && j != 0) {
                    seatsMap[i][j] = "S";
                }
            }
        }
    }

    public static boolean setSeat(int row, int seat, int seats, int rows, String[][] seatsMap) {
        if(row < 0 || seat < 0 || row > rows || seat > seats) {
            System.out.println("Wrong input!");
            return false;
        } else if(seatsMap[row][seat] == "B") {
            System.out.println("That ticket has already been purchased!");
            return false;
        } 
        seatsMap[row][seat] = "B";
        return true;
    }

    public static void printSeats(int rows, int seats, String[][] seatsMap) {
        System.out.println("Cinema:");
        for(int i = 0; i <= rows; i++) {
            if(i != 0) {
                System.out.printf("%d ", i);
            }
            for(int j = 0; j <= seats; j++) {
                if(i == 0) {
                    if(j == 0) {
                        System.out.print("  ");
                    }
                    if(j != 0) {
                        System.out.printf("%d ", j);
                    }
                    if(j == seats) {
                        System.out.println(" ");
                    }
                } else {
                    if(j != 0) {
                        System.out.printf("%s ", seatsMap[i][j]);
                    }
                    if(j == seats) {
                        System.out.println("");
                    }
                }
            }
        }
    }

    public static void printStatistics(int purchasedTickets, float percentage, int currentIncome, int totalIncome) {
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
        
    }

    public static int calculateTotalIncome(int rows, int seats) {
        int firstHalf = rows / 2;
        int secondHalf = rows - firstHalf;
        if(secondHalf == 0) {
            int totalIncome = firstHalf * seats * 18;
            return totalIncome;
        } else {
            int totalIncome = firstHalf * seats * 10 + secondHalf * seats * 8;
            return totalIncome;
        }
    }

    public static int calculateTicketPrice(int rows, int totalNumberOfSeats, int rowNumber) {
        if(totalNumberOfSeats <= 60) {
            System.out.println("Ticket price: $10");
            return 10;
        } else {
            int firstHalf = rows / 2;

            if(rowNumber <= firstHalf) {
                System.out.println("Ticket price: $10");
                return 10;
            } else {
                System.out.println("Ticket price: $8");
                return 8;
            }
        }

    }
}