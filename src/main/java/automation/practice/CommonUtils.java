package automation.practice;

import com.github.javafaker.Faker;

import java.util.Locale;

public class CommonUtils {
    public static String randomEmail(){
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String[] domain = {"@gmail.com","@icloud.com","@yahoo.com","@hotmail.com"};

        String email = lastName + "." + firstName + domain[(int) (Math.random() * (4))];
        return email.toLowerCase(Locale.ROOT);


    }

    public static void main(String[] args) {

        String email = "wilkinson.edgar@hotmail.com";

        int atSign = email.indexOf("@");
        String[] fullName = email.substring(0, atSign).split("\\.");
        String firstName = fullName[1].substring(0,1).toLowerCase() + fullName[1].substring(1);
        String lastName = fullName[0].substring(0,1).toLowerCase() + fullName[1].substring(1);


        System.out.println(firstName);
        System.out.println(lastName);



    }

}
