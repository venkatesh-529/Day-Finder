import java.util.*;
public class Calender {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter year: ");
        int year=sc.nextInt();
        System.out.print("Enter month: ");
        int month=sc.nextInt();
        System.out.println("Enter date:");
        int date=(sc.nextInt()%7);
        int ans=((oddDays_Year(year,month)+oddDays_Month(month)+date)%7);
       if(ans==0){
        System.out.println("**it's SUNDAY**");
       }
       else if(ans==1){
        System.out.println("**it's MONDAY**");
       }
       else if(ans==2){
        System.out.println("**it's TUESDAY**");
       }
       else if(ans==3){
        System.out.println("**it's WEDNESDAY**");
       }
       else if(ans==4){
        System.out.println("**it's THURSDAY**");
       }
       else if(ans==5){
        System.out.println("**it's FRIDAY**");
       }
       else{
        System.out.println("**it's SATURDAY**");
       }
    }
    public static int oddDays_Year(int n,int c){
        int x=n/100;
        int ans1=0;
        if(n%100==0){
            x=n/100;
            if(x%4==0&&c>2){
                System.out.println("Leap year!");
                ans1=-1;
            }
            else{
                //System.out.println("Not leap year");
                ans1=-2;
            }
        }
        else{
                //System.out.println("Not a leap year");
                int b=0;
                if(n%4==0&&c>2){
                    b=1;
                    System.out.println("leap year");
                }
                int rem1=(((n-1)%400)%100);
                int rem3=rem1/4;
                int odd1=(2*rem3+(rem1-rem3))%7;
                int rem2=(((n-1)%400)-rem1)/100;
                int odd2=0;

                if(rem2==1){
                    odd2=5;
                }
                else if(rem2==2){
                    odd2=3;
                }
                else if(rem2==3){
                    odd2=1;
                }
                else{
                    odd2=0;
                }
                ans1=(b+odd1+odd2)%7;
            
        }
        return ans1;
    }
    public static int oddDays_Month(int x){
        if(x==1||x==10){
            return 0;
        }
        else if(x==2||x==3||x==11){
            return 3;
        }
        else if(x==4||x==7){
            return 6;
        }
        else if(x==5){
            return 1;
        }
        else if(x==6){
            return 4;
        }
        else if(x==8){
            return 2;
        }
        else {
            return 5;
        }
    }
    public static boolean isLeapYear(int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }
}
