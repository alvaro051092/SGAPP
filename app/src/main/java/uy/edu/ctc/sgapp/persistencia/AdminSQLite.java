package uy.edu.ctc.sgapp.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import uy.edu.ctc.sgapp.entidad.Configuracion;
import uy.edu.ctc.sgapp.utiles.Utilidades;

/**
 * Created by alvar on 02/08/2017.
 */

public class AdminSQLite{
    private Utilidades utilidades;
    private static AdminSQLite instancia;
    private String TAG = this.getClass().getSimpleName().toUpperCase();
    private ManejadorDBHelper mDbHelper;
    private static final String TEXT_TYPE           = " TEXT";
    private static final String NUM_TYPE            = " INTEGER";
    private static final String COMMA_SEP           = ",";
    private Context contexto;

    /*CONFIGURACION*/
    private static final String SQL_DELETE_CONFIGURACION = "DROP TABLE IF EXISTS " + configuracionReg.CONFIGURACION_TABLE_NAME;
    private static final String SQL_CREATE_CONFIGURACION =
            "CREATE TABLE " + configuracionReg.CONFIGURACION_TABLE_NAME + " (" +
                    configuracionReg._ID + " INTEGER PRIMARY KEY," +
                    configuracionReg.CONFIGURACION_COL_SESSION_ID + TEXT_TYPE + COMMA_SEP +
                    configuracionReg.CONFIGURACION_COL_PERCOD + TEXT_TYPE +  " )";



    //----------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------
    //MANEJADOR
    //----------------------------------------------------------------------------------------
    private AdminSQLite(Context context) {
        this.contexto   = context;
        mDbHelper       = new ManejadorDBHelper(context);
        utilidades      = Utilidades.GetInstancia();
    }
    //-----------------------------------------------------------------------
    public static final AdminSQLite getInstancia(Context context){
        if (instancia == null)
        {
            instancia = new AdminSQLite(context);
        }

        return  instancia;
    }
    //-----------------------------------------------------------------------
    public class ManejadorDBHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "sgapp.db";

        //----------------------------------------------------------------------------------------

        public ManejadorDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //----------------------------------------------------------------------------------------

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_CONFIGURACION);
        }

        //----------------------------------------------------------------------------------------

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_CONFIGURACION);
            onCreate(db);
        }

        //----------------------------------------------------------------------------------------

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        //----------------------------------------------------------------------------------------
    }
    //<<<<<<----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------
    //ESTRUCTURAS
    //----------------------------------------------------------------------------------------

    /*CONFIGURACION*/
    public static class configuracionReg implements BaseColumns {
        public static final String CONFIGURACION_TABLE_NAME     = "parametro";
        public static final String CONFIGURACION_COL_SESSION_ID = "sessionId";
        public static final String CONFIGURACION_COL_PERCOD     = "perCod";
    }
    //<<<<<----------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------
    //MANTENIMIENTO DE CONFIGURACION
    //----------------------------------------------------------------------------------------
    public long ConfiguracionAgregar(Configuracion configuracion){

        SQLiteDatabase db       = mDbHelper.getWritableDatabase();
        ContentValues values    = new ContentValues();

        values.put(configuracionReg.CONFIGURACION_COL_PERCOD, configuracion.getPerCod());
        values.put(configuracionReg.CONFIGURACION_COL_SESSION_ID, configuracion.getSessionId());

        configuracion.set_ID(db.insert(configuracionReg.CONFIGURACION_TABLE_NAME, null, values));

        return configuracion.get_ID();
    }
    //-----------------------------------------------
    public Configuracion ConfiguracionDevolver(long _ID){

        Configuracion configuracion       = new Configuracion();
        SQLiteDatabase db   = mDbHelper.getReadableDatabase();

        String[] projection = {
                configuracionReg._ID,
                configuracionReg.CONFIGURACION_COL_PERCOD,
                configuracionReg.CONFIGURACION_COL_SESSION_ID
        };

        String selection        = configuracionReg._ID + " = ?";
        String[] selectionArgs  = {Long.toString(_ID)};

        Cursor c = db.query(
                configuracionReg.CONFIGURACION_TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        if (c.moveToFirst()) {

            configuracion.set_ID(c.getLong(c.getColumnIndexOrThrow(configuracionReg._ID)));

            configuracion.setPerCod(c.getString(c.getColumnIndexOrThrow(configuracionReg.CONFIGURACION_COL_PERCOD)));
            configuracion.setSessionId(c.getString(c.getColumnIndexOrThrow(configuracionReg.CONFIGURACION_COL_SESSION_ID)));
        }
        else
        {
            utilidades.MostrarMensajeConsola(TAG, "No se encontro registro");
        }

        return configuracion;
    }
    //-----------------------------------------------
    public void ConfiguracionModificar(Configuracion configuracion){
        SQLiteDatabase db       = mDbHelper.getReadableDatabase();
        ContentValues values    = new ContentValues();

        values.put(configuracionReg.CONFIGURACION_COL_PERCOD, configuracion.getPerCod());
        values.put(configuracionReg.CONFIGURACION_COL_SESSION_ID, configuracion.getSessionId());

        String selection = configuracionReg._ID + " = ?";
        String[] selectionArgs = {Long.toString(configuracion.get_ID())};

        int count = db.update(
                configuracionReg.CONFIGURACION_TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }
    //<<<<<<----------------------------------------------------------------------------------


}
