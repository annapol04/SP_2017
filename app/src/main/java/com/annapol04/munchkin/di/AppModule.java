package com.annapol04.munchkin.di;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import com.annapol04.munchkin.db.AppDb;
import com.annapol04.munchkin.db.EventDao;
import com.annapol04.munchkin.engine.Event;
import com.annapol04.munchkin.engine.MessageBook;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        AppModuleFlavor.class,
        ViewModelModule.class,
        SignInActivityModule.class,
        MainActivityModule.class,
        PlayDeskActivityModule.class,
})
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    public AppDb provideDb(Application app) {
        return new AppDb();//Room.databaseBuilder(app, AppDb.class, "munchkin_app.db").build();
    }

    @Singleton
    @Provides
    public EventDao provideEventDao(AppDb db) {
        return new EventDao() {
            @Override
            public LiveData<List<Event>> loadEntries() {
                return null;
            }

            @Override
            public void insert(Event event) {

            }
        };//db.eventDao();
    }

}
