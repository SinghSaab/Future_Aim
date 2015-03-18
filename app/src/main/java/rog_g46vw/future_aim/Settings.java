package rog_g46vw.future_aim;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Settings extends ListActivity {

    String settingItems[] = {"Set Password Toggle", "option2", "option3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setListAdapter(new ArrayAdapter<String>(Settings.this, android.R.layout.simple_list_item_1, settingItems));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String optionSelected = settingItems[position] ;

        try {
            if(position == 0) {
                Class passwordToggle = Class.forName("rog_g46vw.future_aim." + optionSelected);
                Intent openClass = new Intent(Settings.this, passwordToggle);
                startActivity(openClass);
            }else if(position == 1)
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
