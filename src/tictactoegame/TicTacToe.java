package tictactoegame;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private Value[][] field;
    private int size;
    public TicTacToe(int n){
        size = n;
        int i, j;
        field = new Value[n][n];
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j)
            field[i][j] = Value.E;
        }
    }

    public Value returnValue (int i, int j) {
        return field[i][j];
    }

    private void show(){
        int i, j;
        for (i = 0; i < size; ++i) {
            for (j = 0; j < size; ++j)
                System.out.print(field[i][j] + "  ");
            System.out.println();
        }
    }

    public void addZero(int i, int j) {
        field[i][j] = Value.O;
    }

    public void addCross(int i, int j) {
        field[i][j] = Value.X;
    }

    public void delete(int i, int j) {
        field[i][j] = Value.E;
    }

    static class ForSpider{     //вложенный класс, чтобы вернуть два значения разных типов
        private final int max;
        private final Position end;

        public ForSpider(int max, Position end) {
            this.max = max;
            this.end = end;
        }
        public int getMax() {
            return max;
        }
        public Position getEnd() {
            return end;
        }
    }

    private ForSpider spider(int i, int j, int max, Value expected) {        //ищет последовательности во всех направлениях
        int k, n;
        Position end = notFound;
        int current = 1;
        for (n = j + 1; n < size; n++) {        //по горизонтали
           if (!field[i][n].equals(expected)) break;
           current++;
        }
        if (current > max) {
            end = new Position(i, n - 1);
            max = current;
        }
        current = 1;
        for (k = i + 1; k < size; k++) {        //по вертикали
            if (!field[k][j].equals(expected)) break;
            current++;
        }
        if (current > max) {
            end = new Position(k - 1, j);
            max = current;
        }
        current = 1;
        for (k = i + 1, n = j - 1; k < size && n >= 0; k++, n--) {  //по диагонали сверху справа влево вниз
            if (!field[k][n].equals(expected)) break;
            current++;
        }
        if (current > max) {
            end = new Position(k - 1, n + 1);
            max = current;
        }
        current = 1;
        for (k = i + 1, n = j + 1; k < size && n < size; k++, n++) {    //по диагонали сверху слева вправо вниз
            if (!field[k][n].equals(expected)) break;
            current++;
        }
        if (current> max) {
            end = new Position(k - 1, n - 1);
            max = current;
        }
        return new ForSpider(max, end);
    }
    private static Position notFound = new Position(-1, -1);
    public List<Position> check(Value expected) {
        int i, j;
        int max = 0;
        ForSpider end;
        List<Position> listI = new ArrayList<>(List.of());  //лист для начала самой длинной последовательности
        List<Position> listJ = new ArrayList<>(List.of());  //лист для конца самой длинной последовательности
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (field[i][j].equals(expected)) {
                    end = spider(i, j, max, expected);
                    if (!end.getEnd().equals(notFound)) {
                        listI.add(new Position(i, j));
                        listJ.add(end.getEnd());
                        max = end.getMax();
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
        }
    }

