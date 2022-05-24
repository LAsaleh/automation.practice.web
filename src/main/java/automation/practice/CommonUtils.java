package automation.practice;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

    public class CommonUtils {

        public static String randomZipCode(){
            return new Faker().address().zipCode().substring(0,5);

        }

        public static String  randomPhoneNumber(){
            return String.format("(%03d) %03d-%04d",
                    (int) Math.floor(999*Math.random()),
                    (int) Math.floor(999*Math.random()),
                    (int) Math.floor(9999*Math.random()));
        }




    public static String generateRandomString(int limit){
        String saltChars = "qwertyuiopasdfghjklzxcvbnm1234567890./".toLowerCase();
        StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < limit){
                int index = (int) (rnd.nextFloat() * saltChars.length());
                salt.append(saltChars.charAt(index));
            }
            String saltStr = salt.toString();
            return saltStr;


        }



    public static String randomState(){
            return new Faker().address().state();
        }

    public static String randomCompanyName(){
            return new Faker().company().name();
}

    public static String randomCity() {
        return new Faker().address().cityName();


}

    public static String randomStreetAddress(){
        return new Faker().address().streetAddress();



}


    public static int randomNumber(int from , int to ){
        return (int)(Math.random()* (to - from + 1)+ from);
    }



    public static String randomEmail(){
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String[] domain = {"@gmail.com","@icloud.com","@yahoo.com","@hotmail.com"};

        String email = lastName + "." + firstName + domain[(int) (Math.random() * (4))];
        return email.toLowerCase(Locale.ROOT);


    }

    public static String randomDOBAbove18 () {

        LocalDate startDate = LocalDate.of(1950, 1, 1);
        long start = startDate.toEpochDay();

        LocalDate endDate = LocalDate.of(LocalDate.now().getYear() - 18, 1, 1);
        long end = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();

        return LocalDate.ofEpochDay(randomEpochDay).toString();
    }


    public static void main(String[] args) {

//        String email = "wilkinson.edgar@hotmail.com";
//
//        int atSign = email.indexOf("@");
//        String[] fullName = email.substring(0, atSign).split("\\.");
//        String firstName = fullName[1].substring(0,1).toLowerCase() + fullName[1].substring(1);
//        String lastName = fullName[0].substring(0,1).toLowerCase() + fullName[1].substring(1);
//
//
//        System.out.println(firstName);
//        System.out.println(lastName);
//
//        System.out.println(randomNumber(1,30));
        System.out.println(randomDOBAbove18());
        System.out.println(generateRandomString(50));
        System.out.println(randomPhoneNumber());

    }

}
