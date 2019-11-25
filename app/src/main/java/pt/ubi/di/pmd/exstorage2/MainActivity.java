package pt.ubi.di.pmd.exstorage2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private SQLiteDatabase oSQLiteDB;
    private AjudanteParaAbrirBD oAPABD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oAPABD = new AjudanteParaAbrirBD(this);
        oSQLiteDB = oAPABD.getWritableDatabase();

        LinearLayout oLL = (LinearLayout) findViewById(R.id.llsv);
        Cursor oCursor = oSQLiteDB.query(oAPABD.TABLE_NAME,new String[]{"*"},null,null,null,null,null,null);
        boolean bCarryOn = oCursor.moveToFirst();
        while(bCarryOn){
            LinearLayout oLL1= (LinearLayout) getLayoutInflater().inflate(R.layout.line,null);
            oLL1.setId(oCursor.getInt(0)*10+4);
            EditText oED1 = (EditText) oLL1.findViewById(R.id.ED1);
            oED1.setId(oCursor.getInt(0)*10+2);
            oED1.setText(oCursor.getString(1));
            EditText oED2 = (EditText) oLL1.findViewById(R.id.ED2);
            oED1.setId(oCursor.getInt(0)*10+3);
            oED1.setText(oCursor.getInt(2)+"");
            Button oB1 = (Button) oLL1.findViewById(R.id.EDIT);
            oB1.setId(oCursor.getInt(0)*10+1);
            Button oB2 = (Button) oLL1.findViewById(R.id.DEL);
            oB2.setId(oCursor.getInt(0)*10);
            oLL.addView(oLL1);
            bCarryOn = oCursor.moveToNext();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        oAPABD.close();
    }
    public void onINSERTclick(View v){
        ContentValues oCV = new ContentValues();
        EditText oED1 = (EditText) findViewById(R.id.name);
        EditText oED2 = (EditText) findViewById(R.id.year);
        oCV.put(oAPABD.COL2, oED1.getText().toString());
        oCV.put(oAPABD.COL3, oED2.getText().toString());
        oSQLiteDB.insert(oAPABD.TABLE_NAME,null,oCV);
    }
}
