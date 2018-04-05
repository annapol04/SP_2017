package com.annapol04.munchkin.engine

import com.annapol04.munchkin.R
import com.annapol04.munchkin.data.EventRepository

import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.Random

import javax.inject.Inject
import javax.inject.Named

class FakeMatch @Inject
constructor(game: Game,
            @Named("myself") myself: Player,
            eventRepository: EventRepository)
    : Match(game, myself, eventRepository) {

    override fun start(amountOfPlayers: Int) {
        super.start(amountOfPlayers)

        for (i in 0 until amountOfPlayers - 1)
            eventRepository.push(Event(Scope.GAME, Action.JOIN_PLAYER, 0, i))
    }

    override fun namingRound() {
        super.namingRound()

        val names = ArrayList(Arrays.asList("Helga", "Cannabiene"))

        val events = ArrayList<Event>((amountOfPlayers - 1) * 2)

        for (i in 1 until amountOfPlayers) {
            events.add(Event(current!!.scope!!, Action.NAME_PLAYER, R.string.ev_join_player, names[i - 1]))
            events.add(Event(current!!.scope!!, Action.HAND_OVER_TOKEN, 0, players[(i + 1) % players.size].scope!!.ordinal))
        }

        eventRepository.push(events)
    }

    @Throws(IllegalEngineStateException::class)
    override fun handOverToken(scope: Scope, playerNr: Int) {
        super.handOverToken(scope, playerNr)

        if (current != myself) {
            if (state == Match.State.HAND_CARDS)
                drawInitialHandcards()
        }
    }
}