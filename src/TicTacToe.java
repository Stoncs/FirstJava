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

    private Object[] spider(int i, int j, int max) {
        int k, n;
        Position end = new Position(-1, -1);
        Object[] result = new Object[2];
        int current = 1;
        for (n = j + 1; n < desk; n++) {        //по горизонтали
           if (!field[i][n].equals("X")) break;
           current++;
        }
        if (current > max) {
            end = new Position(i, n - 1);
            max = current;
        }
        current = 1;
        for (k = i + 1; k < desk; k++) {        //по вертикали
            if (!field[k][j].equals("X")) break;
            current++;
        }
        if (current > max) {
            end = new Position(k - 1, j);
            max = current;
        }
        current = 1;
        for (k = i + 1, n = j - 1; k < desk && n >= 0; k++, n--) {  //по диагонали сверху справа влево вниз
            if (!field[k][n].equals("X")) break;
            current++;
        }
        if (current > max) {
            end = new Position(k - 1, n + 1);
            max = current;
        }
        current = 1;
        for (k = i + 1, n = j + 1; k < desk && n < desk; k++, n++) {    //по диагонали сверху слева вправо вниз
            if (!field[k][n].equals("X")) break;
            current++;
        }
        if (current> max) {
            end = new Position(k - 1, n - 1);
            max = current;
        }
        result[0] = end;
        result[1] = max;
        return result;
    }

    public List<Position> checkCross() {
        int i, j;
        int max = 0;
        Position notFound = new Position(-1, -1);
        Object[] end;
        List<Position> listI = new ArrayList<>(List.of());  //лист для начала самой длинной последовательности
        List<Position> listJ = new ArrayList<>(List.of());  //лист для конца самой длинной последовательности
        for (i = 0; i < desk; i++) {
            for (j = 0; j < desk; j++) {
                if (field[i][j].equals("X")) {
                    end = spider(i, j, max);
                    if (!end[0].equals(notFound)) {
                        listI.add(new Position(i, j));
                        listJ.add((Position) end[0]);
                        max = (int) end[1];
                    }
                }
            }
        }
        if (max == 0) return List.of();
        return List.of(listI.get(listI.size() - 1), listJ.get(listJ.size() - 1));
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
            game.addCross(1,1);
            game.addCross(2,1);
            game.checkCross();
            System.out.println(game.checkCross().toString());
        }
    }

