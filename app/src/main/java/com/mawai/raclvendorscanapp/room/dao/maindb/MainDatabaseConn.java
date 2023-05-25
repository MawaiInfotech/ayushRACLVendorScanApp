package com.mawai.raclvendorscanapp.room.dao.maindb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mawai.raclvendorscanapp.jobchallan.model.JobChallanModel;
import com.mawai.raclvendorscanapp.room.dao.dao.AssemblyChallanDao;

@Database(entities = {JobChallanModel.class}, version = 1, exportSchema = false)
public abstract class MainDatabaseConn extends RoomDatabase {

    public abstract AssemblyChallanDao assemblyScanDao();

    private static MainDatabaseConn INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.

        }
    };

//    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE users "
//                    + " ADD COLUMN last_update INTEGER");
//        }};
//
//    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            // Create the new table
//            database.execSQL(
//                    "CREATE TABLE users_new (userid TEXT, username TEXT, last_update INTEGER, " +
//                            "PRIMARY KEY(userid))");// Copy the data
//            database.execSQL(
//                    "INSERT INTO users_new (userid, username, last_update) SELECT userid, username," +
//                            " last_update FROM users");// Remove the old table
//            database.execSQL("DROP TABLE users");// Change the table name to the correct one
//            database.execSQL("ALTER TABLE users_new RENAME TO users");
//        }
//    };
//
//    static final Migration MIGRATION_1_4 = new Migration(1, 4) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            // Create the new table
//            database.execSQL(
//                    "CREATE TABLE users_new (userid TEXT, username TEXT, last_update INTEGER, PRIMARY KEY(userid))");
//
//            // Copy the data
//            database.execSQL(
//                    "INSERT INTO users_new (userid, username, last_update) SELECT userid, username, last_update FROM users");// Remove the old table
//            database.execSQL("DROP TABLE users");// Change the table name to the correct one
//            database.execSQL("ALTER TABLE users_new RENAME TO users");
//        }
//    };

    public static MainDatabaseConn getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (MainDatabaseConn.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context, MainDatabaseConn.class, "RACL_DATABASE")
                            .fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;

    }
}
