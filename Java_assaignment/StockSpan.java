import java.util.Stack;

public class StockSpan {
    public static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] spans = new int[n];
        spans[0] = 1;

        for (int i = 1; i < n; i++) {
            int span = 1;
            int j = i - 1;

            while (j >= 0 && prices[i] >= prices[j]) {
                span += spans[j];
                j -= spans[j];
            }

            spans[i] = span;
        }

        return spans;
    }

    public static void main(String[] args) {
        int[] prices = {200,300,40,70,80};
        int[] spans = calculateSpan(prices);

        System.out.println("Spans: ");
        for (int span : spans) {
            System.out.print(span + " ");
        }
    }
}