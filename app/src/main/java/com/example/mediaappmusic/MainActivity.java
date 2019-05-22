package com.example.mediaappmusic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Database myDb;
    EditText edtuser, edtpassword;
    Button btndangnhap, btndangky, btnthoat, REG;
    String user, pass1, pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        Controlbtn();
        myDb = new Database(this);
    }

    //chuc nang cua button
    private void Controlbtn() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Thoat ung dung?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Cancel!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("DANG KY TAI KHOAN MOI!");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dangkydialog);
                final EditText edtuserdk = (EditText) dialog.findViewById(R.id.edtuserdk);
                final EditText edtpassdk1 = (EditText) dialog.findViewById(R.id.edtpassdk1);
                final EditText edtpassdk2 = (EditText) dialog.findViewById(R.id.edtpassdk2);
                Button btnhuydk = (Button) dialog.findViewById(R.id.btnhuydk);
                Button btndongydk = (Button) dialog.findViewById(R.id.btndongydk);
                btndongydk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user = edtuserdk.getText().toString();
                        pass1 = edtpassdk1.getText().toString();
                        pass2 = edtpassdk2.getText().toString();

                        if(!user.isEmpty() && !pass1.isEmpty())
                        {
                            if(pass1.equals(pass2))
                            {
                                Boolean success = myDb.addData(user,pass1);
                                if(success)
                                {
                                    Toast.makeText(MainActivity.this,"Dang ky thanh cong",Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else
                                    Toast.makeText(MainActivity.this,"Thong tin khong hop le!", Toast.LENGTH_LONG).show();
                            }else
                                Toast.makeText(MainActivity.this,"Mat khau khong trung khop!", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(MainActivity.this,"Hay nhap day du thong tin!", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

                btnhuydk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namestr = edtuser.getText().toString();
                String passstr = edtpassword.getText().toString();
                Boolean DNsuccecc = myDb.FindData(namestr,passstr);
                if(!namestr.isEmpty() && !passstr.isEmpty())
                {
                    if(DNsuccecc){

                        Toast.makeText(MainActivity.this,"Dang nhap thanh cong!",Toast.LENGTH_LONG).show();
                        Intent acti2 = new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(acti2);
                    }
                    else
                        Toast.makeText(MainActivity.this,"Dang nhap that bai!",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Dang nhap that bai!",Toast.LENGTH_LONG).show();
            }
        });
    }
    //tham chieu den cac thanh phan
    private void Anhxa() {
        edtuser = (EditText) findViewById(R.id.edittextuser);
        edtpassword = (EditText) findViewById(R.id.edittextpassword);
        btndangky = (Button) findViewById(R.id.btndangky);
        btndangnhap = (Button) findViewById(R.id.btndangnhap);
        btnthoat = (Button) findViewById(R.id.btnthoat);
    }

}
