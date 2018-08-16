package com.azkara.hp.azkar.Util;


/**
 * Created by NaderNabil216@gmail.com on 7/16/2018.
 */
public class Constants {
    public class IntentStrings {
        public final static String SHOW_OVERLAY_VIEW = "SHOW_OVERLAY_VIEW";
        public final static String OVERLAY_ZEKR = "OVERLAY_ZEKR";
    }

    public class SharedPreferencesTags {
        public final static String AlarmManagerTime = "AlarmManagerTime";
        public static final String CellsData = "CellsData";
        public static final String AzkarElMoslemFileVersion = "AzkarElMoslemFileVersion";
        public static final String AzkarFileVersion = "AzkarFileVersion";
        public static final String ThemeColor="ThemeColor";
    }

    public class RoomTags {
        public final static String AzkarElMoslemTable = "AzkarElMoslemTable";
        public final static String AzkaryTable = "AzkaryTable";
        public final static String ZekrContentCol = "ZekrContentCol";
        public final static String ZekrCountCol = "ZekrCountCol";
        public final static String ZekrInfoCol = "ZekrInfoCol";
        public final static String ZekrCategory = "ZekrCategory";
        public final static String ZekrId = "ZekrId";

    }

    public class CellsType {
        public static final int TYPE_SALAH = 1;
        public static final int TYPE_ZEKR = 2;
        public static final int TYPE_QOUR2AN = 3;
        public static final int TYPE_SEYAM = 4;
    }

    public class ConstantsValues {
        public static final long NoRepeat = 0;
        public static final long VeryHighRepeat = 3600000;
        public static final long HighRepeat = 3600000 * 2;
        public static final long MediumRepeat = 3600000 * 4;
        public static final long LowRepeat = 3600000 * 6;

        public static final int LightTheme = 1;
        public static final int DarkTheme = 2;
        public static final int GreenTheme = 3;
        public static final int PinkTheme = 4;

    }
}
