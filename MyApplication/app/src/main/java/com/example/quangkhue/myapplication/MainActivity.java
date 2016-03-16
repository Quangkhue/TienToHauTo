package com.example.quangkhue.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private String s;
    private Stack<String> stk;
    private Stack<Float> tack;
    private char[] day;
    Button button;
    EditText edtNhap, edtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btTinhTong);
        edtNhap = (EditText) findViewById(R.id.edtNhap);
        edtKetQua = (EditText) findViewById(R.id.edtKetQua);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = edtNhap.getText().toString();
                TinhHauTo();
                Toast.makeText(MainActivity.this, TinhHauTo(), Toast.LENGTH_LONG).show();
                edtKetQua.setText(GiaTri() + "");
            }
        });
    }

    public int DoUuTien(String x) {

        if (x.equals("+") || x.equals("-")) {
            return 0;
        }

        if (x.equals("*") || x.equals("/")) {
            return 1;
        }

        return 2;
    }

    public int KiemTraToanHang(String x) {
        try {
            Integer.parseInt(x);
            return 0;
        } catch (Exception e) {
            // TODO: handle exception
            return 1;
        }
    }

    public String TinhHauTo() {
        String x = "";
        day = s.toCharArray();
        stk = new Stack<>();
        for (int i = 0; i < day.length; i++) {
            if (KiemTraToanHang(String.valueOf(day[i])) == 0) {
                x += String.valueOf(day[i]);
            }
            if (day[i] == '+' || day[i] == '-' || day[i] == '*'
                    || day[i] == '/') {
                try {
//                    if (stk.empty()) {
//                        stk.push(String.valueOf(day[i]));
//                    } else {
//                        String kt = stk.peek();
//                        if (DoUuTien(kt) < DoUuTien(String.valueOf(day[i]))) {
//                            stk.push(String.valueOf(day[i]));
//                        } else {
//                            x += stk.pop();
//                            stk.push(String.valueOf(day[i]));
//                        }
//                    }
                    if (stk.empty()) {
                        stk.push(String.valueOf(day[i]));
                    } else {
                        String kt = stk.peek();
                        if (DoUuTien(kt) < DoUuTien(String.valueOf(day[i]))) {
                            stk.push(String.valueOf(day[i]));
                        } else if (DoUuTien(kt) == DoUuTien(String.valueOf(day[i]))) {
                            x += stk.pop();
                            stk.push(String.valueOf(day[i]));
                        } else {
                            while (DoUuTien(kt) >= DoUuTien(String.valueOf(day[i]))) {
                                x += stk.pop();
                                if (stk.empty()) {
                                    break;
                                } else {
                                    kt = stk.peek();
                                }

                            }
                            stk.push(String.valueOf(day[i]));
                        }

                    }

                } catch (Exception e) {
                    System.out.println("Loi");
                }

            }
        }

        try {
            while (!stk.empty()) {
                x += stk.pop();
            }
        } catch (Exception e) {
            System.out.println("Loi");
            // TODO: handle exception
        }

        return x;
    }

    public float GiaTri() {
        char[] ht = TinhHauTo().toCharArray();
        tack = new Stack<>();
        for (int i = 0; i < ht.length; i++) {
            try {

                if (KiemTraToanHang(String.valueOf(ht[i])) == 0) {
                    tack.push(Float.parseFloat(String.valueOf(ht[i])));
                } else {
                    switch (String.valueOf(ht[i])) {
                        case "+":
                            tack.push(tack.pop() + tack.pop());
                            break;
                        case "-":
                            float x1 = tack.pop();
                            float y1 = tack.pop();
                            tack.push(y1 - x1);
                            break;
                        case "*":
                            tack.push(tack.pop() * tack.pop());
                            break;

                        default:
                            float x2 = tack.pop();
                            float y2 = tack.pop();
                            tack.push(y2 / x2);
                            break;
                    }

                }

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return tack.pop();
    }

    public String[] Chuoi(){
        String s = "2*3*4+5_8/4-9",s1;
         s.split("/*");
        s1 = s.replaceAll("/*",",");
        Log.d("kt",s1);
        return null;
    }
}

