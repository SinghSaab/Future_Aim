package rog_g46vw.future_aim;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    boolean FirstTimeRun = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//This piece of code with turn application run full screen and change manifest file as well.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final Button ctOS_home_button_exit = (Button) findViewById(R.id.button);
        ctOS_home_button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
        Toast toast = Toast.makeText(this,"Welcome to ctOS ", Toast.LENGTH_SHORT);
        toast.show();
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,200);







    }

    public static String getCurrentSsid(Context context) {
        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
            }
        }
        return ssid;
        //Toast.makeText(this, ssid, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(FirstTimeRun == true) {
            Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
        }
        else {
            FirstTimeRun = true;
        }
          Toast.makeText(this, "Welcome back"+getCurrentSsid(this), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
            Intent i = new Intent(this,Settings.class);
            startActivity(i);
            //Intents are basically used to call another activity (either from same or different app. for same app, we use             explicit intents (like done here) and for different app like accessing email or maps, we'll use explicit                 intents

            return true;
        }
        else if(id == R.id.action_battery_stat) {

            if(item.isChecked() == true){
                item.setChecked(!item.isChecked());
                Toast.makeText(this, "Un-checked", Toast.LENGTH_SHORT).show();
            }
            else {
                item.setChecked(!item.isChecked());
                Intent batteryStatus = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));;
                int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                float batteryPct = (level / (float)scale)*100;
                Toast.makeText(this, "Battery Level: "+batteryPct+"%", Toast.LENGTH_SHORT).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    public void process_mail_msg_map(View view) {
        Intent intent=null;
        Intent chooser=null;

        if(view.getId() == R.id.button1)

        {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:45.357115,-75.800148"));
            chooser=Intent.createChooser(intent, "Choose an app to continue");
            startActivity(chooser);
        }

        if(view.getId() == R.id.button2)
        {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String sendto[]={"add_email@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, sendto);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "Content of the email");
            intent.setType("message/rfc822");
            chooser=Intent.createChooser(intent, "Choose an app to continue");
            startActivity(chooser);
        }

        if(view.getId() == R.id.button3)
        {
            intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:+16138904176"));
            intent.putExtra("sms_body", "Heloz");
            chooser=Intent.createChooser(intent, "Choose an app to continue");
            startActivity(chooser);
        }
    }
}
