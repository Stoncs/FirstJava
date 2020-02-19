public class TicTacToe {
    String[][] field;

    public TicTacToe(int n){
        int i, j;
        field = new String[n][n];
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j)
            field[i][j] = "_";
        }
    }

    void Show(int n){
        int i, j;
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j)
                System.out.print(field[i][j] + "  ");
            System.out.println();
        }
    }

    void AddZero(int x, int y) {
        field[y - 1][x - 1] = "0";
    }

    void AddCross(int x, int y) {
        field[y - 1][x - 1] = "X";
    }

    void Delete(int x, int y) {
        field[y - 1][x - 1] = "_";
    }

    void CheckCross(int n) {
        int current, i, j, m;
        int max = 0;
        /*for (i = 0; i < n; i++) {
            if (field[i][0].equals("X")) current = 1;
                else current = 0;
            for (j = 1; j < n; j++)
            if (field[i][j].equals("X") && field[i][j - 1].equals("X")) current += 1;
                else {
                    if (current > max) max = current;
                    current = 0;
            }
            if (current > max) max = current;
        }
        for (j = 0; j < n; j++) {
            if (field[0][j].equals("X")) current = 1;
            else current = 0;
            for (i = 1; i < n; i++)
                if (field[i][j].equals("X") && field[i - 1][j].equals("X")) current += 1;
                else {
                    if (current > max) max = current;
                    current = 0;
                }
            if (current > max) max = current;
        } */
        for (i = 0; i < n; i++) {
            if (field[i][0].equals("X")) current = 1;
            else current = 0;
            for (j = 1, m = i - 1; j < n && m >= 0; j++, m--) {
                if (field[m][j].equals("X") && field[m + 1][j - 1].equals("X")) current += 1;
                else {
                    if (current > max) max = current;
                    current = 0;
                }
            }
            if (current > max) max = current;
        }
        System.out.println(max);
    }
}

class GameDemo {
    public static void main (String[] args) {
        TicTacToe demo = new TicTacToe(5);
        demo.AddCross(1, 2);
        demo.AddCross(2, 2);
        demo.AddCross(3, 2);
        demo.AddCross(4, 2);
        demo.AddCross(3, 3);
        demo.AddCross(1, 5);
        demo.AddCross(2, 1);
        demo.Show(5);
        demo.CheckCross(5);
    }
}
