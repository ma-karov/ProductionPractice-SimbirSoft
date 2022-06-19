package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final ListJobs List_Jobs = new ListJobs(); private Boolean IS_OneElement_ListJobs = false; private final short[] Array_Date = { 1970, 0, 0 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CalendarView Calendar_View = findViewById(R.id.CalendarView1);
        Calendar_View.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int Year, int Month, int Day) {
            Array_Date[0] = (short) Year; Array_Date[1] = (short) ++Month; Array_Date[2] = (short) Day; } } );

    }

    public void Close_KeyBoard(final View view) {
    final InputMethodManager Manager_KeyBoard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    if (Manager_KeyBoard != null) {
        Manager_KeyBoard.hideSoftInputFromWindow(findViewById(R.id.EditText1).getWindowToken(), 0);
        Manager_KeyBoard.hideSoftInputFromWindow(findViewById(R.id.TextBox_Time).getWindowToken(), 0); } }

    public void CalendarView_Click(View view) {

    }

    public void Button1_Click(View view) { /*findViewById(R.id.Button_GetInformation).setEnabled(true);*/ GetData_CalendarView(); }

//    public void ButtonGetInformation_Click(View view) {  }

    private String[] CorrectEnterClock(String Content_EditTextView) { Content_EditTextView += ":\0"; final Byte DimensionArray = 5; final String[] ArrayString_Clock = new String[DimensionArray]; String StringNumber = new String(); Byte i = 1, Index = 0;
    for (char Char = Content_EditTextView.charAt(0); Char != '\0'; Char = Content_EditTextView.charAt(i++)) { if ((Char == ':' || Char == ' ') ) {
    if (Index == DimensionArray) { ArrayString_Clock[Index - 1] = Index + ""; break; } if (StringNumber != "") { ArrayString_Clock[Index++] = StringNumber; ArrayString_Clock[DimensionArray - 1] = Index + ""; StringNumber = ""; } }
    else if (Char != '-') StringNumber += Char; } return ArrayString_Clock; }

    private short StringToNumber(String StringValue) { StringValue += "\0"; Byte i = 1; short NumberValue = 0;
    for (char Char = StringValue.charAt(0); Char != '\0'; Char = StringValue.charAt(i++)) NumberValue = (short) ((Char - '0' ) + NumberValue*10); return NumberValue; }

    private void ShowPopAp() { final Toast PopAp = Toast.makeText(getApplicationContext(), "Неверно введены данные. Проверьте их", Toast.LENGTH_SHORT); PopAp.setGravity(Gravity.CENTER, 0, 0); PopAp.show(); }

    private void GetData_CalendarView() { final EditText EditTextView_NameJob = findViewById(R.id.EditText1), TextBox_Time = findViewById(R.id.TextBox_Time), EditTextView_DescriptionJob = findViewById(R.id.EditTextTextMultiLine1);
    final String[] String_oClock = CorrectEnterClock(TextBox_Time.getText() + ""); final String Name_Job = EditTextView_NameJob.getText() + "", Description_Job = EditTextView_DescriptionJob.getText() + "";
    if (CorrectStandardClock(String_oClock) && Name_Job != "" && Description_Job != "") { ListJobs ListJob;

    if (IS_OneElement_ListJobs) { final ListJobs Append_ListJob = List_Jobs.AppendRecord();
    Append_ListJob.date_start.SetHour((byte) StringToNumber(String_oClock[0])).SetMinute((byte) StringToNumber(String_oClock[1]));
    Append_ListJob.date_finish.SetHour((byte) StringToNumber(String_oClock[2])).SetMinute((byte) StringToNumber(String_oClock[3]));
    Append_ListJob.date_start.SetYear(Array_Date[0]).SetMonth((byte) Array_Date[1]).SetDay((byte) Array_Date[2]);
    Append_ListJob.date_finish.SetYear(Array_Date[0]).SetMonth((byte) Array_Date[1]).SetDay((byte) Array_Date[2]); Append_ListJob.Name = Name_Job; List_Jobs.Description = Description_Job; ListJob = Append_ListJob; } else { IS_OneElement_ListJobs = true;
    List_Jobs.date_start.SetHour((byte) StringToNumber(String_oClock[0])).SetMinute((byte) StringToNumber(String_oClock[1]));
    List_Jobs.date_finish.SetHour((byte) StringToNumber(String_oClock[2])).SetMinute((byte) StringToNumber(String_oClock[3]));
    List_Jobs.date_start.SetYear(Array_Date[0]).SetMonth((byte) Array_Date[1]).SetDay((byte) Array_Date[2]);
    List_Jobs.date_finish.SetYear(Array_Date[0]).SetMonth((byte) Array_Date[1]).SetDay((byte) Array_Date[2]); List_Jobs.Name = Name_Job; List_Jobs.Description = Description_Job; ListJob = List_Jobs; } //Array_Date[0] = 1970; Array_Date[1] = 0; Array_Date[2] = 0;

    final String[] List = { ListJob.Name, ListJob.date_start.ToString((byte) 3) };
//    for (final ListJobs ListJob : List_Jobs.GetAllRecords()) { List[i++] = ListJob.Name; List[i++] = ListJob.Description; }

    AppendRecordInTable(List); } else ShowPopAp();
//    EditTextView_DescriptionJob.setText(1 + ""); }
//    Log.d("Calendar", "Job");
    }

    private void AppendRecordInTable(final String[] Content_TextViews) { final Short CountRecord = ListJobs.Get_CountRecord(); if (CountRecord > 9) { final ConstraintLayout MainConstraintLayout = findViewById(R.id.MainConstraintLayout); MainConstraintLayout.getLayoutParams().height += 40; }
    final TableLayout Table = findViewById(R.id.TableLayout1); final TableRow Table_Row = new TableRow(this); Table_Row.setPadding(5, 5, 5, 5);
    for (final String Content_TextView : Content_TextViews) { final TableRow.LayoutParams Params_TextView = new TableRow.LayoutParams(50, 50, 1.0f); Params_TextView.gravity = Gravity.CENTER;
    final TextView Text_View = new TextView(this); Text_View.setText(Content_TextView); Text_View.setLayoutParams(Params_TextView); Table_Row.addView(Text_View); } Table.addView(Table_Row);
        Table_Row.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) { final TableLayout TableL = findViewById(R.id.TableLayout1); final Short SelectedRow = (short) TableL.indexOfChild(v);
        final Intent SendMessage = new Intent(MainActivity.this, ContentTableRow_Activity.class);
        final ListJobs SelectedJob = List_Jobs.GetRecord((short)(SelectedRow - 1));
        SendMessage.putExtra("Name_Job", SelectedJob.Name); SendMessage.putExtra("Description_Job", SelectedJob.Description);
        SendMessage.putExtra("StartJob", SelectedJob.date_start.ToString((byte) 3)); SendMessage.putExtra("EndJob", SelectedJob.date_finish.ToString((byte) 3));
        startActivity(SendMessage); } });
    }

    private Boolean CorrectStandardClock(final String[] String_oClock) { if (StringToNumber(String_oClock[4]) == 4 && StringToNumber(String_oClock[0]) <= StringToNumber(String_oClock[2]) && StringToNumber(String_oClock[1]) < StringToNumber(String_oClock[3])) return true; return false; }

}
