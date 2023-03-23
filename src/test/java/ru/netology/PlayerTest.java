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
    public void shouldSumGenreIfPlayedFewGameWithSameGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Побег из тюрьмы", "Аркады");
        Game game3 = store.publishGame("Морской бой", "Аркады");
        Game game4 = store.publishGame("Pacman", "Аркады");
        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game1, 2);
        player.play(game3, 5);
        player.play(game2, 6);
        player.play(game4, 2);
        player.play(game1, 5);

        int expected = 20;
        int actual = player.sumGenre("Аркады");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfPlayedGamesWithDifferentGenres() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Судоку", "Логические");
        Game game3 = store.publishGame("Road rash", "Гонки");
        Game game4 = store.publishGame("Mario", "Аркады");
        Game game5 = store.publishGame("Forza Motorsport", "Гонки");
        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game1, 2); //Аркады
        player.play(game2, 6); //Логические
        player.play(game3, 5); //Гонки
        player.play(game4, 2); //Аркады
        player.play(game5, 5); //Гонки
        player.play(game1, 5); //Аркады
        player.play(game5, 5); //Гонки

        int expected = 15;
        int actual = player.sumGenre("Гонки");

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

    @Test
    public void shouldGetMostPlayedGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Судоку", "Логические");
        Game game3 = store.publishGame("Road rash", "Гонки");
        Game game4 = store.publishGame("Mario", "Аркады");
        Game game5 = store.publishGame("Forza Motorsport", "Гонки");
        Game game6 = store.publishGame("Маджонг", "Логические");
        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.installGame(game6);

        player.play(game1, 2); //Аркады
        player.play(game1, 5); //Аркады
        player.play(game4, 2); //Аркады
        player.play(game2, 12); //Логические
        player.play(game6, 5); //Логические
        player.play(game6, 6); //Логические
        player.play(game3, 5); //Гонки
        player.play(game5, 1); //Гонки
        player.play(game5, 2); //Гонки

        Game expected = game2;
        Game actual = player.mostPlayedByGenre("Логические");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnNullIfNotPlayed() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Судоку", "Логические");
        Game game3 = store.publishGame("Road rash", "Гонки");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game2, 12); //Логические
        player.play(game3, 5); //Гонки

        assertNull(player.mostPlayedByGenre("Аркады"));
    }
}