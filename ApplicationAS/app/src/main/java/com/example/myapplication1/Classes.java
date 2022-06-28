package com.example.myapplication1;

import java.util.Calendar;

public class Classes {}

class ListJobs {
    private Short id = 1; private static Short CountRecord = 0; public TimeStamp date_start, date_finish; public String Name, Description; private ListJobs NextRecordJob;

    ListJobs() { CountRecord++; id++; date_start = new TimeStamp(); date_finish = new TimeStamp(); Name = Description = ""; NextRecordJob = null; }

    public ListJobs AppendRecord() { if (NextRecordJob == null) { NextRecordJob = new ListJobs(); return NextRecordJob; }
    while(NextRecordJob.NextRecordJob != null) NextRecordJob = NextRecordJob.NextRecordJob; NextRecordJob.NextRecordJob = new ListJobs(); return NextRecordJob.NextRecordJob; }

    public ListJobs GetRecord(Short Index) { if (Index < 0 || Index >= CountRecord) return null; if (Index == 0) return this; ListJobs ListJob = NextRecordJob;
    for (Short i = 1; i < Index; i++) ListJob = ListJob.NextRecordJob; return ListJob; }

    public static Short Get_CountRecord() { return CountRecord; }

//    public ListJobs GetEndRecord() { ListJobs Record = this; while(Record.NextRecordJob != null) Record = Record.NextRecordJob; return Record; }

    public ListJobs[] GetAllRecords() { ListJobs[] List_Jobs = new ListJobs[CountRecord]; ListJobs Record = this;
    for (Short i = 0; i < CountRecord;) { List_Jobs[i++] = Record; Record = Record.NextRecordJob; } return List_Jobs; }

    public String ISRecord(final short[] Array_oClock, final short[] Array_Date) { ListJobs Record = this;
    do { final TimeStamp RecordDS = Record.date_start, RecordDF = Record.date_finish;
        if (Array_Date[0] == RecordDS.GetYear() && Array_Date[1] == RecordDS.GetMonth() && Array_Date[2] == RecordDS.GetDay() && (
        BETWEEN(Array_oClock[0], (short) RecordDS.GetHour(), (short) RecordDF.GetHour()) &&
        BETWEEN(Array_oClock[1], (short) RecordDS.GetMinute(), (short) RecordDF.GetMinute()) ||
        BETWEEN(Array_oClock[2], (short) RecordDS.GetHour(), (short) RecordDF.GetHour()) &&
        BETWEEN(Array_oClock[3], (short) RecordDS.GetMinute(), (short) RecordDF.GetMinute()))) return Record.Name + "\n" + RecordDS.ToString((byte) 3) + " - " + RecordDF.ToString((byte) 2); Record = Record.NextRecordJob; } while (Record != null); return ""; }

    private static Boolean BETWEEN(final Short Value1, final Short Value2, final Short Value3) { if (Value1 >= Value2 && Value1 <= Value3) return true; return false; }

}

class TimeStamp {
    private Byte Day, Month, Hour, Minute, Second; private Short Year;

    TimeStamp() { Day = Month = Hour = Minute = Second = 0; Year = 1970; }

    public TimeStamp SetDay(final Byte Day) { this.Day = Day; return this; }
    public TimeStamp SetMonth(final Byte Month) { this.Month = Month; return this; }
    public TimeStamp SetYear(final Short Year) { this.Year = Year; return this; }
    public TimeStamp SetHour(final Byte Hour) { this.Hour = Hour; return this; }
    public TimeStamp SetMinute(final Byte Minute) { this.Minute = Minute; return this; }
    //public TimeStamp SetSecond(Byte Second) { this.Second = Second; return this; }
    public Byte GetDay() { return Day; } public Byte GetMonth() { return Month; } public Short GetYear() { return Year; }
    public Byte GetHour() { return Hour; } public Byte GetMinute() { return Minute; } //public Byte GetSecond() { return Second; }

    public String ToString(final Byte Switch) {
    switch(Switch) {
        case 1: return DateToStandard(Day) + "-" + DateToStandard(Month) + "-" + Year;
        case 2: return DateToStandard(Hour) + ": " + DateToStandard(Minute);
        case 3: return ToString((byte) 1) + " " + ToString((byte) 2);
    } return ""; }

    public static short[] GetDate_ToDay() { final Calendar ToDayDate = Calendar.getInstance(); return new short[] { (short) ToDayDate.get(Calendar.YEAR), (short) (ToDayDate.get(Calendar.MONTH) + 1), (short) ToDayDate.get(Calendar.DAY_OF_MONTH) }; }

    private String DateToStandard(final Byte Number) { if (Number < 10) return "0" + Number; return Number + ""; }
}

