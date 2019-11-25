package pt.ubi.di.pmd.exstorage2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aluno on 25/11/2019.
 */

public class AjudanteParaAbrirBD extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "FavoriteMovies";
    protected static final String TABLE_NAME = "Movie";

    protected static final String COL1 = "id";
    protected static final String COL2 = "name";
    protected static final String COL3 = "year";

    private static final String CREATE_MOVIE = "CREATE TABLE" + TABLE_NAME + " (" +
            COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL2 + " VARCHAR(50) NOT NULL" +
            COL3 + " INTEGER NOT NULL";
    public AjudanteParaAbrirBD(Context context) {
        super(context, DB_NAME , null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME + ";");
        db.execSQL(CREATE_MOVIE);
    }
}
