import java.util.Scanner;

public class PlaneManagement {

    public static void buy_seat(int[] seatsInRow, int[][] seats, Ticket[][] ticketList){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the row (A, B, C, D): ");
        char rowChar = input.next().toUpperCase().charAt(0);

        System.out.print("Enter the column: ");
        int column = input.nextInt();

        int row = rowChar - 'A';

        if (row >= 0 && row < 4 && column >= 1 && column <= seatsInRow[row] && seats[row][column - 1] == 0){
            seats[row][column - 1] = 1;

            System.out.println("Enter the name: ");
            String name=input.next();
            System.out.println("Enter the surname: ");
            String surname=input.next();
            System.out.println("Enter the email: ");
            String email=input.next();

            Person person1=new Person(name, surname, email);

            double price;
            if (column<=5){
                price=200;
            } else if (column<=9) {
                price=150;
            }else {
                price=180;
            }

            Ticket ticket1=new Ticket(rowChar, column, price, person1);
            ticketList[row][column - 1] = ticket1;

            ticket1.save();


            System.out.println("Seat reserved successfully!");
        } else if (row < 0 || row >= 4 || column < 1 || column > seatsInRow[row]) {
            System.out.println("Invalid seat. Please enter a valid seat.");
        }
        else{
            System.out.println("Seat is already taken!");
        }

    }

    public static void cancel_seat(int[] seatsInRow, int[][] seats, Ticket[][] ticketList){
        Scanner input=new Scanner(System.in);

        System.out.print("Enter the row (A, B, C, D): ");
        char rowChar = input.next().toUpperCase().charAt(0);

        System.out.print("Enter the column: ");
        int column = input.nextInt();

        int row = rowChar - 'A';

        if (row >= 0 && row < 4 && column>=1 && column <= seatsInRow[row]){
            if (seats[row][column-1]==0){
                System.out.println("Seat is already free!");
            }
            else{
                seats[row][column-1]=0;
                ticketList[row][column-1]=null;

                System.out.println("Seat canceled successfully!");
            }
        }
        else {
            System.out.println("Enter a valid seat");
        }

    }

    public static void find_first_available(int[] seatsInRow, int[][] seats){
        for (int row = 0; row < 4; row++){
            for (int column=0; column < seatsInRow[row]; column++){
                if (seats[row][column] == 0){
                    System.out.println("First available seat: " + (char)('A' + row) + (column + 1));
                    return;

                }
            }
        }
        System.out.println("No seat found");

    }

    public static void show_seating_plan(int[] seatsInRow, int[][] seats){
        System.out.println("\nSeating Plan:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0 ){
                    System.out.print( "O ");
                }else{
                    System.out.print("X ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    public static void print_tickets_info(Ticket[][] ticketList){
        double sum=0;
        for (int i=0; i<ticketList.length; i++){
            for (int j=0; j<ticketList[i].length; j++){
                Ticket currentTicket=ticketList[i][j];

                if(currentTicket !=null){
                    char rowChar = (char) ('A' + i);
                    int column = j + 1;

                    System.out.println("Seat:" + rowChar + column + " " + "sold out" );
                    sum=sum+currentTicket.getPrice();
                }
            }
        }
        System.out.println("Total price:" + " " + sum);
    }

    public static void search_ticket(Ticket[][] ticketList) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the row (A, B, C, D): ");
        char rowChar = input.next().toUpperCase().charAt(0);

        System.out.print("Enter the column: ");
        int column = input.nextInt();

        int row = rowChar - 'A';

        if (row >= 0 && row < 4 && column >= 1 && column < ticketList[row].length) {
            Ticket currentTicket = ticketList[row][column - 1];

            if (currentTicket != null) {
                System.out.println("Ticket information for Seat " + rowChar + column + ":");
                System.out.println("Seat: " + rowChar + column);
                System.out.println("Price: " + currentTicket.getPrice());
                System.out.println("Person Information:");
                System.out.println("Name: " + currentTicket.getPerson().getName());
                System.out.println("Surname: " + currentTicket.getPerson().getSurname());
                System.out.println("Email: " + currentTicket.getPerson().getEmail());
            } else {
                System.out.println("This seat is available.");
            }

        } else {
            System.out.println("Invalid seat. Please enter a valid seat.");
        }

    }


    public static void main(String[] args) {
        int[] seatsInRow = {14, 12, 12, 14};

        int[][] seats = new int[4][];
        for (int i = 0; i < 4; i++) {
            seats[i] = new int[seatsInRow[i]];
        }

        Ticket[][] ticketList=new Ticket[4][];
        for (int i=0; i<4; i++){
            ticketList[i]=new Ticket[seatsInRow[i]];
        }



        System.out.println("Welcome to the plane management application\n");

        while (true) {
            System.out.println("************************");
            System.out.println("*     MENU OPTION      *");
            System.out.println("************************");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find the first available seat ");
            System.out.println("4) Show seating plan");
            System.out.println("5) Print tickets information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");

            Scanner input = new Scanner(System.in);
            System.out.println("Please select an option: ");

            if (input.hasNextInt()) {
                int choice = input.nextInt();

                if (choice==1){
                    buy_seat(seatsInRow, seats, ticketList);
                } else if (choice==2) {
                    cancel_seat(seatsInRow, seats, ticketList);
                } else if (choice==3) {
                    find_first_available(seatsInRow, seats);
                } else if (choice==4) {
                    show_seating_plan(seatsInRow, seats);
                } else if (choice==5) {
                    print_tickets_info(ticketList);

                } else if (choice==6) {
                    search_ticket(ticketList);

                } else if (choice==0) {
                    System.out.println("Exiting the program. Goodbye!");
                    input.close();
                    break;
                }

            }else {
                System.out.println("Invalid input. Please enter a valid menu option.");
                input.next();
            }

        }
    }

}