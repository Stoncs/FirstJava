package tictactoegame;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private Box[][] field;
    private int size;
    public TicTacToe(int n){
        size = n;
        int i, j;
        field = new Box[n][n];
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j)
            field[i][j] = Box.E;
        }
    }

    @Override
    public String toString() {
        int i, j;
        for (i = 0; i < size; ++i) {
            for (j = 0; j < size; ++j)
                System.out.print(field[i][j] + "  ");
            System.out.println();
        }
        return null;
    }

    public Box returnValue (int i, int j) {
        return field[i][j];
    }

    public void addZero(int i, int j) {
        field[i][j] = Box.O;
    }

    public void addCross(int i, int j) {
        field[i][j] = Box.X;
    }

    public void delete(int i, int j) {
        field[i][j] = Box.E;
    }

    private static class ForSpider{     //вложенный класс, чтобы вернуть два значения разных типов
        final int max;
        final Position end;

        public ForSpider(int max, Position end) {
            this.max = max;
            this.end = end;
        }
    }
    private ForSpider control(int i, int k, int j, int n, Box expected, int max, Position end) {
        int row, column;
        int current = 1;
        for (row = i, column = j; (row < size && row >=0) && (column < size && column >= 0); row += k, column += n) {  //по диагонали сверху справа влево вниз
            if (!field[row][column].equals(expected)) break;
            current++;
        }
        if (current > max) {
            end = new Position(row - k, column - n);
            max = current;
        }
        return new ForSpider(max, end);
    }

    private ForSpider spider(int i, int j, int max, Box expected) {        //ищет последовательности во всех направлениях
        int k, n;
        Position end = notFound;
        int current = 1;
        ForSpider operation;
        operation = control(i, 0, j + 1, 1, expected, max, end);        //по горизонтали
        if (operation.max > max) {
            max = operation.max;
            end = operation.end;
        }
        operation = control(i + 1, 1, j, 0, expected, max, end);        //по вертикали
        if (operation.max > max) {
            max = operation.max;
            end = operation.end;
        }
        operation = control(i + 1, 1, j - 1, -1, expected, max, end);       //по диагонали сверхну справа влево вниз
        if (operation.max > max) {
            max = operation.max;
            end = operation.end;
        }
        operation = control(i + 1, 1, j + 1, 1, expected, max, end);        //по диагонали сверху слева вправо вниз
        if (operation.max > max) {
            max = operation.max;
            end = operation.end;
        }
        return new ForSpider(max, end);
    }

    private static Position notFound = new Position(-1, -1);

    public List<Position> check(Box expected) {
        int i, j;
        int max = 0;
        ForSpider end;
        List<Position> listI = new ArrayList<>(List.of());  //лист для начала самой длинной последовательности
        List<Position> listJ = new ArrayList<>(List.of());  //лист для конца самой длинной последовательности
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (field[i][j].equals(expected)) {
                    end = spider(i, j, max, expected);
                    if (!end.end.equals(notFound)) {
                        listI.add(new Position(i, j));
                        listJ.add(end.end);
                        max = end.max;
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
        game.toString();
        }
    }

