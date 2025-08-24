import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DayFinderApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Day Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel labelDay = new JLabel("Enter Day (1-31):");
        JTextField tfDay = new JTextField();

        JLabel labelMonth = new JLabel("Enter Month (1-12):");
        JTextField tfMonth = new JTextField();

        JLabel labelYear = new JLabel("Enter Year:");
        JTextField tfYear = new JTextField();

        JButton checkButton = new JButton("Find Day");
        JLabel result = new JLabel("");

        frame.add(labelDay);
        frame.add(tfDay);
        frame.add(labelMonth);
        frame.add(tfMonth);
        frame.add(labelYear);
        frame.add(tfYear);
        frame.add(new JLabel());  // Empty cell
        frame.add(checkButton);
        frame.add(new JLabel("Result:"));
        frame.add(result);

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int d = Integer.parseInt(tfDay.getText().trim());
                    int m = Integer.parseInt(tfMonth.getText().trim());
                    int y = Integer.parseInt(tfYear.getText().trim());

                    if (!isValidDate(d, m, y)) {
                        JOptionPane.showMessageDialog(frame, "Invalid date entered!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String day = getDay(d, m, y);
                    result.setText(day);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numeric values!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static boolean isValidDate(int d, int m, int y) {
        if (y < 1 || m < 1 || m > 12 || d < 1)
            return false;

        int[] daysInMonth = {31, (isLeapYear(y) ? 29 : 28), 31, 30, 31, 30,
                             31, 31, 30, 31, 30, 31};

        return d <= daysInMonth[m - 1];
    }

    public static boolean isLeapYear(int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    public static String getDay(int date, int month, int year) {
        int ans = (oddDays_Year(year, month) + oddDays_Month(month) + (date % 7)) % 7;

        switch (ans) {
            case 0:
                return "SUNDAY";
            case 1:
                return "MONDAY";
            case 2:
                return "TUESDAY";
            case 3:
                return "WEDNESDAY";
            case 4:
                return "THURSDAY";
            case 5:
                return "FRIDAY";
            case 6:
                return "SATURDAY";
            default:
                return "Invalid";
        }
    }

    public static int oddDays_Year(int n, int c) {
        int x = n / 100;
        int ans1 = 0;

        if (n % 100 == 0) {
            x = n / 100;
            if (x % 4 == 0 && c > 2) {
                ans1 = -1;
            } else {
                ans1 = -2;
            }
        } else {
            int b = 0;
            if (n % 4 == 0 && c > 2) {
                b = 1;
            }
            int rem1 = (((n - 1) % 400) % 100);
            int rem3 = rem1 / 4;
            int odd1 = (2 * rem3 + (rem1 - rem3)) % 7;
            int rem2 = (((n - 1) % 400) - rem1) / 100;
            int odd2 = 0;

            if (rem2 == 1) odd2 = 5;
            else if (rem2 == 2) odd2 = 3;
            else if (rem2 == 3) odd2 = 1;
            else odd2 = 0;

            ans1 = (b + odd1 + odd2) % 7;
        }
        return ans1;
    }

    public static int oddDays_Month(int x) {
        if (x == 1 || x == 10) return 0;
        else if (x == 2 || x == 3 || x == 11) return 3;
        else if (x == 4 || x == 7) return 6;
        else if (x == 5) return 1;
        else if (x == 6) return 4;
        else if (x == 8) return 2;
        else return 5;
    }
}
