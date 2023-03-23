package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GameStoreTest {
    GameStore store = new GameStore();
    List<Game> games = new ArrayList<>();

    Map<String, Integer> playedTime = new HashMap<>();

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddFewGames() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Fifa23", "Sports");
        Game game2 = store.publishGame("GTAV", "ActionAdventure");

        assertTrue(store.containsGame(game));
        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
    }

    @Test
    public void shouldAddGameIfNoGames() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game3 = new Game("Stalker", "Shooter", store);

        assertTrue(store.containsGame(game));
        assertFalse(store.containsGame(game3));
    }

    @Test
    public void shouldAddPublishGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertEquals(List.of(game), store.getGames());

    }

    @Test
    public void shouldAddPublishFewGames() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Fifa23", "Sports");
        Game game2 = store.publishGame("GTAV", "ActionAdventure");

        assertEquals(List.of(game, game1, game2), store.getGames());

    }

    @Test
    public void shouldNotAddContainsGamesSameName() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Stalker", "Shooter");
        Game game1 = new Game("Stalker", "Shooter", store);

        assertEquals(List.of(game), store.getGames());
    }

    @Test
    public void shouldAddPlayTime() {

        GameStore store = new GameStore();
        store.playedTime.put("Маша", 3);

        store.addPlayTime("Маша", 5);

        Assertions.assertEquals(8,store.playedTime.get("Маша"));

    }

    @Test
    public void shouldAddPlayTimeFewPlayers() {

        GameStore store = new GameStore();
        store.playedTime.put("Маша", 3);
        store.playedTime.put("Катя", 2);

        store.addPlayTime("Маша", 5);
        store.addPlayTime("Катя", 3);

        Assertions.assertEquals(8, store.playedTime.get("Маша"));
        Assertions.assertEquals (5, store.playedTime.get("Катя"));

    }

    @Test
    public void shouldGetSumPlayedTime() {

        GameStore store = new GameStore();
        store.playedTime.put("Вова", 5);
        store.playedTime.put("Женя", 4);

        Assertions.assertEquals(9, store.getSumPlayedTime());

    }

    @Test
    public void shouldGetMostPlayerIfPlayed1Hour() {
        GameStore store = new GameStore();

        store.addPlayTime("Катя", 1);

        Assertions.assertEquals("Катя", store.getMostPlayer());

    }

    @Test
    public void shouldGetMostPlayer() {
        GameStore store = new GameStore();
        store.addPlayTime("Маша", 5);
        store.addPlayTime("Катя", 3);
        store.addPlayTime("Вова", 4);

        Assertions.assertEquals("Маша", store.getMostPlayer());
    }

    @Test
    public void shouldGetMostPlayerIfNull() {
        GameStore store = new GameStore();

        Assertions.assertEquals(null, store.getMostPlayer());
    }

    @Test
    public void shouldGetMostPlayerIfOnePlayer() {
        GameStore store = new GameStore();
        store.addPlayTime("Маша", 5);

        Assertions.assertEquals("Маша", store.getMostPlayer());
    }

}
