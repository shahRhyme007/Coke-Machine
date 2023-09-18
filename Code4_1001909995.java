 /*
 * Name: Shah Arifur Rahman Rhyme
 * File Name: Code2_1001909995.java
 * UTA ID: 1001909995
 */

 
 
import java.util.ArrayList; 
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Random; 
import java.io.PrintWriter; 

  
public class Code4_1001909995
{
    enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }

    public static String displayMoney(int money )
    {
        int intDollar = money/100; 
        int intCent = money % 100; 

        String strDollar = String.valueOf(intDollar); 
        String strCent = String.valueOf(intCent); 

        String Final_dollar = "$" + strDollar +"." +(strCent.length() == 1 ? "0":"")+strCent; 

        return Final_dollar;     
    }

    public static int PencilMenu(int choice_val) throws FileNotFoundException
    {
        Scanner frog = new Scanner(System.in); 
        choice_val = 0;

        System.out.printf("\nPlease choose the following options\n\n\n");
        System.out.println("0. No pencils for me today"); 
        System.out.println("1. Purchase pencils"); 
        System.out.println("2. Check inventory level");
        System.out.println("3. Check change level");
            
        System.out.print("\n\nChoice : "); 
        boolean userInput; 
        do
        {
            userInput = true; 
            try
            {
                choice_val =  frog.nextInt();
            }
            catch(Exception e)
            {
                choice_val = -1;
                System.out.printf("\nInvalid Menu Option\n"); 
                System.out.printf("\nPlease choose the following options\n\n\n");
                System.out.println("0. No pencils for me today"); 
                System.out.println("1. Purchase pencils"); 
                System.out.println("2. Check inventory level");
                System.out.println("3. Check change level");
                    
                System.out.print("\n\nChoice : "); 
                frog.nextLine();  
                userInput = false; 
            }   
        }
        while (userInput == false);
           
     
        while(choice_val > 3 || choice_val < 0 )
        {
            System.out.printf("please enter choice from 0 - 3\n");
            System.out.print("Choice : "); 
            choice_val =  frog.nextInt();  
        }
        return choice_val;         
    }
     
    
    public static ACTION buyPencils(int totalCost, int userAmount, int noOfpencil, int arrayLevel[])
    {
        if (userAmount == totalCost)
        {
            arrayLevel[0] = arrayLevel[0] - noOfpencil;
            arrayLevel[1] = arrayLevel[1] + totalCost;
            return ACTION.EXACTPAYMENT; 
        }
        else if (userAmount > totalCost && arrayLevel[1] > (userAmount-totalCost) )
        {
            arrayLevel[0] = arrayLevel[0] - noOfpencil;
            arrayLevel[1] = arrayLevel[1] + userAmount - (userAmount-totalCost);
            return ACTION.DISPENSECHANGE; 
        }
        else if ((userAmount-totalCost) > arrayLevel[1])
        {
            return ACTION.INSUFFICIENTCHANGE; 
        }
        else
        {
            return ACTION.INSUFFICIENTFUNDS; 
        } 
    }
    public static void ReadFile (int arrayLevel[] , String FILENAME, ArrayList<String> PencilColor)
    {
        File FH = new File (FILENAME);
        Scanner inFileRead = null; 
        
        try
        {
            inFileRead = new Scanner(FH);
        }
        catch(Exception e)
        {
            System.out.printf("File %s did not open \n", FILENAME); 
            System.exit(0);
        }
         
        String strFirstLine = inFileRead.nextLine(); //for reading first line from the file
        //System.out.print(intFirstLine);
        String strSecondLine = inFileRead.nextLine(); //for reading second line from the file
        //System.out.print(strSecondLine);
        
        String storeInt[] = strFirstLine.split("[ ]"); 
        
        arrayLevel[0] = Integer.parseInt(storeInt[1]); //spliting from the file for changeLevel 
        // System.out.print( arrayLevel[0]); 
        arrayLevel[1] = Integer.parseInt(storeInt[0]); // splitting from the file for inventory level

        //for color 
        String colorArray[] = strSecondLine.split("[ ]"); 
        
        for (String color : colorArray)
        {
            PencilColor.add(color); 
            //System.out.println(PencilColor); 
        }
         inFileRead.close(); 
    }
    
    public static String randomColor (ArrayList<String> PencilColor )
    {
        Random rand = new Random(); 
        int colorNum = rand.nextInt(PencilColor.size()); 
        
        return PencilColor.get(colorNum); 
    }
    
    
    
    public static void main(String[] args) throws FileNotFoundException//my main function starting from here
    {
        System.out.printf("\nWelcome to my Pencil  Machine\n\n"); 
        Scanner frog = new Scanner(System.in);
        
        int menu_choice = 0, pencilCost = 0, total_cost = 0 ,userPayment = 0; 
        String chngeLvl ="";
        final int PENCIL_PRICE = 60;
        int inventoryLevel=100 , changeLevel = 500 ; 
        int purchaseQuantity = 0; 
        ArrayList<String> PencilColor = new ArrayList<>(); 
        
        String FILENAME = args[0].substring(args[0].indexOf('=')+1); // getting the file name from command line
        //System.out.print(FILENAME); 
        String strPencilCost = args[1].substring(args[1].indexOf('=')+1); // getting the pencil cost from command line
        int PencilCOST = Integer.parseInt(strPencilCost); 
        //System.out.print(PencilCOST); 
         
        int arrayLevel[] = {inventoryLevel, changeLevel};
        ReadFile (arrayLevel , FILENAME, PencilColor); 
        // ReadFile (arrayLevel , FILENAME, PencilColor);  
        inventoryLevel= arrayLevel[0]  ; // getting the value of inventory level from the file(at index 1)
        // System.out.print(inventoryLevel);
        changeLevel = arrayLevel[1]  ;  // getting the value fo change level from the file(0)
        
        int menu = PencilMenu(menu_choice); 
        
        while(menu != 0)
        {
            switch (menu)
            {
                case 1: 
                {
                    if (arrayLevel[0] == 0)
                    {
                        System.out.println("\n\nThe pencil dispencer is out of inventory");
                        menu = PencilMenu(menu_choice);
                    }
                    else
                    {
                        String display = displayMoney(PENCIL_PRICE);
                        System.out.printf("\n\nA pencil costs %s", display);  
                        System.out.printf("\nHow many pencils would you like to purchase? "); 

                        purchaseQuantity = frog.nextInt(); 
                       
                        while(purchaseQuantity <= 0 ||  inventoryLevel <=0  || purchaseQuantity > arrayLevel[0] )
                        {
                            System.out.printf("Cannot sell that quantity of pencils.  "); 
                            System.out.printf("please reenter quantity  "); 
                            purchaseQuantity = frog.nextInt(); 
                        }   
                        total_cost = PENCIL_PRICE * purchaseQuantity; 
                        String totalPrice = displayMoney(total_cost); 
                        System.out.printf("Your total is %s\n\n", totalPrice);  
                        
                        System.out.printf("Enter your Payment (in cents) "); 
                        userPayment = frog.nextInt();  // given in cents
                      
                        ACTION buy = buyPencils(total_cost, userPayment, purchaseQuantity, arrayLevel); 
                        
                        changeLevel = arrayLevel[1]; 
                        inventoryLevel = arrayLevel[0];

                        switch(buy)
                        {
                            case DISPENSECHANGE: 
                            {
                                int change = userPayment - total_cost; 
                                String strChange = displayMoney(change); 
                                System.out.printf("Here's your %s pencils and your change of %s\n\n", randomColor(PencilColor), strChange);
                                break; 
                            }
                            case INSUFFICIENTCHANGE: 
                            {
                                System.out.printf("This Pencil Dispenser does not have enough change and cannot accept\nyour payment.\n\n");
                                break; 
                            }
                            case INSUFFICIENTFUNDS: 
                            {
                                System.out.printf("You have not given enough money. Hence take your money back\n\n");
                                break; 
                            }
                            case EXACTPAYMENT: 
                            {
                                System.out.printf("Here's your %s pencils. Thank you for exact payment!!...\n\n", randomColor(PencilColor));
                                break; 

                            }
                            default:
                            {
                                System.out.printf("something went wrong\n\n"); 
                                break; 
                            }
                        }
                        menu = PencilMenu(menu_choice);  
                        PrintWriter outFile = new PrintWriter(FILENAME); 
                        outFile.printf("%d %d \n", changeLevel, inventoryLevel); 
                        
                        for (String outColor: PencilColor)
                        {
                            outFile.printf("%s ", outColor); 
                        }
                        outFile.close(); 
                    }
                    break; 
                }
                case 2: 
                {
                    System.out.printf("The current inventory level is %d \n",inventoryLevel ); 
                    menu = PencilMenu(menu_choice);
                    break;
                }
                case 3: 
                {
                    chngeLvl = displayMoney(changeLevel);
                    System.out.printf("The current change level is %s \n", chngeLvl); 
                    menu = PencilMenu(menu_choice);
                    break;
                }
                default: 
                {
                    System.out.printf("Invalid menu option.\n"); 
                    break; 
                }
            }
        } 
    }
}








