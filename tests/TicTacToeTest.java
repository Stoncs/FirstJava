/*
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TicTacToeTest {

    @Test
    void addZero() {
        TicTacToe game = new TicTacToe(10);
        game.addZero(10,2);
        game.addZero(8, 1);
        game.addZero(1, 3);
        Assert.assertEquals("0", game.field[1][9]);
        Assert.assertEquals("0", game.field[0][7]);
        Assert.assertEquals("0", game.field[2][0]);
        Assert.assertNotEquals("0", game.field[9][1]);
    }

    @Test
    void addCross() {
        TicTacToe game = new TicTacToe(6);
        game.addCross(4, 5);
        game.addCross(4,6);
        game.addCross(1,5);
        Assert.assertEquals("X", game.field[4][3]);
        Assert.assertEquals("X", game.field[5][3]);
        Assert.assertEquals("X", game.field[4][0]);
        Assert.assertNotEquals("X", game.field[3][3]);
    }

    @Test
    void delete() {
        TicTacToe game = new TicTacToe(3);
        game.addCross(1, 3);
        game.addZero(2,2);
        game.addCross(2,1);
        game.delete(2,2);
        Assert.assertEquals("_", game.field[1][1]);
        Assert.assertEquals("X", game.field[2][0]);
        game.delete(1,3);
        Assert.assertEquals("_", game.field[2][0]);
        game.delete(2,1);
        Assert.assertEquals("_", game.field[0][1]);
    }

    @Test
    void checkCross() {
        TicTacToe game1 = new TicTacToe(3);
        Assert.assertEquals(0, game1.checkCross()); //проверка на пустое поле
        game1.addCross(1,1);
        game1.addCross(2,3);
        Assert.assertEquals(1, game1.checkCross()); //макс = 1
        game1.addCross(3,3);
        Assert.assertEquals(2, game1.checkCross()); //макс = 2, по горизонтали
        TicTacToe game2 = new TicTacToe(6);
        game2.addCross(2,2);
        game2.addCross(3,2);
        game2.addCross(5,2);
        game2.addCross(6,2);
        game2.addZero(4,2);
        Assert.assertEquals(2, game2.checkCross()); //по горизонтали
        game2.addCross(4,3);
        game2.addCross(4,4);
        game2.addCross(4,5);
        Assert.assertEquals(3, game2.checkCross()); //по вертикали
        game2.addCross(5,4);
        game2.addCross(6,5);
        Assert.assertEquals(4, game2.checkCross()); //по диагонали слева сверху вправо вниз (второй цикл)
        TicTacToe game3 = new TicTacToe(10);
        game3.addCross(3,10);
        game3.addCross(2,9);
        game3.addCross(1, 8);
        Assert.assertEquals(3, game3.checkCross()); //по диагонали слева сверху вправо вниз (первый цикл)
        game3.addCross(1, 4);
        game3.addCross(2, 3);
        game3.addCross(3, 2);
        game3.addCross(4, 1);
        Assert.assertEquals(4, game3.checkCross()); //по диагонали слева снизу вправо вверх (первый цикл)
        game3.addCross(5, 8);
        game3.addCross(6, 7);
        game3.addCross(7, 6);
        game3.addCross(8, 5);
        game3.addCross(9, 4);
        Assert.assertEquals(5, game3.checkCross()); //по диагонали слева снизу вправо вверх (второй цикл)
    }

    @Test
    void checkZero() {
        TicTacToe game = new TicTacToe(10);
        game.addZero(4,5);
        game.addZero(5,5);
        game.addZero(6,5);
        Assert.assertEquals(3, game.checkZero()); //горизонталь
        game.addZero(6,7);
        game.addZero(6,6);
        game.addZero(6,4);
        Assert.assertEquals(4, game.checkZero()); //вертикаль
        game.delete(6, 5);
        game.addZero(7,9);
        game.addZero(8,8);
        game.addZero(9,7);
        Assert.assertEquals(3, game.checkZero()); //по диагонали слева снизу вправо вверх (второй цикл)
        game.addZero(3,7);
        game.addZero(4,6);
        Assert.assertEquals(4, game.checkZero()); //по диагонали слева снизу вправо вверх (первый цикл)
        game.addZero(7,7);
        game.addZero(4,4);
        Assert.assertEquals(5, game.checkZero()); //по диагонали слева свехру вправо вниз (первый цикл)
        game.addCross(5,5);
        game.addZero(9, 5);
        game.addZero(8, 4);
        game.addZero(7, 3);
        game.addZero(6, 2);
        Assert.assertEquals(4, game.checkZero()); //по диагонали слева свехру вправо вниз (второй цикл)
    }
}*/