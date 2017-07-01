/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numbertheoryapp;

import java.util.Scanner;

/**
 *
 * @author Gian
 */
public class NumberTheoryApp {

    public NumberTheoryApp() {
        
    }
    
    public int remMod(int a, int b) {
        return a%b;
    }
    
    public boolean isPrime(int x) {
        double root = Math.pow(x,0.5);
        root = Math.floor(root);
        int test = (int) root;
        
        for (int i = 2; i <= test; i++) {
            if (x%i == 0) return false;
        }
        return true;
    }
    
    public String primeFactorize(int n) {
        String factorization = "";
        int prevPow = 0;
        int currPrime = 2;
        
        while (n != 1) {
            while (isPrime(currPrime) && n%currPrime == 0) {
                prevPow++;
                n = n/currPrime;
            }
            if (prevPow == 1) factorization = factorization + currPrime + "x";
            if (prevPow > 1) factorization = factorization + currPrime + "^" + prevPow + "x";
            prevPow = 0;
            currPrime++;
        }
        
        factorization = factorization.substring(0,factorization.length()-1);
        return factorization;
    }
    
    public int hcf(int a, int b) {
        String hcfString = "";
        boolean checker = false;
        String primeFacA = primeFactorize(a);
        String primeFacB = primeFactorize(b);
        
        String[] factorsA = primeFacA.split("x");
        String[] factorsB = primeFacB.split("x");
        
        for (int i = 0; i < factorsA.length; i++) {
            if (factorsA[i].contains("^")) {
                String[] powSplit = factorsA[i].split("\\^");
                int baseA = Integer.parseInt(powSplit[0]);
                int expA = Integer.parseInt(powSplit[1]);
                for (int j = 0; j < factorsB.length; j++) {
                    if (factorsB[j].contains("^")) {
                        String[] powerSplit = factorsB[j].split("\\^");
                        int baseB = Integer.parseInt(powerSplit[0]);
                        int expB = Integer.parseInt(powerSplit[1]);
                        if (baseA == baseB) {
                            hcfString = hcfString + baseA + "^" + min(expA,expB) + "x";
                            checker = true;
                        }
                    } else {
                        int num2 = Integer.parseInt(factorsB[j]);
                        if (baseA == num2) {
                            hcfString = hcfString + num2 + "x";
                            checker = true;
                        }
                    }
                }
            } else {
                int num1 = Integer.parseInt(factorsA[i]);
                for (int j = 0; j < factorsB.length; j++) {
                    if (factorsB[j].contains("^")) {
                        String[] powerSplit = factorsB[j].split("\\^");
                        int base = Integer.parseInt(powerSplit[0]);
                        int exp = Integer.parseInt(powerSplit[1]);
                        if (num1 == base) {
                            hcfString = hcfString + base + "x";
                            checker = true;
                        }
                    } else {
                        int num2 = Integer.parseInt(factorsB[j]);
                        if (num1 == num2) {
                            hcfString = hcfString + num2 + "x";
                            checker = true;
                        }
                    }
                }
            }
        }
        
        if (!checker) return 1;
        hcfString = hcfString.substring(0,hcfString.length()-1);
        return multiplyPrimes(hcfString);
    }
    
    public int lcm(int a, int b) {
        return a*b /hcf(a,b) ;
    }
    
    public boolean isCoPrime(int a, int b) {
        if (hcf(a,b) == 1) return true;
        else return false;
    }
    
    public int min(int x, int y) {
        if (x > y) return y;
        else return x;
    }
    
    public int max(int x, int y) {
        if (x > y) return x;
        else return y;
    }
    
    public int multiplyPrimes(String factorization) {
        int product = 1;
        
        while (factorization.length() != 0) {
            int firstX = factorization.indexOf("x");
            if (firstX == -1) {
                if (factorization.contains("^")) {
                    String[] nums = factorization.split("\\^");
                    int base = Integer.parseInt(nums[0]);
                    int exp = Integer.parseInt(nums[1]);
                    double power = Math.pow(base,exp);
                    int pow = (int) power;
                    product = product * pow;
                    break;
                }
                else {
                    product = product * Integer.parseInt(factorization);
                    break;
                }
            }
            String first = factorization.substring(0,firstX);
        
            if (first.contains("^")) {
                String[] nums = first.split("\\^");
                int base = Integer.parseInt(nums[0]);
                int exp = Integer.parseInt(nums[1]);
                double power = Math.pow(base,exp);
                int pow = (int) power;
                product = product*pow;
            } else product = product* Integer.parseInt(first);
        
            factorization = factorization.substring(firstX+1,factorization.length());
        }
        
        return product;
        
    }
    
    public void menu() {
        System.out.println("SIMPLE NUMBER THEORY APP: What do you want to do?");
        System.out.println("[0] Exit the program.");
        System.out.println("[1] Find the remainder when dividing two numbers.");
        System.out.println("[2] Determine if my number is prime.");
        System.out.println("[3] Determine if two of my numbers are co-prime.");
        System.out.println("[4] Determine the prime factorization of my number.");
        System.out.println("[5] Find the number of my prime factorization.");
        System.out.println("[6] Identify the Highest Common Factor of my two numbers.");
        System.out.println("[7] Identify the Least Common Multiple of my two numbers.");
        System.out.println("[8] Find the modular remainder of my two numbers (Coming Soon!)");
        System.out.println("[9] Use Goldbach's Conjecture to determine all pairs of primes whose sum is my number (Coming Soon!)");
        System.out.print("Enter a number from 0-9: ");
    }
    public static void main(String[] args) {
        String choice = "";
        do {
            NumberTheoryApp nta = new NumberTheoryApp();
            Scanner in = new Scanner(System.in);
            nta.menu();
            choice = in.nextLine();
            
        
            if (choice.equals("0")) return;
            else if (choice.equals("1")) {
                System.out.print("Enter two non-negative integers with a space: ");
                String l1 = in.nextLine();
                String[] l1Nums = l1.split("\\s+");
                String l1first = l1Nums[0];
                String l1second = l1Nums[1];
                int rem = nta.remMod(Integer.parseInt(l1first),Integer.parseInt(l1second));
                System.out.println("The remainder is " + rem);
            } else if (choice.equals("2")) {
                System.out.print("Enter a non-negative integer greater than 1: ");
                String l2 = in.nextLine();
                int l2num = Integer.parseInt(l2);
                boolean prime = nta.isPrime(l2num);
                if (prime) System.out.println("Your number is prime.");
                else System.out.println("Your number is composite.");
            } else if (choice.equals("3")) {
                System.out.print("Enter two non-negative integers with a space: ");
                String l3 = in.nextLine();
                String[] l3Nums = l3.split("\\s+");
                String l3first = l3Nums[0];
                String l3second = l3Nums[1];
                boolean cp = nta.isCoPrime(Integer.parseInt(l3first),Integer.parseInt(l3second));
                if (cp) System.out.println("The two numbers are co-prime");
                else System.out.println("The two numbers are not co-prime");
            } else if (choice.equals("4")) {
                System.out.print("Enter a non-negative integer greater than 1: ");
                String l4 = in.nextLine();
                int l4num = Integer.parseInt(l4);
                String fac = nta.primeFactorize(l4num);
                System.out.println("The prime factorization is " + fac);
            } else if (choice.equals("5")) {
                System.out.print("Enter a valid prime factorization: ");
                String l5 = in.nextLine();
                int l5num = nta.multiplyPrimes(l5);
                System.out.println("The number you are looking for is " + l5num);
            } else if (choice.equals("6")) {
                System.out.print("Enter two non-negative integers with a space: ");
                String l6 = in.nextLine();
                String[] l6Nums = l6.split("\\s+");
                String l6first = l6Nums[0];
                String l6second = l6Nums[1];
                int hcf = nta.hcf(Integer.parseInt(l6first),Integer.parseInt(l6second));
                System.out.println("The highest common factor is " + hcf);
            } else if (choice.equals("7")) {
                System.out.print("Enter two non-negative integers with a space: ");
                String l7 = in.nextLine();
                String[] l7Nums = l7.split("\\s+");
                String l7first = l7Nums[0];
                String l7second = l7Nums[1];
                int lcm = nta.lcm(Integer.parseInt(l7first),Integer.parseInt(l7second));
                System.out.println("The least common multiple is " + lcm);
            } else if (choice.equals("8") || choice.equals("9")) {
                System.out.println("Feature is still unavaiable.");
            } else {
                System.out.println("INVALID option. TRY AGAIN!");
            }
            
            System.out.println("Press Enter to continue");
            String u = in.nextLine();
        } while(!(choice.equals("0")));
    }
    
}
