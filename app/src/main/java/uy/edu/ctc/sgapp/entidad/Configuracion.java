package uy.edu.ctc.sgapp.entidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by alvar on 02/08/2017.
 */

public class Configuracion {
    private Long _ID;
    private String PerCod;
    private String SessionId;

    public Configuracion() {
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getPerCod() {
        return PerCod;
    }

    public void setPerCod(String perCod) {
        PerCod = perCod;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }
}
