package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Petya");

        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldUpdatePlayedTime() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Petya");

        player.installGame(game);

        int expected = 5;
        int actual = player.play(game, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWithNotInstalledGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Крестики - нолики", "Головоломки");

        Player player = new Player("Petya");
        player.installGame(game1);

        assertThrows(RuntimeException.class, () -> player.play(game2, 5));
    }

    @Test
    public void shouldNotAffectIfAlreadyInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Крестики - нолики", "Головоломки");
        Player player = new Player("Petya");
        player.installGame(game);

        player.play(game, 3);
        player.installGame(game);

        int expected = 8;
        int actual = player.play(game, 5);

        assertEquals(expected, actual);
    }

    // другие ваши тесты
}