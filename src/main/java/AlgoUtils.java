public class AlgoUtils {
    /**
     * e.g if x = 100 -> return 1+2+...100
     * @param x a specific number
     * @return accumulated value
     */
    static int sumOneToN(int x) {
        boolean b = x > 0 && (x += sumOneToN(x - 1)) > 0;
        return x;
    }
}
