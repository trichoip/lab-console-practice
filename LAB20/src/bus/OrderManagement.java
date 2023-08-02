package bus;


import model.Customer;
import model.Order;
import model.Product;
import repo.CustomerRepository;
import repo.OrderRepository;
import repo.ProductRepository;
import tools.Utils;

public class OrderManagement {

    OrderRepository orders = new OrderRepository();
    CustomerRepository customers = new CustomerRepository();
    ProductRepository products = new ProductRepository();

    public void printProducts() {
        products.readFromFile();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void printCustomers() {
        customers.readFromFile();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void printOrders() {
        orders.readFromFile();
        orders.sort((o1, o2) -> customers.details(o1.getCustomerID()).getCustomerName()
                .compareTo(customers.details(o2.getCustomerID()).getCustomerName()));
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void searchCutomer() {
        String id = Utils.getString("Enter customer id: ");
        Customer customer = customers.details(id);
        if (customer != null) {
            System.out.println(customer);
            return;
        }

        System.out.println("This customer does not exist");
    }

    public void addCustomer() {
        String id = Utils.getString("Enter customer id: ");
        if (customers.details(id) != null) {
            System.out.println("This customer already exists");
            return;
        }
        String name = Utils.getString("Enter customer name: ");

        if (name == null || name.trim().length() == 0) {
            System.out.println("Customer Name cannot be null");
            return;
        }
        String address = Utils.getString("Enter customer address: ");
        if (address == null || address.trim().length() == 0) {
            System.out.println("Customer Address cannot be null");
            return;
        }
        String phone = Utils.getString("Enter customer phone: ");

        if (!Utils.checkValidRegex(phone, Utils.REGEX_PHONE)) {
            System.out.println("Customer Phone must be a string with length from 10 to 12 characters");
            return;
        }
        customers.create(new Customer(id, name, address, phone));
        System.out.println("Customer added successfully");
    }

    public void updateCustomer() {

        String id = Utils.getString("Enter customer id: ");
        Customer customer = customers.details(id);
        if (customer == null) {
            System.out.println("This customer does not exist");
            return;
        }

        String name = Utils.updateString("Enter new customer name: ", customer.getCustomerName());

        if (name == null || name.trim().length() == 0) {
            System.out.println("Customer Name cannot be null");
            return;
        }
        String address = Utils.updateString("Enter new customer address: ", customer.getCustomerAddress());
        if (address == null || address.trim().length() == 0) {
            System.out.println("Customer Address cannot be null");
            return;
        }
        String phone = Utils.updateString("Enter new customer phone: ", customer.getCustomerPhone());

        if (!Utils.checkValidRegex(phone, Utils.REGEX_PHONE)) {
            System.out.println("Customer Phone must be a string with length from 10 to 12 characters");
            return;
        }
        customers.update(new Customer(id, name, address, phone));
        System.out.println("Customer updated successfully");
    }

    public void saveCustomersToFile() {
        customers.saveToFile();
    }

    public void printPendingOrder() {
        orders.readFromFile();
        for (Order order : orders) {
            if (!order.isStatus()) {
                System.out.println(order);
            }
        }
    }

    public void addOrder() {

        String id = Utils.getString("Enter order id: ");
        if (orders.details(id) != null) {
            System.out.println("This order already exists");
            return;
        }

        for (int i = 0; i < customers.size() - 1; i++) {
            System.out.println(i + " : " + customers.get(i));
        }

        int customerIndex = Utils.getInt("Choose customer: ", 0, customers.size() - 1);

        for (int i = 0; i < products.size() - 1; i++) {
            System.out.println(i + " : " + products.get(i));
        }
        int productIndex = Utils.getInt("Choose product: ", 0, products.size() - 1);

        int quantity = Utils.getInt("Enter order quantity: ", 1, 1000000000);
        String date = Utils.getString("Enter order date(MM/DD/YYYY): ");
        if (Utils.inputDate(date) == null) {
            System.out.println("Order Date invalid");
            return;
        }
        String status = Utils.getString("Enter order status(true,false): ");
        if (!Utils.checkValidRegex(status, Utils.CHECK_BOOLEAN)) {
            System.out.println("Order Status invalid");
            return;
        }

        orders.create(
                new Order(id, customers.get(customerIndex).getCustomerID(), products.get(productIndex).getProductID(),
                        quantity, "",
                        Boolean.valueOf(status)));
        System.out.println("Order added successfully");
    }

    public void saveOrders() {
        orders.saveToFile();
    }

    public void updateOrder() {
        String id = Utils.getString("Enter order id: ");
        Order order = orders.details(id);
        if (order == null) {
            System.out.println("This order does not exist");
            return;
        }
        String status = Utils.updateString("Enter order status(true,false): ", String.valueOf(order.isStatus()));
        if (!Utils.checkValidRegex(status, Utils.CHECK_BOOLEAN)) {
            System.out.println("Order Status invalid");
            return;
        }
        orders.update(
                new Order(id, order.getCustomerID(), order.getProductID(),
                        order.getOrderQuantity(), order.getOrderDate(),
                        Boolean.valueOf(status)));
        System.out.println("Order updated successfully");
    }

    public void deleteOrder() {
        String id = Utils.getString("Enter order id: ");

        if (orders.details(id) != null) {
            boolean check = Utils.confirmYesNo("Are you sure you want to delete this order? (Y/N): ");
            if (check) {
                orders.delete(id);
                System.out.println("Order deleted successfully");
                return;
            } else {
                System.out.println("Order not deleted");
                return;
            }

        }
        System.out.println("Order does not exist");
    }

}
