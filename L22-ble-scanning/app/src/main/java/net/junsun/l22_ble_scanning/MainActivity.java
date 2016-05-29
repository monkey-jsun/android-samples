package net.junsun.l22_ble_scanning;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private int REQUEST_ENABLE_BT = 1;
    private Handler mHandler;
    private static final long SCAN_PERIOD = 10000;
    private BluetoothLeScanner mLEScanner;
    private ScanSettings settings;
    private List<ScanFilter> filters;
    boolean mScanning=false;
    List<MyBleDevice> devices;
    BleDeviceArrayAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devices = new ArrayList<>();
        adaptor = new BleDeviceArrayAdaptor(this, android.R.layout.simple_list_item_2, devices);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adaptor);

        mHandler = new Handler();
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE Not Supported",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        mLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
        settings = new ScanSettings.Builder()
                //.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                .build();
        filters = new ArrayList<ScanFilter>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        if (!mScanning) {
            menu.findItem(R.id.menu_stop).setVisible(false);
            menu.findItem(R.id.menu_scan).setVisible(true);
            menu.findItem(R.id.menu_refresh).setActionView(null);
        } else {
            menu.findItem(R.id.menu_stop).setVisible(true);
            menu.findItem(R.id.menu_scan).setVisible(false);
            menu.findItem(R.id.menu_refresh).setActionView(
                    R.layout.actionbar_indeterminate_progress);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                startScanning();
                break;
            case R.id.menu_stop:
                stopScanning();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            startScanning();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            stopScanning();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Bluetooth not enabled.
                finish();
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startScanning() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopScanning();
            }
        }, SCAN_PERIOD);
        mLEScanner.startScan(filters, settings, mScanCallback);
        mScanning=true;
        devices.clear();
        invalidateOptionsMenu(); // update menu
    }

    private void stopScanning() {
        mLEScanner.stopScan(mScanCallback);
        mScanning=false;
        invalidateOptionsMenu();  // update menu
    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            // Log.i("jsun", result.getScanRecord().getDeviceName() + "," + result.getDevice().getAddress() + "," + result.getRssi());
            MyBleDevice d = new MyBleDevice(
                    result.getScanRecord().getDeviceName(),
                    result.getDevice().getAddress(),
                    result.getRssi()
            );
            if (!devices.contains(d)) {
                devices.add(d);
                adaptor.notifyDataSetChanged();
            }
        }


        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            for (ScanResult sr : results) {
                Log.i("jsun", "ScanResult - Results " + sr.toString());
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            Toast.makeText(getApplicationContext(),"Scan failed, error Code =" + errorCode, Toast.LENGTH_SHORT).show();
        }
    };
}
