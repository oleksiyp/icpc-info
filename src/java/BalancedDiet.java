import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class BalancedDiet {
    int []sweetsDistribution;
    int []ateSweets;
    int nSweetTypes;
    int nSweetsAte;
    int totalSweetsInDistribution;

    BufferedReader input;
    StringTokenizer tokenizer;

    PriorityQueue<Eatable> queue;

    void readInput() throws IOException {
        initInput();
        readLine();
        nSweetTypes = nextIntToken();
        nSweetsAte = nextIntToken();

        sweetsDistribution = new int[nSweetTypes];
        ateSweets = new int[nSweetTypes];

        readLine();
        for (int i = 0; i < nSweetTypes; i++) {
            int distributionValue = nextIntToken();
            sweetsDistribution[i] = distributionValue;
            totalSweetsInDistribution += distributionValue;
        }

        int typeOfSweetAte;
        readLine();
        for (int i = 0; i < nSweetsAte; i++) {
            typeOfSweetAte = nextIntToken();
            typeOfSweetAte--;
            ateSweets[typeOfSweetAte]++;
        }
    }

    private void initQueue() {
        queue = new PriorityQueue<>();
        for (int i = 0; i < nSweetTypes; i++) {
            queue.add(new Eatable(i));
        }
    }

    class Eatable implements Comparable<Eatable> {
        int ratio;
        int sweetType;

        Eatable(int sweetType) {
            this.sweetType = sweetType;

            calc();
        }

        public void calc() {
            int ate = ateSweets[sweetType] + 1;

            int m = BalancedDiet.this.totalSweetsInDistribution;
            int n = sweetsDistribution[sweetType];

            ratio = (ate * m + n - 1) / n;
        }

        public void eat() {
            ateSweets[sweetType]++;
        }

        @Override
        public int compareTo(Eatable o) {
            return Integer.compare(ratio, o.ratio);
        }
    }

    private int simulateEatingSweets() {
        for (int n = nSweetsAte + 1; n <= nSweetsAte + totalSweetsInDistribution * 2; n++) {
            Eatable s = queue.peek();
            if (s.ratio > n) {
                return s.ratio;
            }
            queue.poll();
            s.eat();
            s.calc();
            queue.add(s);
        }
        return -1;
    }

    private int nextIntToken() {
        return Integer.parseInt(nextToken());
    }

    private void initInput() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    private void readLine() throws IOException {
        tokenizer = new StringTokenizer(input.readLine());
    }

    private String nextToken() {
        return tokenizer.nextToken();
    }


    public static void main (String[] args) throws java.lang.Exception {
        BalancedDiet diet = new BalancedDiet();
        diet.readInput();
        diet.initQueue();
        int r = diet.simulateEatingSweets();
        if (r == -1) {
            System.out.println("forever");
        } else {
            System.out.println(r);
        }
    }

}