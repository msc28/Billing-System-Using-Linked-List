
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class working
{
    public static void main(String []args)throws IOException
    {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);

        int product_id,number;
        
        int choice_of_customer,number_of_products,customer_id;

        System.out.println("*********************************Banglore Hypermarket***********************************");
        System.out.println("                          R V Vidyanikethan, Kengeri                                         ");
        System.out.println("                          Bengaluru, Karnataka -560059   ");
        System.out.println();
        System.out.println();
        System.out.println("*****************************************************************************************");

        Items obj =  new Items();
        Cart ShoppingList = new Cart();
        Boolean flag = Boolean.TRUE, input_error_flag = Boolean.TRUE;
        obj.display();

        while(flag)
        {
            choice_of_customer = 0;
            number_of_products = 0;
            System.out.println("1. Add items to cart");
            System.out.println("2. Remove items from cart");
            System.out.println("3. Display Cart");
            System.out.println(" Type anything else to proceed to checkout");
            System.out.println();
            try {
                choice_of_customer = Integer.parseInt(in.readLine());
            } catch (Exception E){
                flag = Boolean.FALSE;
                continue;
            }
            input_error_flag = Boolean.FALSE;
            do{
                try{
                    switch (choice_of_customer) {

                        case 1:
                            System.out.println("How many products do you want to enter?");
                            number_of_products = Integer.parseInt(in.readLine());
                            for (int i = 1; i <= number_of_products; i++) {
                                System.out.println("Enter product id and required quantity ");
                                product_id = Integer.parseInt(in.readLine());
                                number = Integer.parseInt(in.readLine());
                                ShoppingList.add_to_cart(product_id, number);
                                System.out.println();
                            }
                            break;
                        case 2:
                            System.out.println("How many products do you want to delete?");
                            number_of_products = Integer.parseInt(in.readLine());


                            for (int i = 1; i <= number_of_products; i++) {
                                System.out.println("Enter product id and number of instances to be removed");
                                product_id = Integer.parseInt(in.readLine());
                                number = Integer.parseInt(in.readLine());
                                ShoppingList.remove_from_cart(product_id, number);


                            }
                            break;
                        case 3:
                            ShoppingList.displayCart();
                            break;
                        default:
                            flag = Boolean.FALSE;
                            break;

                    }
                    input_error_flag = Boolean.FALSE;
                } catch (Exception E){
                    System.out.println("Wrong Input. Try again");
                    input_error_flag = Boolean.TRUE;
                }
            } while (input_error_flag);

        }
        File bill = new File("bill.txt");
        bill.createNewFile();
        File id_file = new File("customer_id.txt");
        Scanner id_file_reader = new Scanner(id_file);
        if(id_file_reader.hasNextInt()) {
            customer_id = id_file_reader.nextInt();
            //Read the customer id.
        } else {
            customer_id = 1000;
        }
        PrintWriter id_file_writer = new PrintWriter(id_file);
        id_file_writer.format("%s\n", (customer_id + 1));
        //Update and store the next customer id
        id_file_reader.close();
        id_file_writer.close();

        System.out.println(customer_id);
        
        FileWriter billWriter = new FileWriter("bill.txt",true);
        billWriter.write("\n\n#########################################################################################\n\n\n");
        billWriter.write("*********************************Banglore Hypermarket***********************************\n");
        billWriter.write("                             R V Vidyanikethan, Kengeri                                         \n");
        billWriter.write("                            Bengaluru, Karnataka -560059  \n\n\n");
        billWriter.write("*****************************************************************************************\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd LLL yyyy HH:mm:ss");
        billWriter.write("Customer id : " + customer_id + "\n" + dtf.format(LocalDateTime.now()) +"\n");
        ShoppingList.displayCart();
        ShoppingList.printBill(billWriter);
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Mode of payment:");
        System.out.println("1.Cash");
        System.out.println("2.UPI") ;
        System.out.println("3.Card");
        System.out.println("Enter your choice:");
        billWriter.write("Mode of payment : ");
        choice_of_customer =Integer.parseInt(in.readLine());
        flag = Boolean.TRUE;
        do {

            Boolean check_number = Boolean.TRUE;

            switch (choice_of_customer) {
                case 1:
                    flag = Boolean.FALSE;
                    billWriter.write("Cash\n");
                    break;
                case 2:
                    System.out.println("Enter Mobile number");
                    Long mobilenumber = Long.parseLong(in.readLine());
                    if (Long.toString(mobilenumber).length()!= 10) {
                        check_number = Boolean.FALSE;
                    }

                    if(check_number)
                    {
                        System.out.println("OTP has been sent to your registered mobile number");
                        flag = Boolean.FALSE;
                        billWriter.write("UPI\n");
                    }
                    else {
                        System.out.println("Wrong number entered , try again!");
                    }
                    break;
                case 3:
                    System.out.println("Enter 16 digit number");
                   Long cardnumber = Long.parseLong(in.readLine());
                    if (Long.toString(cardnumber).length()!= 16) {
                        check_number = Boolean.FALSE;
                    }

                    if(check_number)
                    {
                        System.out.println("OTP has been sent to your registered mobile number");
                        flag = Boolean.FALSE;
                        billWriter.write("Card\n");
                    }
                    else
                    {
                        System.out.println("Wrong number entered , try again!");
                    }
                    break;
                default: System.out.println("Enter again");
                         break;



            }

        }
        while(flag);

        System.out.println("***************************************************************");
        System.out.println("                     Thank you for shopping with us ");
        System.out.println("***************************************************************");
        billWriter.write("*****************************************************************************************\n");
        billWriter.write("                             Thank you for shopping with us \n");
        billWriter.write("*****************************************************************************************\n");
        billWriter.close();

    }
}