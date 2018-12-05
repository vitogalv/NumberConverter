package number.converter.com.numberconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Converter extends AppCompatActivity {

    private EditText[] textFeilds = new EditText[4];
    private boolean oneActive = false;
    private int edited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        textFeilds[0] = findViewById(R.id.decInput);
        textFeilds[1] = findViewById(R.id.octInput);
        textFeilds[2] = findViewById(R.id.hexInput);
        textFeilds[3] = findViewById(R.id.binInput);
        textFeilds[0].addTextChangedListener(new DecTextWatcher());
        textFeilds[1].addTextChangedListener(new OctTextWatcher());
        textFeilds[2].addTextChangedListener(new HexTextWatcher());
        textFeilds[3].addTextChangedListener(new BinTextWatcher());
    }

    /**
     * The Event Handler for the Convert Button
     * @param view the Convert Button that was clicked
     */
    public void convert(View view){
        EditText editedFeild = textFeilds[edited];
        String input = editedFeild.getText().toString();
        editedFeild.setEnabled(false);
        String[] conversions;
        try{
            if (edited == 0) {
                conversions = Conversions.fromDecimal(input);
            } else if (edited == 1) {
                conversions = Conversions.fromOctal(input);
            } else if (edited == 2) {
                conversions = Conversions.fromHex(input);
            } else {
                conversions = Conversions.fromBinary(input);
            }

            int conversion = 0;
            for(int i = 0; i < textFeilds.length; i++){
                if(i == edited){
                    continue;
                }
                textFeilds[i].setText(conversions[conversion]);
                if(conversions[conversion].length() > textFeilds[i].getText().toString().length()){
                    Log.d("Converter", "Size Mismatch at " + i);
                }
                conversion++;
            }
        }catch(Exception e){
            Log.e("Converter", e.toString());
            Toast.makeText(this, "Invalid Value. Please clear and try again.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * The Event Handler for the Clear Button
     * @param view the Clear Button that was clicked
     */
    public void clear(View view){
        for(EditText edit : textFeilds){
            edit.getText().clear();
            edit.setMaxEms(10);
            edit.setEnabled(true);
        }
        oneActive = false;
    }

    private class DecTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(oneActive == false) {
                for (int idx = 1; idx < 4; idx++) {
                    textFeilds[idx].setEnabled(false);
                }
                oneActive = true;
                edited = 0;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    private class OctTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(oneActive == false) {
                for (int idx = 0; idx < 4; idx++) {
                    if (idx == 1) {
                        continue;
                    }
                    textFeilds[idx].setEnabled(false);
                }
                oneActive = true;
                edited = 1;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    private class HexTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(oneActive == false) {
                for (int idx = 0; idx < 4; idx++) {
                    if (idx == 2) {
                        continue;
                    }
                    textFeilds[idx].setEnabled(false);
                }
                oneActive = true;
                edited = 2;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    private class BinTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(oneActive == false) {
                for (int idx = 0; idx < 3; idx++) {
                    if (idx == 1) {
                        continue;
                    }
                    textFeilds[idx].setEnabled(false);
                }
                oneActive = true;
                edited = 3;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }
}
