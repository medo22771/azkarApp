package com.azkara.hp.azkar.Util;


import android.app.AlarmManager;

import java.util.Calendar;

/**
 * Created by NaderNabil216@gmail.com on 7/16/2018.
 */
public class Constants {
    public class IntentStrings {
        public final static String SHOW_OVERLAY_VIEW = "SHOW_OVERLAY_VIEW";
        public final static String OVERLAY_ZEKR = "OVERLAY_ZEKR";
        public static final String ZekrNotification="ZekrNotification";
        public static final String CategoryId = "CategoryId";
        public static final String CategoryName = "CategoryName";
        public static final String CategoryPosition = "CategoryPosition";
        public static final String SebhaZekr = "SebhaZekr";
        public static final String ZekrType="ZekrType";

    }

    public class SharedPreferencesTags {
        public final static String AlarmManagerTime = "AlarmManagerTime";
        public static final String CellsData = "CellsData";
        public static final String AzkarElMoslemFileVersion = "AzkarElMoslemFileVersion";
        public static final String AzkarFileVersion = "AzkarFileVersion";
        public static final String ThemeColor = "ThemeColor";
        public static final String AzkarElMoslem = "AzkarElMoslem";
        public static final String Azkary = "Azkary";
        public static final String AzkarSebha = "AzkarSebha";
        public static final String AzkarElMoslemFontSize = "AzkarElMoslemFontSize";
        public static final String AzkaryFontSize = "AzkaryFontSize";
        public static final String SelectedFont = "SelectedFont";
        public static final String SebhaCount = "SebhaCount";
        public static final String SebhaVibrate = "SebhaVibrate";
        public static final String ZekrDisappear = "ZekrDisappear";
        public static final String AzkarVibrate = "AzkarVibrate";
        public static final String AzkarSabahTime = "AzkarSabahTime";
        public static final String AzkarMasaaTime = "AzkarMasaaTime";
        public static final String AzkarNoomTime = "AzkarNoomTime";
        public static final String OverlayCalendar="OverlayCalendar";
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
        public static final int NoRepeat = 0;
        public static final int VeryHighRepeat = 1 ;
        public static final int HighRepeat = 2;
        public static final int MediumRepeat = 4;
        public static final int LowRepeat = 6;

        public static final int LightTheme = 1;
        public static final int DarkTheme = 2;
        public static final int GreenTheme = 3;
        public static final int PinkTheme = 4;

        public static final int SmallFont = 20;
        public static final int MediumFont = 24;
        public static final int LargeFont = 28;


    }

    public class AzkarCategories {
        public static final int cat1 = 1;
        public static final int cat2 = 2;
        public static final int cat3 = 3;
        public static final int cat4 = 4;
        public static final int cat5 = 5;
        public static final int cat6 = 6;
        public static final int cat7 = 7;
        public static final int cat8 = 8;
        public static final int cat9 = 9;
        public static final int cat10 = 10;
        public static final int cat11 = 11;
        public static final int cat12 = 12;
        public static final int cat13 = 13;
        public static final int cat14 = 14;
        public static final int cat15 = 15;
        public static final int cat16 = 16;
        public static final int cat17 = 17;
        public static final int cat18 = 18;
    }
}
