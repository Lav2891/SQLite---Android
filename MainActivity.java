package com.example.ashwin.sqlitetry2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText name, password, delete, newname, oldname;
    CusAdapter a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.edittextname);
        password = (EditText)findViewById(R.id.edittextpass);
        delete = (EditText)findViewById(R.id.edittextdelete);
        newname = (EditText)findViewById(R.id.edittextnewname);
        oldname = (EditText)findViewById(R.id.edittextcurrentname);
        a = new CusAdapter(this);
    }

    public void add(View view){
        String n = name.getText().toString();
        String p = password.getText().toString();
        if (n.isEmpty() || p.isEmpty()) {
            Message.message(getApplicationContext(), "Enter both name and password");
        }
        else {
            long id = a.insertdata(n,p);
            if(id<=0) {
                Message.message(getApplicationContext(), "Insertion Unsuccessful");
                name.setText("");
                password.setText("");
            }
            else {
                Message.message(getApplicationContext(),"Insertion Successful");
                name.setText("");
                password.setText("");
            }
        }
    }

    public void view(View view)
    {
        String data = a.getdata();
        Message.message(this,data);
    }

    public void delete( View view) {
        String uname = delete.getText().toString();
        if (uname.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int aa = a.delete(uname);
            if (aa <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
                delete.setText("");
            } else {
                Message.message(this, "DELETED");
                delete.setText("");
            }
        }
    }

    public void update( View view) {
        String u1 = oldname.getText().toString();
        String u2 = newname.getText().toString();
        if (u1.isEmpty() || u2.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int aa = a.updateName(u1, u2);
            if (aa <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
                oldname.setText("");
                newname.setText("");
            } else {
                Message.message(getApplicationContext(), "Updated");
                oldname.setText("");
                newname.setText("");
            }
        }
    }
}
