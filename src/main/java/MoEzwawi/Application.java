package MoEzwawi;

import MoEzwawi.entities.Category;
import MoEzwawi.entities.Customer;
import MoEzwawi.entities.Product;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        File customers = new File("src/main/java/MoEzwawi/files/customers.txt");
        File products = new File("src/main/java/MoEzwawi/files/products.txt");
        //commento questa parte perché la generazioe randomica di 50 clienti e 50 prodotti (pokemon)
        //mi serve solo una volta visto che me la salvo su un file nel disco rigido
        /*for(int i=0;i<50;i++){
            Faker faker = new Faker(Locale.ITALY);
            Random rndm = new Random();
            try {
                writeCustomer(customers, faker.name().firstName(), rndm.nextInt(1,6));
            } catch (IOException e){
                throw new RuntimeException("Sorry");
            }
        }
        for(int i=0;i<50;i++){
            Faker faker = new Faker(Locale.ITALY);
            Random rndm = new Random();
            try {
                writeProduct(products, faker.pokemon().name(), Category.values()[rndm.nextInt(Category.values().length)], rndm.nextDouble(1,150));
            } catch (IOException e){
                throw new RuntimeException("Sorry");
            }
        }*/
        List<Customer> listOfCustomers = new ArrayList<>();
        List<Product> listOfProducts = new ArrayList<>();
        try {
            listOfCustomers = readCustomersFromFile(customers);
            listOfProducts = readProductsFromFile(products);
        } catch (IOException e){
            System.err.println("Error: "+e.getMessage());
        }
        System.out.println("Customers: "+System.lineSeparator()+listOfCustomers);
        System.out.println("Products: "+System.lineSeparator()+listOfProducts);
        Map<Category,List<Product>> productsByCategory = listOfProducts.stream().collect(
                Collectors.groupingBy(Product::getCategory)
        );
        System.out.println("Products by category:"+System.lineSeparator()+productsByCategory);
        List<Product> categoryCProducts = productsByCategory.get(Category.C);
        System.out.println("categoryCProducts"+categoryCProducts);
        Map<Integer,List<Customer>> customersByTier = listOfCustomers.stream().collect(Collectors.groupingBy(Customer::getTier));
        System.out.println("customersByTier"+customersByTier);
        List<Customer> tierOneCustomers = customersByTier.get(1);
        System.out.println("tierOneCustomers"+tierOneCustomers);
        double total = listOfProducts.stream().mapToDouble(Product::getPrice).sum();
        System.out.println("TOTAL: "+total);
        double maxPrice = listOfProducts.stream().mapToDouble(Product::getPrice).max().getAsDouble();
        System.out.println("MAX PRICE: "+maxPrice);
        double averageOfCPrices = categoryCProducts.stream().mapToDouble(Product::getPrice).average().getAsDouble();
        System.out.println("MEDIA: "+averageOfCPrices);
    }
    public static void writeCustomer(File file, String customerName, int customerTier) throws IOException {
        FileUtils.writeStringToFile(file,customerName+"@"+customerTier+"#"+System.lineSeparator(), StandardCharsets.UTF_8,true);
    }public static void writeProduct(File file, String productName, Category category, double price) throws IOException {
        FileUtils.writeStringToFile(file,productName+"@"+category+"@"+price+"#"+System.lineSeparator(), StandardCharsets.UTF_8,true);
    }
    public static List<Customer> readCustomersFromFile (File file) throws IOException {
        List<Customer> newListOfCustomers = new ArrayList<>();
        String data = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
        List<String> listOfCustomersData = Arrays.asList(data.split("#"));
        listOfCustomersData.forEach(customer -> {
            List<String> customerData = Arrays.asList(customer.split("@"));
            if(customerData.size()==2) {
                try {
                    newListOfCustomers.add(new Customer(customerData.get(0), Integer.parseInt(customerData.get(1))));
                } catch (Exception e) {
                    System.err.println("Error in reading customers"+System.lineSeparator()+"Exception: " + e.getMessage());
                }
            }
        });
        return  newListOfCustomers;
    }
    public static List<Product> readProductsFromFile (File file) throws IOException{
        List<Product> newListOfProducts = new ArrayList<>();
        String data = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
        List<String> listOfProductsData = Arrays.asList(data.split("#"));
        listOfProductsData.forEach(product -> {
            String[] productData = product.split("@");
            if(productData.length == 3) {
                try {
                    String productName = productData[0];
                    Category category = null;
                    switch (productData[1]) {
                        case "A" -> category = Category.A;
                        case "B" -> category = Category.B;
                        case "C" -> category = Category.C;
                        default -> System.err.println("You messed up dude");
                    }
                    double price = Double.parseDouble(productData[2]);
                    newListOfProducts.add(new Product(productName, category, price));
                } catch (Exception e) {
                    System.err.println("Error in reading products"+System.lineSeparator()+"Exception: " + e.getMessage());
                }
            }
        });
        return newListOfProducts;
    }
}
