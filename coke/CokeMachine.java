 /*
Name : Shah Arifur Rahman Rhyme
UTA ID: 1001909995
File Name: Code3_1001909995.java
*/
package coke;
public class CokeMachine 
{
    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
        
    private String machineName; 
    private int changeLevel; 
    private int maxChangeCapacity = 5000; 
    private int inventoryLevel;
    private int maxInventoryCapacity = 100; 
    private int CokePrice; 
    private int changeDispensed; 
    private static int numberOfCokesSold = 0;
    public CokeMachine(String name, int cost, int change, int inventory)
    {
        machineName = name; 
        CokePrice = cost; 
        changeLevel = change; 
        inventoryLevel = inventory; //inventoryLevel =100
    }
    public CokeMachine()
    {
        machineName = "New Machine"; 
        CokePrice = 50; 
        changeLevel = 500; 
        inventoryLevel = 100; 
    }

    public void setMachineName(String newMachineName)
    {
        machineName = newMachineName; 
    }
    public void setCokePrice(int newCokePrice)
    {
        CokePrice = newCokePrice; 
    }

    public String getMachineName()
    {
        return machineName; 
    }
    public int getChangeLevel()
    {
        return changeLevel; 
    }
    public int getMaxChangeCapacity()
    {
        return maxChangeCapacity; 
    }
    public int getInventoryLevel()
    {
        return inventoryLevel; 
    }
    public int getMaxInventoryLevel()
    {
        return maxInventoryCapacity; 
    }
    public int getCokePrice()
    {
        return CokePrice; 
    }
    public int getChangeDispensed()
    {
        return changeDispensed; 
    }
    public int getNumberOfCokesSold()
    {
        return numberOfCokesSold; 
    }
    public boolean incrementInventoryLevel(int amountToAdd)
    {
        boolean boolValue = false; 
        if ((amountToAdd + inventoryLevel) <= maxInventoryCapacity)
        {
            inventoryLevel += amountToAdd; 
            boolValue = true; 
        }
        return boolValue; 
        
    }
    public boolean incrementChangeLevel(int amountToAdd)
    {
        boolean boolReturn = false; 
        if ((amountToAdd + changeLevel) <= maxChangeCapacity)
        {
            changeLevel += amountToAdd; 
            boolReturn = true; 
        }
        return boolReturn; 

    }
    public ACTION buyACoke(int payment)
    { 
        ACTION act2 = ACTION.EXACTPAYMENT;
        if (CokePrice > payment)
        {
            act2 = ACTION.INSUFFICIENTFUNDS; 
        }
        if (CokePrice == payment)
        {
            inventoryLevel--;
            changeLevel = changeLevel + CokePrice;
            numberOfCokesSold++;
        }    
        if ((payment > CokePrice) && (changeLevel > (payment - CokePrice)))
        {
            inventoryLevel--;
            changeDispensed = payment - CokePrice;
            changeLevel = changeLevel +  CokePrice;
            numberOfCokesSold++;
            act2 = ACTION.DISPENSECHANGE; 
        }
        if ((payment - CokePrice) > changeLevel)
        {
            act2 = ACTION.INSUFFICIENTCHANGE; 
        }
        return act2;  
    }
    
    public String toString()
    {
        return String.format("Machine Name: \t\t\t\t %s\nCurrent Inventory Level: \t\t %d\nCurrent Change Level: \t\t\t %d\nMax Inventory Capacity: \t\t %d\nMax Change Capacity: \t\t\t %d\nLast Change Dispensed: \t\t\t %d\nCoke Price:\t\t\t\t% d\nNumber Of Cokes Sold: \t\t\t %d\n", 
        machineName, inventoryLevel,  changeLevel, maxInventoryCapacity, maxChangeCapacity, changeDispensed, CokePrice,numberOfCokesSold);
    }


}

