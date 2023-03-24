package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GameStoreTest {

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
        Map<String, Integer> time = new HashMap<>();
        time.put("Маша", 5);
        store.addPlayTime("Маша", 5);

        assertEquals(time, store.getPlayedTime());

    }

    @Test
    public void shouldAddPlayTimeFewPlayers() {

        GameStore store = new GameStore();
        Map<String, Integer> time = new HashMap<>();

        time.put("Маша", 3);
        time.put("Катя", 2);

        store.addPlayTime("Маша", 3);
        store.addPlayTime("Катя", 2);

        assertEquals(time, store.getPlayedTime());

    }

    @Test
    public void shouldGetSumPlayedTime() {

        GameStore store = new GameStore();
        Map<String, Integer> time = new HashMap<>();
        store.addPlayTime("Вова", 5);
        store.addPlayTime("Вова", 4);
        time.put("Вова", 9);

        Assertions.assertEquals(9, store.getSumPlayedTime());

    }

    @Test
    public void shouldSumPlayedTimePlayers() {
        GameStore store = new GameStore();
        Map<String, Integer> PlayedTime = new HashMap<>();
        Game game = store.publishGame("Нетология Батл Онлайн", "Аркады");

        store.addPlayTime("Маша", 5);
        store.addPlayTime("Катя", 3);
        store.addPlayTime("Вова", 4);


        Assertions.assertEquals(12, store.getSumPlayedTime());
    }

    @Test
    public void shouldGetMostPlayerIfPlayed1Hour() {
        GameStore store = new GameStore();
        Map<String, Integer> PlayedTime = new HashMap<>();

        store.addPlayTime("Катя", 1);
        String expected = ("Катя");

        Assertions.assertEquals("Катя", store.getMostPlayer());

    }

    @Test
    public void shouldGetMostPlayer() {
        GameStore store = new GameStore();
        Map<String, Integer> PlayedTime = new HashMap<>();
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

