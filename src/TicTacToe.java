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

    private void show(int n){
        int i, j;
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j)
                System.out.print(field[i][j] + "  ");
            System.out.println();
        }
    }

    public void addZero(int x, int y) {
        field[y - 1][x - 1] = "0";
    }

    public void addCross(int x, int y) {
        field[y - 1][x - 1] = "X";
    }

    public void Delete(int x, int y) {
        field[y - 1][x - 1] = "_";
    }

    public int checkCross(int n) {
        int current, i, j, m;
        int max = 0;
        for (i = 0; i < n; i++) {               //проверка горизонталей, если нет ни одного?
            if (field[i][0].equals("X"))
            current = 1;
             else current = 0;
            for (j = 1; j < n; j++)
            if (!field[i][j].equals("X")) {
                if (current > max) max = current;
                current = 0;
            }
            else current += 1;
            if (current > max) max = current;
        }
        if (max == 0) return max;
        for (j = 0; j < n; j++) {               //проверка вертикалей
            if (field[0][j].equals("X")) current = 1;
            else current = 0;
            for (i = 1; i < n; i++) {
                if (field[i][j].equals("X")) {
                    if (current > max) max = current;
                    current = 0;
                } else current += 1;
            }
            if (current > max) max = current;
        }
        for (i = 0; i < n; i++) {               //проверка одной диагонали и || ей (первая половина)
            if (field[i][0].equals("X")) current = 1;
            else current = 0;
            for (j = 1, m = i - 1; j < n && m >= 0; m--, j++) {
                if (field[m][j].equals("X") && field[m + 1][j - 1].equals("X")) current += 1;
                else {
                    if (current > max) max = current;
                    current = 0;
                }
            }
            if (current > max) max = current;
        }
        for (j = 1; j < n; j++) {               //вторая половина
            if (field[n - 1][j].equals("X")) current = 1;
            else current = 0;
            for (i = n - 2, m = j + 1; i >= 0 && m < n; i--, m++) {
                if (!field[i][m].equals("X")) {
                    if (current > max) max = current;
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) max = current;
        }
        for (i = n - 1; i >= 0; i--) {               //проверка другой диагонали и || ей (первая половина)
            if (field[i][0].equals("X")) current = 1;
            else current = 0;
            for (j = 1, m = i + 1; j < n && m < n ; m++, j++) {
                if (!field[m][j].equals("X")) {
                    if (current > max) max = current;
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) max = current;
        }
        for (j = 1; j < n; j++) {                   //вторая половина
            if (field[0][j].equals("X")) current = 1;
            else current = 0;
            for (i = 0, m = j + 1; i >= 0 && m < n; i++, m++) {
                if (!field[i][m].equals("X")) {
                    if (current > max) max = current;
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) max = current;
        }
        return max;
    }

        public static void main (String[] args) {
            TicTacToe demo = new TicTacToe(5);
            demo.addCross(1, 2);
            demo.addCross(2, 2);
            demo.addCross(3, 2);
            demo.addCross(4, 4);
            demo.addCross(5, 5);
            demo.addCross(3, 3);
            demo.addCross(1, 5);
            demo.addCross(2, 1);
            demo.addCross(5,4);
            demo.addCross(4, 5);
            demo.show(5);
            System.out.println(demo.checkCross(5));
        }
    }

