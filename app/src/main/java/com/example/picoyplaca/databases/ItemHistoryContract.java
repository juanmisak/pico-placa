package com.example.picoyplaca.databases;

import android.provider.BaseColumns;

public class ItemHistoryContract {
    public static abstract class ItemHistoryContractEntry implements BaseColumns {
        public static final String HISTORY_ITEM_TABLE = "item_table";

        public static final String ITEM_HISTORY_PLATE = "item_history_plate";
        public static final String ITEM_HISTORY_TIMESTAMP = "item_history_timestamp";
        public static final String ITEM_HISTORY_SENIOR_CITIZEN = "item_history_senior_citizen";
        public static final String ITEM_HISTORY_HANDICAPPED="item_history_handicapped";
        public static final String ITEM_HISTORY_INFRINGEMENT="item_history_infringement";
    }
}
