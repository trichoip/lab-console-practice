package tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
* This is comment, do not delete 2021.11.30
 * and open the template in the editor.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author minh tri
 */
public class Utils {

    public static final String CHECK_ID_VALID = "SE\\d{3}";
    public static final String CHECK_PHONE_VALID = "[\\d]{10,12}";
    public static final String CHECK_EMAIL_VALID = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[\\a-zA-Z]{2,6}";
    //      sua date thi sua ca hai cai duoi || vd: "-" to "/"
    public static final String DATE_FORMAT_OF_SIMPLEDATEFORMAT = "dd-MM-yyyy";
    //public static final String DATE_FORMAT_OF_SIMPLEDATEFORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final String DATE_FORMAT_VALID = "\\d{2}-\\d{2}-\\d{4}";

    public static final String CHECK_GENDER_VALID = "male|famale";

    private static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String input, String error) {
        int n;
        while (true) {
            try {
                System.out.print(input);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(error);
            }
        }
    }

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String updateString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int updateInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static double getDouble(String welcome, double min, double max) {
        boolean check = true;
        double number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static double updateDouble(String welcome, double min, double max, double oldData) {
        boolean check = true;
        double number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static double getAnDouble(String input, String error) {
        int n;
        while (true) {
            try {
                System.out.print(input);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(error);
            }
        }
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }

    //check phone
    //ascii number 0-9 | 48 - 57
    public static boolean checkPhoneValidAscii(String phone) {
        if ((phone.length() == 10 || phone.length() == 11 || phone.length() == 12) && phone.charAt(0) == 48 && (phone.charAt(1) == 57 || phone.charAt(1) == 49 || phone.charAt(1) == 56)) {
            for (int i = 2; i < phone.length(); i++) {
                if (phone.charAt(i) < 48 || phone.charAt(i) > 57) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean checkStudentIDValidAscii(String id) {
        if (id.length() == 7 && id.charAt(0) == 71 && (id.charAt(1) == 67 || id.charAt(1) == 84)) {
            for (int i = 2; i < id.length(); i++) {
                if (id.charAt(i) < 48 || id.charAt(i) > 57) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean checkValidRegex(String valid, String regex) {
        if (valid.matches(regex) == false) {
            System.out.println("Invalid format!!!");
        }
        return valid.matches(regex);
    }

    public static boolean isNumber(String num) {
        int n = 0;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) > 57 || num.charAt(i) < 48) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkYearValid(int year) {
        //get current year, month, day
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (year > currentYear || year < 1900) {
            return false;
        }
        return true;
    }

    public static boolean checkMonthValid(int month) {
        if (month > 12 || month < 1) {
            return false;
        }
        return true;
    }

    //nam nhuam
    public static boolean isLeap(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 4 == 0 && year % 100 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDateValid(int year, int month, int day) {
        if (month <= 0 || month > 12 || year <= 0) {
            return false;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            // day 31
            if (day > 31 || day <= 0) {
                return false;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            //day 30
            if (day > 30 || day <= 0) {
                return false;
            }
        } else if (month == 2) {

            if (isLeap(year)) {
                if (day > 29 || day <= 0) {
                    return false;
                }
            } else {
                if (day > 28 || day <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Date getStringToDate(String stringtodate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_OF_SIMPLEDATEFORMAT);
        Date date = null;
        try {
            date = formatter.parse(stringtodate);

        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return date;
    }

    public static String getDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_OF_SIMPLEDATEFORMAT);
        String dateToString = "";
        try {
            dateToString = formatter.format(date);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dateToString;
    }

    public static Date inputDate(String stringtodate) {
        int day, month, year;
        String dateFormat = DATE_FORMAT_VALID;
        Date date = null;

        if (stringtodate.matches(dateFormat)) {
            try {

                StringTokenizer st = new StringTokenizer(stringtodate, "-");
                day = Integer.parseInt(st.nextToken());
                month = Integer.parseInt(st.nextToken());
                year = Integer.parseInt(st.nextToken());

                if (Utils.checkDateValid(year, month, day) == true) {
                    date = getStringToDate(stringtodate);
                } else {
                    System.out.println("Date invalid!!!");
                }

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("DateFormat invalid!!!");
        }
        return date;
    }

    public static String getDateCurrent() {
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_OF_SIMPLEDATEFORMAT);
        return dateFormat.format(d);
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diffInMillies = Math.abs(d1.getTime() - d2.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

//  long diff = getDifferenceDays(firstDate, secondDate);
//// Lay khoang cach giua 2 date
//        System.out.println("Difference Days : "+ diff);
    //So sanh 02 date
    //return 1 : neu d1 sau d2
    //return 0 : neu d1 = d2
    //return -1 : neu d1 truoc d2
//        System.out.println("Compare firstDate and secondDate:"+ 
//                firstDate.compareTo(secondDate));  
}
