package com.example.sharma.vertosacademy.NoteTaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharma.vertosacademy.R;

public class NoteActivity extends AppCompatActivity {
    private EditText mEtTitle;
    private EditText mEtContent;

    private String mNoteFileName;
    private  Note mLoadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);



        mEtTitle=(EditText)findViewById(R.id.note_et_tittle);
        mEtContent=(EditText)findViewById(R.id.note_et_content);

        mNoteFileName=getIntent().getStringExtra("NOTE FILE");
        if(mNoteFileName!=null&&!mNoteFileName.isEmpty()){
            mLoadedNote=Utilities.getNoteByName(this,mNoteFileName);

            if(mLoadedNote!=null){
                mEtTitle.setText(mLoadedNote.getTittle());
                mEtContent.setText(mLoadedNote.getContent());
            }
        }
    }

    public void SAVE(View view)
    {
        saveNote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_note_save:
                saveNote();
                break;
        }
        return true;
    }
    private void saveNote(){
        Note note;
        if(mLoadedNote==null) {
            note = new Note(System.currentTimeMillis(), mEtTitle.getText().toString()
                    , mEtContent.getText().toString());
        }else{
            note = new Note(mLoadedNote.getDateTime(), mEtTitle.getText().toString()
                    , mEtContent.getText().toString());
        }
        if(Utilities.saveNote(this,note)){
            Toast.makeText(this,"your note is saved!",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Cannot save the note,please make sure you have enough space"
                    ,Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
