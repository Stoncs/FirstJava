public class TicTacToe {
    String[][] field;
    public TicTacToe(int n){
        int i;
        int j;
        field = new String[n][n];
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j)
            field[i][j] = "_";
        }
    }
    public void Show(int n){
        int i;
        int j;
        for (i = 0; i < n; ++i) {
            System.out.println("\n");
            for (j = 0; j < n; ++j)
                System.out.print(field[i][j] + "  ");
        }
    }
}

class GameDemo {
    public static void main (String args[]) {
        TicTacToe demo = new TicTacToe(5);
        demo.Show(5);
    }
}
