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
        return "(" + i + "; " + j + ")";
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

    public void addZero(int i, int j) {
        field[i][j] = "0";
    }

    public void addCross(int i, int j) {
        field[i][j] = "X";
    }

    public void delete(int i, int j) {
        field[i][j] = "_";
    }

    private Object[] spider(int i, int j, int max, String expected) {        //ищет последовательности во всех направлениях
        int k, n;
        Position end = new Position(-1, -1);
        Object[] result = new Object[2];
        int current = 1;
        for (n = j + 1; n < desk; n++) {        //по горизонтали
           if (!field[i][n].equals(expected)) break;
           current++;
        }
        if (current > max) {
            end = new Position(i, n - 1);
            max = current;
        }
        current = 1;
        for (k = i + 1; k < desk; k++) {        //по вертикали
            if (!field[k][j].equals(expected)) break;
            current++;
        }
        if (current > max) {
            end = new Position(k - 1, j);
            max = current;
        }
        current = 1;
        for (k = i + 1, n = j - 1; k < desk && n >= 0; k++, n--) {  //по диагонали сверху справа влево вниз
            if (!field[k][n].equals(expected)) break;
            current++;
        }
        if (current > max) {
            end = new Position(k - 1, n + 1);
            max = current;
        }
        current = 1;
        for (k = i + 1, n = j + 1; k < desk && n < desk; k++, n++) {    //по диагонали сверху слева вправо вниз
            if (!field[k][n].equals(expected)) break;
            current++;
        }
        if (current> max) {
            end = new Position(k - 1, n - 1);
            max = current;
        }
        result[0] = end;    //координата конца последовательности
        result[1] = max;    //новая максимальная длина последовательности
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
                    end = spider(i, j, max, "X");
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

    public List<Position> checkZero() {     //тоже самое, только вместо "X" - "0"
        int i, j;
        int max = 0;
        Position notFound = new Position(-1, -1);
        Object[] end;
        List<Position> listI = new ArrayList<>(List.of());  //лист для начала самой длинной последовательности
        List<Position> listJ = new ArrayList<>(List.of());  //лист для конца самой длинной последовательности
        for (i = 0; i < desk; i++) {
            for (j = 0; j < desk; j++) {
                if (field[i][j].equals("0")) {
                    end = spider(i, j, max, "0");
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

        public static void main (String[] args) {
            TicTacToe game = new TicTacToe(5);
            game.addCross(1,1);
            game.addCross(2,1);
            game.checkCross();
            System.out.println(game.checkCross().toString());
        }
    }

