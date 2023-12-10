package MoEzwawi;

import MoEzwawi.entities.Customer;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Application {

    public static void main(String[] args) {
        File customers = new File("src/main/java/MoEzwawi/files/customers.txt");
        //commento questa parte perché la generazioe randomica di nomi e tier mi serve solo una
        //volta visto che me la salvo su un file nel disco rigido
        /*for(int i=0;i<50;i++){
            Faker faker = new Faker(Locale.ITALY);
            Random rndm = new Random();
            try {
                writeCustomer(customers, faker.name().firstName(), rndm.nextInt(1,6));
            } catch (IOException e){
                throw new RuntimeException("Sorry");
            }
        }*/
        File products = new File("src/main/java/MoEzwawi/files/products.txt");
        for(int i=0;i<50;i++){
            Faker faker = new Faker(Locale.ITALY);
            Random rndm = new Random();
            try {
                writeProduct(products, faker.pokemon().name(), rndm.nextInt(1,6));
            } catch (IOException e){
                throw new RuntimeException("Sorry");
            }
        }
    }
    public static void writeCustomer(File file, String customerName, int customerTier) throws IOException {
        FileUtils.writeStringToFile(file,customerName+"@"+customerTier+"#", StandardCharsets.UTF_8,true);
    }public static void writeProduct(File file, String Name, int customerTier) throws IOException {
        FileUtils.writeStringToFile(file,customerName+"@"+customerTier+"#", StandardCharsets.UTF_8,true);
    }

}
