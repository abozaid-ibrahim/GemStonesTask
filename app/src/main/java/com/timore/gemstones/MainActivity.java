package com.timore.gemstones;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView elementsRV;
    private GemStonesRVAdapter elementsAdapter;
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText elementET = (EditText) findViewById(R.id.insertElement_et);
        resultTv = (TextView) findViewById(R.id.result_tv);
        resultTv.setText("Gem Elements is: " + "\n Output: " + 0);

        elementsRV = (RecyclerView) findViewById(R.id.gemElements_recyclerview);
        elementsRV.setLayoutManager(new LinearLayoutManager(this));
        elementsAdapter = new GemStonesRVAdapter(new ArrayList<String>());
        elementsRV.setAdapter(elementsAdapter);
        findViewById(R.id.submit_new_element_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(elementET.getText().toString())) {
                    elementsAdapter.add(0, elementET.getText().toString());
                    elementET.setText(null);
                    printGemElements();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            elementsAdapter.gemElements.clear();
            elementsAdapter.notifyDataSetChanged();
            resultTv.setText("Gem Elements is: " + "\n Output: " + 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void printGemElements() {

        if (elementsAdapter.gemElements.size() <= 0) {
            Snackbar.make(findViewById(R.id.insertElement_til), "Enter Elements First!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        List<String> commonChars = new ArrayList<>();
        String common = "";

        char[] measure = elementsAdapter.gemElements.get(0).toCharArray();
        for (int j = 0; j < elementsAdapter.gemElements.size(); j++) {
            //get commons chars for every word
            for (int i = 0; i < measure.length; i++) {
                if (elementsAdapter.gemElements.get(j).contains(measure[i] + "")) {
                    //to prevent char repeating
                    if (!common.contains(measure[i] + ""))
                        common += (measure[i]);
                }
            }
            // if first word doesn't have common chars with second get out
            if (common.length() <= 0) {
                resultTv.setText("Gem Elements is: " + "\n Output: " + 0);

                return;
            } else {
                commonChars.add(common);
                common = "";
            }
        }
        String shortest = "";
        String prev = "";
        for (int x = 0; x < commonChars.size(); x++) {
            if (commonChars.get(x).length() < prev.length())
                shortest = commonChars.get(x);
            prev = commonChars.get(x);

        }
        resultTv.setText("Gem Elements is: " + shortest + "\n Output: " + shortest.length());

    }


}
