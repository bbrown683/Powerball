import java.util.Scanner;

/**
 * Benjamin Brown and Benjamin Estes
 */
public class Powerball
{
    public static void main(String args[])
    {  
        Scanner input = new Scanner(System.in);
         
        int k = 0;
        int n = 0;
        int m = 0;
        
        int count = 0;
        
        do
        {
            System.out.print("Numbers (non-Powerball): ");
            k = input.nextInt();
            
            if (k > 0)
            {
                count++;
            }
        } while (count < 1);
        
        // Reset count.
        count = 0;
        
        do
        {
            System.out.print("Maximum (non-Powerball): ");
            n = input.nextInt();
            
            if (n > 0 && n >= k)
            {
                count++;
            }
            else
            {
                System.out.println("The maximimum cannot be less than the total number.");
            }
        } while (count < 1);
        
        // Reset count.
        count = 0;
        
        do
        {
            System.out.print("Maximum (For Powerball): ");
            m = input.nextInt();
            
            if (m > 0)
            {
                count++;
            }
        } while (count < 1);
        
        System.out.println();
        
        double chance = jackpotChance(k, n, m);
        System.out.println("You have a " + (chance * 100) + "% chance of winning.");
        
        System.out.println();
        
        int[] playerDraw = enterNumbers(k, n);

        // Get Powerball number from user and reset count once again.
        int playerPowerball = 0;
        count = 0;     

        do
        {            
            System.out.print("Enter Powerball Number: ");
            playerPowerball = input.nextInt();
            
            if (playerPowerball > m || playerPowerball < 1)
            {
                System.out.println("Powerball number is not within threshold.");
            }
            else
            {
                count++;
            }
        } while(count < 1);
        
        int[] computerDraw = drawNumbers(k,n);
        
        System.out.println();
        
        printArray(playerDraw, "Player");
        printArray(computerDraw, "Computer");
        
        int computerPowerball = (int)(1 + Math.random() * m);
        
        System.out.println("Powerball Number: " + computerPowerball);
        
        System.out.println();
        
        if (containSameElements(playerDraw, computerDraw))
        {
            if (playerPowerball == computerPowerball)
            {    
                System.out.println("You Win!");
            }
            else
            {
                System.out.println("Sorry, you lose.");
            }
        }
        else
        {
            System.out.println("Sorry, you lose.");
        }
    }
    
    // Prints the array in a pretty fashion.
    public static void printArray(int[] a, String header)
    {
        System.out.print(header + ": [");
        
        for (int i = 0; i < a.length; i++)
        {
            // first spot with length of 1
            if (a.length == 1)
            {
                System.out.print(a[i]);
            }
            // first spot with a length > 1
            else if(i == 0)
            {
                System.out.print(a[i] + ",");
            }
            // first spot + 1 to length - 1
            else if(i != a.length - 1)
            {
                System.out.print(" " + a[i] + ",");
            }
            // last spot
            else
            {
                System.out.print(" " + a[i]);
            }
        }
        
        System.out.println("]");
    }

    // Searches an array for "n" and if it does not exist more than once, return true, otherwise return false.
    public static boolean isUnique(int[] a, int n)
    {
        int count = 0;
        
        for (int i = 0; i < a.length; i++)
        {
            if(a[i] == n)
            {
                count++;
            }
        }
        
        if (count > 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    // Copies the elements of the arrays into new ones so we dont override the previous values.
    public static int[] copyArray(int[] array)
    {
        int[] tempArray = new int[array.length];
        
        // Copy elements from array into tempArray
        for (int i = 0; i < array.length; i++)
        {
            tempArray[i] = array[i];
        }
        
        return tempArray;
    }
    
    // Sorts the array in numerical order.
    // It will continue to swap array[i] until it is the order of smallest to largest.
    public static int[] sortArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int k = 0; k < array.length; k++)
            {
                if (array[i] < array[k])
                {
                    // Set array[i] to a temp variable to swap the value
                    int temp = array[i];
                    
                    // Then array[i] will be replaced with array[k]
                    array[i] = array[k];
                    
                    // Finally, replace array[k] with temp
                    array[k] = temp;
                }
            }
        }
        
        return array;
    }
    
    public static double jackpotChance(int k, int n, int m)
    {
        // Set these to 1 to prevent divide by zero
        int topHalf      = 1;
        int bottomHalf   = 1;
        
        double total     = 0;
        
        /*
         * Implements a lottery algorithm
         */
        
        // Does factorial from (n - k + 1) to n
        for (int i = n - k + 1; i <= n; i++)
        {
            topHalf = topHalf * i;
        }
        
        // Does factorial from 1 to k
        for (int i = 1; i <= k; i++)
        {
            bottomHalf = bottomHalf * i; 
        }
        
        total = (double)(topHalf / bottomHalf) * m;
        
        return 1 / total;
    }
    
    public static int[] enterNumbers(int k, int n)
    {
        Scanner input = new Scanner(System.in);
        int[] playerDraw = new int[k];
        int count = 0;     
        
        do
        {            
            System.out.print("Enter number: ");
            playerDraw[count] = input.nextInt();
            
            if (playerDraw[count] > n || playerDraw[count] < 1)
            {
                System.out.println("Number is not within threshold.");
            }
            else if (isUnique(playerDraw, playerDraw[count]))
            {
                count++;
            }
            else
            {
                System.out.println("Number is already present.");
            }
            
        } while(count < k);
        
        return playerDraw;
    }
    
    public static int[] drawNumbers(int k, int n)
    {
        Scanner input = new Scanner(System.in);
        int[] computerDraw = new int[k];
        int count = 0;     
        
        do
        {            
            computerDraw[count] = (int)(1 + Math.random() * n);
            
            if(isUnique(computerDraw, computerDraw[count]))
            {
                count++;
            }
            
        } while (count < k);
        
        return computerDraw;
    }
    
    public static boolean containSameElements(int[] a, int[] b)
    {        
        int[] aCopy = copyArray(a);
        int[] bCopy = copyArray(b);
        
        /*
         * Sort the array's if they have the same length to test if they have identical elements
         */
        if (a.length == b.length)
        {
            int count = 0;
            
            aCopy = sortArray(aCopy);
            bCopy = sortArray(bCopy);
            
            for (int i = 0; i < aCopy.length && i < bCopy.length; i++)
            {
                if (aCopy[i] == bCopy[i])
                {
                    count++;
                }
            }
            
            // The count of identical elements should equal the length of the arrays. If not, they are not the same.
            if (count == aCopy.length && count == bCopy.length) 
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}
