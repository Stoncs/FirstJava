public class TicTacToe {
    String[][] field;
    private int desk;
    public TicTacToe(int n){
        desk = n;
        int i, j;
        field = new String[n][n];
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j)
            field[i][j] = "_";
        }
    }

    private void show(){
        int i, j;
        for (i = 0; i < desk; ++i) {
            for (j = 0; j < desk; ++j)
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

    public void delete(int x, int y) {
        field[y - 1][x - 1] = "_";
    }

    public int checkCross() {
        int current, i, j, m;
        int max = 0;
        for (i = 0; i < desk; i++) {               //проверка горизонталей, если нет ни одного?
            if (field[i][0].equals("X"))
            current = 1;
             else current = 0;
            for (j = 1; j < desk; j++)
            if (!field[i][j].equals("X")) {
                if (current > max) max = current;
                current = 0;
            }
            else current += 1;
            if (current > max) max = current;
        }
        if (max == 0) return max;
        for (j = 0; j < desk; j++) {               //проверка вертикалей
            if (field[0][j].equals("X")) current = 1;
            else current = 0;
            for (i = 1; i < desk; i++) {
                if (!field[i][j].equals("X")) {
                    if (current > max) max = current;
                    current = 0;
                } else current += 1;
            }
            if (current > max) max = current;
        }
        for (i = 0; i < desk; i++) {               //проверка одной диагонали и || ей (первая половина)
            if (field[i][0].equals("X")) current = 1;
            else current = 0;
            for (j = 1, m = i - 1; j < desk && m >= 0; m--, j++) {
                if (!field[m][j].equals("X")) {
                    if (current > max) max = current;
                    current = 0;
                } else current += 1;
            }
            if (current > max) max = current;
        }
        for (j = 1; j < desk; j++) {               //вторая половина
            if (field[desk - 1][j].equals("X")) current = 1;
            else current = 0;
            for (i = desk - 2, m = j + 1; i >= 0 && m < desk; i--, m++) {
                if (!field[i][m].equals("X")) {
                    if (current > max) max = current;
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) max = current;
        }
        for (i = desk - 1; i >= 0; i--) {               //проверка другой диагонали и || ей (первая половина)
            if (field[i][0].equals("X")) current = 1;
            else current = 0;
            for (j = 1, m = i + 1; j < desk && m < desk ; m++, j++) {
                if (!field[m][j].equals("X")) {
                    if (current > max) max = current;
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) max = current;
        }
        for (j = 1; j < desk; j++) {                   //вторая половина
            if (field[0][j].equals("X")) current = 1;
            else current = 0;
            for (i = 1, m = j + 1; i < desk && m < desk; i++, m++) {
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

    public int checkZero() {           //тоже самое, что и checkCross, только вместо X - 0
        int current, i, j, m;
        int max = 0;
        for (i = 0; i < desk; i++) {
            if (field[i][0].equals("0"))
                current = 1;
            else current = 0;
            for (j = 1; j < desk; j++)
                if (!field[i][j].equals("0")) {
                    if (current > max) max = current;
                    current = 0;
                }
                else current += 1;
            if (current > max) max = current;
        }
        if (max == 0) return max;
        for (j = 0; j < desk; j++) {
            if (field[0][j].equals("0")) current = 1;
            else current = 0;
            for (i = 1; i < desk; i++) {
                if (!field[i][j].equals("0")) {
                    if (current > max) max = current;
                    current = 0;
                } else current += 1;
            }
            if (current > max) max = current;
        }
        for (i = 0; i < desk; i++) {
            if (field[i][0].equals("0")) current = 1;
            else current = 0;
            for (j = 1, m = i - 1; j < desk && m >= 0; m--, j++) {
                if (!field[m][j].equals("0")) {
                    if (current > max) max = current;
                    current = 0;
                } else current += 1;
            }
            if (current > max) max = current;
        }
        for (j = 1; j < desk; j++) {
            if (field[desk - 1][j].equals("0")) current = 1;
            else current = 0;
            for (i = desk - 2, m = j + 1; i >= 0 && m < desk; i--, m++) {
                if (!field[i][m].equals("0")) {
                    if (current > max) max = current;
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) max = current;
        }
        for (i = desk - 1; i >= 0; i--) {
            if (field[i][0].equals("0")) current = 1;
            else current = 0;
            for (j = 1, m = i + 1; j < desk && m < desk ; m++, j++) {
                if (!field[m][j].equals("0")) {
                    if (current > max) max = current;
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) max = current;
        }
        for (j = 1; j < desk; j++) {
            if (field[0][j].equals("0")) current = 1;
            else current = 0;
            for (i = 1, m = j + 1; i < desk && m < desk; i++, m++) {
                if (!field[i][m].equals("0")) {
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
            TicTacToe game2 = new TicTacToe(6);
            game2.addCross(2,2);
            game2.addCross(3,2);
            game2.addCross(5,2);
            game2.addCross(6,2);
            game2.addZero(4,2);
            game2.addCross(4,3);
            game2.addCross(4,4);
            game2.addCross(4,5);
            game2.addCross(5,4);
            game2.addCross(6,5);
            game2.show();
            System.out.println(game2.checkCross());
        }
    }

