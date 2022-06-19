package com.example.myapplication1;

import android.util.EventLogTags;

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

    public Boolean ISRecord(final Short[] Array_oClock, final Short[] Array_Date) { ListJobs[] List_Jobs = new ListJobs[CountRecord]; ListJobs Record = this;
    for (Short i = 0; i < CountRecord;) { List_Jobs[i++] = Record; Record = Record.NextRecordJob; } return true; }

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
        case 1: return DateToStandart(Day) + "-" + DateToStandart(Month) + "-" + Year;
        case 2: return DateToStandart(Hour) + ": " + DateToStandart(Minute);
        case 3: return ToString((byte) 1) + " " + ToString((byte) 2);
    } return ""; }

    private String DateToStandart(final Byte Number) { if (Number < 10) return "0" + Number; return Number + ""; }
}
