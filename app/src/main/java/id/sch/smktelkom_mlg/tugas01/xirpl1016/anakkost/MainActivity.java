package id.sch.smktelkom_mlg.tugas01.xirpl1016.anakkost;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {

    EditText etnama;
    EditText etalamat;
    EditText etjumlah;
    Spinner spmakanan;
    TextView tvHasil;
    RadioButton deliver;
    RadioGroup rgStatus;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spmakanan = (Spinner) findViewById(R.id.makanan);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        etnama = (EditText) findViewById(R.id.pemesan);
        etalamat = (EditText) findViewById(R.id.alamat);
        etjumlah = (EditText) findViewById(R.id.jumlah);
        deliver = (RadioButton) findViewById(R.id.delivery);

        findViewById(R.id.buttonPesan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void doProcess() {

        if (isvalid()) {

            int ju = etjumlah.getText().toString().isEmpty() ? 0 : Integer.parseInt(etjumlah.getText().toString());
            int hitung = 5000 * ju;
            String nama = etnama.getText().toString();
            tvHasil.setText("Terimakasih " + nama + " Telah memesan " + spmakanan.getSelectedItem().toString() + " Kres di Kriyuk Syek" + "\n"
                    + "Silahkan Anda membayar " + hitung);
        }

    }

    private boolean isvalid() {

        boolean valid = true;


        String nama = etnama.getText().toString();
        String alamat = etalamat.getText().toString();
        int harga = etjumlah.getText().toString().isEmpty() ? 0 : Integer.parseInt(etjumlah.getText().toString());

        if (harga == 0) {
            etjumlah.setError("Jumlah harus diisi");
            valid = false;
        }

        if (deliver.isChecked()) {
            if (alamat.isEmpty()) {
                etalamat.setError("Alamat Belum diisi");
                valid = false;
            } else {
                etalamat.setError(null);
            }

        }

        if (nama.isEmpty()) {
            etnama.setError("Nama Belum diisi");
            valid = false;
        } else {
            etnama.setError(null);
        }
        return valid;
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


