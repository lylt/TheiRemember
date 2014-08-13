package com.example.iremember;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.os.Build;

public class MainActivity extends Activity {
	EditText edtTittle;
	EditText edtBody;
	Button btnCreate;
	private MySQLiteOpenHelper dataHelper;
	private Cursor cusor;
	private SimpleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dataHelper=new MySQLiteOpenHelper(MainActivity.this);
		edtTittle=(EditText) findViewById(R.id.edtTittle);
		edtBody=(EditText) findViewById(R.id.edtBody);
		btnCreate=(Button) findViewById(R.id.btnCreate);
		btnCreate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String tittle=edtTittle.getText().toString();
				String body=edtBody.getText().toString();
				Record r= new Record(tittle,body);
				dataHelper.INSERT_RECORD(r);
				dataHelper.close();
				/// I love you
			}
		});
	}

}
