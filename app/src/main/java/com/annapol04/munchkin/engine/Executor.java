package com.annapol04.munchkin.engine;

import android.util.Log;

import com.annapol04.munchkin.data.EventRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Executor implements EventRepository.OnNewEventListener {
    private static final String TAG = Executor.class.getSimpleName();
    private final Game game;
    private final EventRepository repository;

    @Inject
    public Executor(Game game, EventRepository repository) {
        this.game = game;
        this.repository = repository;

        this.repository.setNewEventListener(this);
    }

    @Override
    public void onNewEvent(Event event) {
        Log.d(TAG, "executing event: " + event.getId());
        event.execute(game);
    }
}