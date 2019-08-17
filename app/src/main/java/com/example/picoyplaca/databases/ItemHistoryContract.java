package com.example.picoyplaca.databases;

import android.provider.BaseColumns;

public class ItemHistoryContract {
    public static abstract class ItemHistoryContractEntry implements BaseColumns {
        public static final String HISTORT_ITEM_TABLE = "item_table";

        public static final String ITEM_HISTORT_PLATE = "item_history_plate";
        public static final String ITEM_HISTORT_TIMESTAMP = "item_history_timestamp";
        public static final String ITEM_HISTORT_SENIOR_CITIZEN = "item_history_senior_citizen";
        public static final String ITEM_HISTORT_HANDICAPPED="item_history_handicapped";
    }
}
