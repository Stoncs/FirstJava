import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Position {
    private int i;
    private int j;

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return i == position.i &&
                j == position.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return i + "; " + j;
    }
}

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

    public List<Position> checkCross() {
        int current, i, j, m;
        int n = -1;      //текущий индекс в листах
        int max = 0;        //макс последовательность
        List<Position> listI = new ArrayList<>(List.of());  //лист для начала самой длинной последовательности
        List<Position> listJ = new ArrayList<>(List.of());  //лист для конца самой длинной последовательности
        Position begin = new Position(0,0);
        for (i = 0; i < desk; i++) {               //проверка горизонталей, если нет ни одного?
            if (field[i][0].equals("X")) {
                current = 1;
                begin = new Position(i,0);
            }
            else current = 0;
            for (j = 1; j < desk; j++)
                if (!field[i][j].equals("X")) {
                    if (current > max) {            //если последовательность оборвалась
                        max = current;
                        listI.add(begin);
                        listJ.add(new Position(i, j - 1));
                        n++;
                    }
                    current = 0;
                }
                else current += 1;
            if (current > max) {        //проверка в конце горизонтали
                max = current;
                listI.add(begin);
                listJ.add(new Position(i, desk - 1));
                n++;
            }
        }
        if (max == 0) return List.of();     //если поле пустое, вернуть пустой лист
        for (j = 0; j < desk; j++) {               //проверка вертикалей
            if (field[0][j].equals("X")) {
                current = 1;
                begin = new Position(0, j);
            }
            else current = 0;
            for (i = 1; i < desk; i++) {
                if (!field[i][j].equals("X")) {
                    if (current > max) {
                        max = current;
                        listI.add(begin);
                        listJ.add(new Position(i - 1, j));
                        n++;
                    }
                    current = 0;
                } else current += 1;
            }
            if (current > max) {
                max = current;
                listI.add(begin);
                listJ.add(new Position(desk - 1, j));
                n++;
            }
        }
        for (i = 0; i < desk; i++) {               //проверка одной диагонали и || ей (первая половина)
            if (field[i][0].equals("X")) current = 1;
            else current = 0;
            for (j = 1, m = i - 1; j < desk && m >= 0; m--, j++) {
                if (!field[m][j].equals("X")) {
                    if (current > max) {
                        max = current;
                        listI.add(begin);
                        listJ.add(new Position(m + 1, j - 1));
                        n++;
                    }
                    current = 0;
                } else current += 1;
            }
            if (current > max) {
                max = current;
                listI.add(begin);
                listJ.add(new Position(m + 1, j - 1));
                n++;
            }
        }
        for (j = 1; j < desk; j++) {               //вторая половина
            if (field[desk - 1][j].equals("X")) {
                current = 1;
                begin = new Position(desk - 1, j);
            }
            else current = 0;
            for (i = desk - 2, m = j + 1; i >= 0 && m < desk; i--, m++) {
                if (!field[i][m].equals("X")) {
                    if (current > max) {
                        max = current;
                        listI.add(begin);
                        listJ.add(new Position(i + 1, m - 1));
                        n++;
                    }
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) {
                max = current;
                listI.add(begin);
                listJ.add(new Position(i + 1, m - 1));
                n++;
            }
        }
        for (i = desk - 1; i >= 0; i--) {               //проверка другой диагонали и || ей (первая половина)
            if (field[i][0].equals("X")) {
                current = 1;
                begin = new Position(i,0);
            }
            else current = 0;
            for (j = 1, m = i + 1; j < desk && m < desk ; m++, j++) {
                if (!field[m][j].equals("X")) {
                    if (current > max) {
                        max = current;
                        listI.add(begin);
                        listJ.add(new Position(m - 1, j - 1));
                        n++;
                    }
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) {
                max = current;
                listI.add(begin);
                listJ.add(new Position(m + 1, j - 1));
                n++;
            }
        }
        for (j = 1; j < desk; j++) {                   //вторая половина
            if (field[0][j].equals("X")) {
                current = 1;
                begin = new Position(0,j);
            }
            else current = 0;
            for (i = 1, m = j + 1; i < desk && m < desk; i++, m++) {
                if (!field[i][m].equals("X")) {
                    if (current > max) {
                        max = current;
                        listI.add(begin);
                        listJ.add(new Position(i - 1, m - 1));
                        n++;
                    }
                    current = 0;
                }
                else current += 1;
            }
            if (current > max) {
                max = current;
                listI.add(begin);
                listJ.add(new Position(i - 1, m - 1));
                n++;
            }
        }
        return List.of(listI.get(n), listJ.get(n));
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
            TicTacToe game = new TicTacToe(5);
            List<Position> listI = new ArrayList<>(List.of());
            List<Position> listJ = new ArrayList<>(List.of());
            game.addCross(1,2);
            game.checkCross();
            System.out.println(game.checkCross().toString());
        }
    }

