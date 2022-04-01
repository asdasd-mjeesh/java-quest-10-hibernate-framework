package ui;

import service.Store;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExecuteManager implements CreatigADate {
    private final Store store;
    private final Scanner in;

    public ExecuteManager(Store store) {
        this.store = store;
        in = new Scanner(System.in);
    }

    public void addProduct() {
        try {
            System.out.print(" Введите название продукта:\t");
            String name = in.nextLine();

            System.out.print(" Введите id произодителя:\t");
            Long producerId = in.nextLong();

            System.out.print(" Введите стоимость продукта:\t");
            int cost = in.nextInt();

            LocalDate shelfLife = createDate();

            System.out.print(" Введите количество продуктов:\t");
            int count = in.nextInt();
            in.nextLine();

            store.saveProduct(
                    name,
                    cost,
                    shelfLife,
                    count,
                    producerId
            );

        } catch (InputMismatchException e) {
            System.err.println("ашыпка ввода");
        }
    }

    public void addProducer() {
        System.out.println(" Введите имя производителя:\t");
        in.nextLine();
        String name = in.nextLine();

        System.out.println(" Введите номер телефона производителя:\t");
        int contact = in.nextInt();

        store.saveProducer(name, contact);
    }

    public void deleteProduct(long id) {
        store.deleteProduct(id);
    }

    public void deleteProducer(long id) {
        store.deleteProducer(id);
    }
}
