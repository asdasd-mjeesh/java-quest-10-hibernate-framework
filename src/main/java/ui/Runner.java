package ui;

import org.hibernate.HibernateException;
import service.Store;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {
    private final Store store;
    private final ShowManager showManager;
    private final ExecuteManager executeManager;

    public Runner() {
        store = new Store(HibernateUtil.buildSessionFactory());
        showManager = new ShowManager(store);
        executeManager = new ExecuteManager(store);
    }

    public static void main(String[] args) {
        new Runner().run();
    }

    private void run() {
        Scanner in = new Scanner(System.in);
        String choice;
        String nameFilter;
        int costFilter;
        LocalDate dateFilter;

        boolean cycleIsRun = true;
        while (cycleIsRun) {
            showManager.showMenu();
            System.out.print("CHOICE:\t");
            choice = in.nextLine();

            try {
                switch (choice) {
                    case "a", "A" -> {
                        System.out.print("name:\t");
                        nameFilter = in.nextLine();
                        showManager.showProductsWithNameAndSortedByShelfLife(nameFilter);
                    }
                    case "b", "B" -> {
                        System.out.print("name:\t");
                        nameFilter = in.nextLine();
                        System.out.print("\nmax cost:\t");
                        costFilter = in.nextInt();
                        showManager.showProductsWithNameAndCostALess(nameFilter, costFilter);
                    }
                    case "c", "C" -> {
                        dateFilter = executeManager.createDate();
                        showManager.showProductWithShelfLifeAlong(dateFilter);
                    }
                    case "d", "D" -> showManager.showAllProductsSortedByPrice();
                    case "e", "E" -> showManager.showAllProducers();
                    case "f", "F" -> showManager.showAllProducersWithThemProducts();
                    case "1" -> showManager.showAll();
                    case "2" -> executeManager.addProduct();
                    case "3" -> {
                        System.out.print("id:\t");
                        long id = in.nextLong();
                        executeManager.deleteProduct(id);
                    }
                    case "4" -> executeManager.addProducer();
                    case "5" -> {
                        System.out.print("id:\t");
                        long id = in.nextLong();
                        executeManager.deleteProducer(id);
                    }
                    case "0" -> cycleIsRun = false;

                    default -> {
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("was input invalid value");
            } catch (HibernateException e) {
                System.out.println("input error. Was enter invalid value or item with this name is exist");
            }
        }
    }

    private void run1() {


        var result = store.getProducts();
        System.out.println(result);


    }
}
