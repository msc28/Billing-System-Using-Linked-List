import java.io.IOException;

public class Items
{
    // to store all items in the market with id and price
    String[][] product = 
    	{
    		{"101", "Maggi", "12"},
            {"102", "Kurkure", "10"},
            {"103", "Oreo", "35"},
            {"104", "Nutella", "80"},
            {"105", "Surf Excel", "45"},
            {"106", "Bulb", "120"},
            {"107", "Bag", "750"},
            {"108", "Shirt", "499"},
            {"109","Toothpaste","50"},
            {"110","Water Bottle","200"},
            {"111","Shoes","549"},
            {"112","Notebook","30"},
            {"113","Toothbrush","20"},
            {"114","Oil","65"},
            {"115","Masks","40"},
            {"116","Soap","32"},
            {"117","Brooms","149"},
            {"118","Blankets","249"},
            {"119","Vicks Vaporub","32"},
            {"120","Pen","5"},
            {"121","Chocolates","50"},
            {"122","Buckets","150"},
            {"123","Locks","45"},
            {"124","Rice","86"},
            {"125","Wheat","92"},
            {"126","Batteries","13"},
            {"127","Belts","120"},
            {"128","Pen Case","80"},
            {"129","Towels","120"},
            {"130","Powder","60"},
    	};

    public void display()throws IOException
    {
        int i ;
        for(i=0;i<30;i++)
        {
            System.out.printf("%s  %-15s %s \n",product[i][0],product[i][1],product[i][2]);
        }
    }
    }
