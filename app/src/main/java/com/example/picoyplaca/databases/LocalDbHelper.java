package com.example.picoyplaca.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.picoyplaca.models.ItemHistoryObject;

import java.util.ArrayList;
import java.util.List;

public class LocalDbHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="picoyplaca.db";

    private List<ItemHistoryObject> mItemHistoryList;
    private ItemHistoryObject mItemHistoryObject;

    public LocalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ ItemHistoryContract.ItemHistoryContractEntry.HISTORY_ITEM_TABLE+"("
                + ItemHistoryContract.ItemHistoryContractEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_PLATE+ " TEXT,"
                + ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_TIMESTAMP+" TEXT,"
                + ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_SENIOR_CITIZEN+" INTEGER DEFAULT -1,"
                + ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_HANDICAPPED+" INTEGER DEFAULT -1,"
                + ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_INFRINGEMENT+" INTEGER DEFAULT -1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addItemToHistory(ItemHistoryObject mItemHistoryObject){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_PLATE,mItemHistoryObject.getPlate());
        values.put(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_TIMESTAMP,mItemHistoryObject.getTimestamp());
        values.put(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_SENIOR_CITIZEN,mItemHistoryObject.isSenior_citizen());
        values.put(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_HANDICAPPED,mItemHistoryObject.isHandicapped());

        sqLiteDatabase.insert(ItemHistoryContract.ItemHistoryContractEntry.HISTORY_ITEM_TABLE,null,values);
    }

    public List<ItemHistoryObject> getItemHistoryList(String plate) {
        mItemHistoryList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String queryGetAll = "SELECT * FROM " + ItemHistoryContract.ItemHistoryContractEntry.HISTORY_ITEM_TABLE+
                " WHERE " + ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_PLATE+ " = " +plate;

        Cursor c=sqLiteDatabase.rawQuery(queryGetAll,null);
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do {
                mItemHistoryObject=new ItemHistoryObject(
                        c.getString(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_PLATE)),
                        c.getString(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_TIMESTAMP)),
                        c.getInt(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_SENIOR_CITIZEN)),
                        c.getInt(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_HANDICAPPED)),
                        c.getInt(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_INFRINGEMENT))
                );
                mItemHistoryList.add(mItemHistoryObject);

            } while (c.moveToNext());

        }
        return mItemHistoryList;
    }

    public List<ItemHistoryObject> getItemHistoryList() {
        mItemHistoryList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String queryGetAll = "SELECT * FROM " + ItemHistoryContract.ItemHistoryContractEntry.HISTORY_ITEM_TABLE;

        Cursor c=sqLiteDatabase.rawQuery(queryGetAll,null);
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do {
                mItemHistoryObject=new ItemHistoryObject(
                        c.getString(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_PLATE)),
                        c.getString(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_TIMESTAMP)),
                        c.getInt(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_SENIOR_CITIZEN)),
                        c.getInt(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_INFRINGEMENT)),
                        c.getInt(c.getColumnIndex(ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_HANDICAPPED))
                );
                mItemHistoryList.add(mItemHistoryObject);

            } while (c.moveToNext());

        }
        return mItemHistoryList;
    }

    public int count(String plate) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String queryGetAll = " SELECT * FROM " + ItemHistoryContract.ItemHistoryContractEntry.HISTORY_ITEM_TABLE+" WHERE " + ItemHistoryContract.ItemHistoryContractEntry.ITEM_HISTORY_PLATE+ " LIKE " + "'"+plate+"'";

        Cursor c = sqLiteDatabase.rawQuery(queryGetAll, null);
        return c.getCount();
    }
}
