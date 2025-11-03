import java.util.*;

class CustomerOrder {
    int orderId;
    long timestamp;

    public CustomerOrder(int orderId, long timestamp) {
        this.orderId = orderId;
        this.timestamp = timestamp;
    }
}

public class Assignment1 {

    // Merge Sort main function
    public static void mergeSort(CustomerOrder[] orders, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(orders, left, mid);
            mergeSort(orders, mid + 1, right);
            merge(orders, left, mid, right);
        }
    }

    // Merge logic
    private static void merge(CustomerOrder[] orders, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        CustomerOrder[] L = new CustomerOrder[n1];
        CustomerOrder[] R = new CustomerOrder[n2];

        for (int i = 0; i < n1; i++)
            L[i] = orders[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = orders[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].timestamp <= R[j].timestamp)
                orders[k++] = L[i++];
            else
                orders[k++] = R[j++];
        }

        while (i < n1) orders[k++] = L[i++];
        while (j < n2) orders[k++] = R[j++];
    }

    // Generate random orders
    private static CustomerOrder[] generateRandomOrders(int n) {
        Random rand = new Random();
        CustomerOrder[] orders = new CustomerOrder[n];
        long baseTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            long timestamp = baseTime + rand.nextInt(1_000_000);
            orders[i] = new CustomerOrder(i + 1, timestamp);
        }
        return orders;
    }

    public static void main(String[] args) {
        int n = 1_000_000; // number of orders
        System.out.println("Sorting " + n + " customer orders by timestamp using Merge Sort...");

        CustomerOrder[] orders = generateRandomOrders(n);

        long start = System.currentTimeMillis();
        mergeSort(orders, 0, orders.length - 1);
        long end = System.currentTimeMillis();

        System.out.println("Sorting completed in " + (end - start) + " ms\n");

        System.out.println("First 10 sorted orders:");
        for (int i = 0; i < 10; i++) {
            System.out.println("Order ID: " + orders[i].orderId + " , Timestamp: " + orders[i].timestamp);
        }
    }
}
