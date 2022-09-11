import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class Cart
{

    public int size;
    public Node head;

    Cart()
    {
        head = null;
        size = 0;
    }
    Items object = new Items();
    public void add_to_cart(int pid,int n)
    {
        if(pid<101||pid>130)
        {
            System.out.println("Wrong productid");
            return;
        }
        int price =  Integer.parseInt(object.product[pid-101][2]);
        Node newNode = new Node(pid,price,n);

        if(head==null)
        {
            head = newNode;
            size++;
            return;
        }
        Node cur = head;
        while(cur.next!=null && cur.productid!=pid)
            cur = cur.next;
        if(cur.productid == pid){
            cur.number_of_items += n;
        } else {
            cur.next = newNode;
            size++;
        }



    }

    public void remove_from_cart(int pid,int number_of_instance)
    {


        if(head == null)
        {
            System.out.println("Cart is empty");
            return;
        }
        Node cur = head,prev = null,del = null;
        //del is used to store the deleted element to display it
        // If product is at the front of the list
        if(head.productid == pid) {
            if (head.number_of_items - number_of_instance <= 0) {
                del = head;
                head = head.next;
                return;
            } else {
                head.number_of_items = head.number_of_items - number_of_instance;
                return;
            }
        }

            else {
            // If product is present anywhere other than the front of list
            while(cur!=null)
            {
                if(cur.productid == pid ) {
                    if ((cur.number_of_items - number_of_instance) <= 0)
                    {

                        prev.next = cur.next; // Delete node from list
                        del = cur; // Save the deleted node for display
                        cur = null;
                        return;
                    }
                    else
                        {
                        cur.number_of_items = cur.number_of_items - number_of_instance;
                        return;
                       }
                }
                prev = cur;
                cur = cur.next;
            }
        }
        if(del == null){
            System.out.println("Product is not present in your cart");
        }
        }


    public void displayCart()
    {
        System.out.println("*********************************************************************");
        if(head == null)
        {
              System.out.println("Cart is empty");
              return;
        }
        System.out.println("Your cart contains : ");
        Node cur = head;
        System.out.printf("%s  %-15s %-10s  %-5s %4s\n","pid","Product name","Cost","Qty","Total");
        while(cur!=null)
        {
            System.out.printf("%s  %-15s %-10s x %-5d %.2f\n",cur.productid, object.product[cur.productid-101][1], cur.cost,cur.number_of_items,cur.price);
            //System.out.println();
            cur = cur.next;


        }
        System.out.println();
        System.out.println();
        System.out.println("*********************************************************************");
        System.out.println("The final amount is : Rs." + GetBill(head));
    }

     public float GetBill(Node head)
     {
         float sum =0.0f;
         if(head==null)
             return 0.0f;
         else
         {
             Node cur = head;
             while(cur!=null)
             {
                 sum = sum+cur.price;
                 cur=cur.next;
             }
             return sum;
         }

     }
     public void printBill(FileWriter billWriter) throws IOException {
         billWriter.write("_________________________________________________________________________________________\n");
         if(head == null)
         {
             billWriter.write("Cart is empty\n");
             return;
         }
         billWriter.write("Your cart contains : \n");
         Node cur = head;
         billWriter.write(String.format("%s  %-15s %-10s  %-5s %4s\n","pid","Product name","Cost","Qty","Total"));
         while(cur!=null)
         {
             billWriter.write(String.format("%s  %-15s %-10s x %-5d %.2f\n",cur.productid, object.product[cur.productid-101][1], cur.cost,cur.number_of_items,cur.price));
             cur = cur.next;


         }
         billWriter.write(String.format("\n_________________________________________________________________________________________\n"));
         billWriter.write(String.format("The final amount is : Rs." + GetBill(head) + "\n"));
         billWriter.flush();
     }
   }

class Node
{
    int number_of_items;
    float price;
    int productid;
    float cost;
    Node next;

    Node(int productid,float cost,int number)
    {
        this.productid = productid;
        this.cost = cost;
        next = null;
        number_of_items =  number;
        price = number*cost;
    }


}