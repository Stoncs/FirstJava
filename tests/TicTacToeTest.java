import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tictactoegame.TicTacToe;
import tictactoegame.Value;

import java.util.List;

class TicTacToeTest {

    @Test
    void addZero() {
        TicTacToe game = new TicTacToe(10);
        game.addZero(1, 9);
        game.addZero(0, 7);
        game.addZero(2, 0);
        Assert.assertEquals(Value.O, game.returnValue(1, 9));
        Assert.assertEquals(Value.O, game.returnValue(0, 7));
        Assert.assertEquals(Value.O, game.returnValue(2, 0));
        Assert.assertNotEquals(Value.O, game.returnValue(9, 1));
    }

    @Test
    void addCross() {
        TicTacToe game = new TicTacToe(6);
        game.addCross(4, 3);
        game.addCross(5, 3);
        game.addCross(4, 0);
        Assert.assertEquals(Value.X, game.returnValue(4, 3));
        Assert.assertEquals(Value.X, game.returnValue(5, 3));
        Assert.assertEquals(Value.X, game.returnValue(4, 0));
        Assert.assertNotEquals(Value.X, game.returnValue(3, 3));
    }

    @Test
    void delete() {
        TicTacToe game = new TicTacToe(3);
        game.addCross(2, 0);
        game.addZero(1, 1);
        game.addCross(0, 1);
        game.delete(1, 1);
        Assert.assertEquals(Value.E, game.returnValue(1, 1));
        Assert.assertEquals(Value.X, game.returnValue(2, 0));
        game.delete(2, 0);
        Assert.assertEquals(Value.E, game.returnValue(2, 0));
        game.delete(0, 1);
        Assert.assertEquals(Value.E, game.returnValue(0, 1));
    }

    @Test
    void check() {
        TicTacToe game1 = new TicTacToe(3);
        Assert.assertEquals(List.of(), game1.check(Value.X)); //проверка на пустое поле
        game1.addCross(0, 0);
        game1.addCross(2, 1);
        Assert.assertEquals("[(0; 0), (0; 0)]", game1.check(Value.X).toString()); //макс = 1
        game1.addCross(2, 2);
        Assert.assertEquals("[(2; 1), (2; 2)]", game1.check(Value.X).toString()); //макс = 2, по горизонтали
        TicTacToe game2 = new TicTacToe(6);
        game2.addCross(1, 1);
        game2.addCross(1, 2);
        game2.addCross(1, 4);
        game2.addCross(1, 5);
        game2.addZero(1, 3);
        Assert.assertEquals("[(1; 1), (1; 2)]", game2.check(Value.X).toString()); //по горизонтали
        game2.addCross(2, 3);
        game2.addCross(3, 3);
        game2.addCross(4, 3);
        Assert.assertEquals("[(2; 3), (4; 3)]", game2.check(Value.X).toString()); //по вертикали
        game2.addCross(3, 4);
        game2.addCross(4, 5);
        Assert.assertEquals("[(1; 2), (4; 5)]", game2.check(Value.X).toString()); //по диагонали слева сверху вправо вниз (второй цикл)
        TicTacToe game3 = new TicTacToe(10);
        game3.addCross(9, 2);
        game3.addCross(8, 1);
        game3.addCross(7, 0);
        Assert.assertEquals("[(7; 0), (9; 2)]", game3.check(Value.X).toString()); //по диагонали слева сверху вправо вниз (первый цикл)
        game3.addCross(3, 0);
        game3.addCross(2, 1);
        game3.addCross(1, 2);
        game3.addCross(0, 3);
        Assert.assertEquals("[(0; 3), (3; 0)]", game3.check(Value.X).toString()); //по диагонали слева снизу вправо вверх (первый цикл)
        game3.addCross(7, 4);
        game3.addCross(6, 5);
        game3.addCross(5, 6);
        game3.addCross(4, 7);
        game3.addCross(3, 8);
        Assert.assertEquals("[(3; 8), (7; 4)]", game3.check(Value.X).toString()); //по диагонали слева снизу вправо вверх (второй цикл)
        TicTacToe game4 = new TicTacToe(10);
        game4.addZero(4, 3);
        game4.addZero(4, 4);
        game4.addZero(4, 5);
        Assert.assertEquals("[(4; 3), (4; 5)]", game4.check(Value.O).toString()); //горизонталь
        game4.addZero(6, 5);
        game4.addZero(5, 5);
        game4.addZero(3, 5);
        Assert.assertEquals("[(3; 5), (6; 5)]", game4.check(Value.O).toString()); //вертикаль
        game4.delete(4, 5);
        game4.addZero(8, 6);
        game4.addZero(7, 7);
        game4.addZero(6, 8);
        Assert.assertEquals("[(6; 8), (8; 6)]", game4.check(Value.O).toString()); //по диагонали слева снизу вправо вверх (второй цикл)
        game4.addZero(6, 2);
        game4.addZero(5, 3);
        Assert.assertEquals("[(3; 5), (6; 2)]", game4.check(Value.O).toString()); //по диагонали слева снизу вправо вверх (первый цикл)
        game4.addZero(6, 6);
        game4.addZero(3, 3);
        Assert.assertEquals("[(3; 3), (7; 7)]", game4.check(Value.O).toString()); //по диагонали слева свехру вправо вниз (первый цикл)
        game4.addCross(4, 4);
        game4.addZero(4, 8);
        game4.addZero(3, 7);
        game4.addZero(2, 6);
        game4.addZero(1, 5);
        Assert.assertEquals("[(1; 5), (4; 8)]", game4.check(Value.O).toString()); //по диагонали слева свехру вправо вниз (второй цикл)
    }
}