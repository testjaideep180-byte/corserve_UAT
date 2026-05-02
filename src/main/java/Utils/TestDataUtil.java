package Utils;

public class TestDataUtil {


        public static String generateCustomerId() {
            int randomNum = (int)(Math.random() * 900000) + 100000;
            return "CUS-" + randomNum;
        }

        public static String generateEmail() {
            return "user" + System.currentTimeMillis() + "@test.com";
        }
    }

