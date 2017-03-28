package com.example.sharma.vertosacademy.NoteTaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.R;

import java.util.ArrayList;

public class MainNoteTaker extends AppCompatActivity {
    private ListView mlistViewNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_note_taker);

        mlistViewNote = (ListView) findViewById(R.id.mainlistview);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_new_note:
                //start noteactivity in new note
                Intent newNoteActivity = new Intent(this,NoteActivity.class);
                startActivity(newNoteActivity);
                break;
        }
        return true;
    }
    public void ADDNOTE(View v)
    {
        Intent newNoteActivity = new Intent(this,NoteActivity.class);
        startActivity(newNoteActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mlistViewNote.setAdapter(null);
        ArrayList<Note> notes = Utilities.getAllSaveNotes(this);
        if (notes == null || notes.size() == 0) {
            Toast.makeText(this, "you have no saved notes!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteAdapter na = new NoteAdapter(this, R.layout.item_note, notes);
            mlistViewNote.setAdapter(na);

            mlistViewNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String filename=((Note)mlistViewNote.getItemAtPosition(position)).getDateTime()
                            +Utilities.FILE_EXTENSION;
                    Intent viewNoteIntent = new Intent(getApplicationContext(), NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE FILE", filename);
                    startActivity(viewNoteIntent);
                }
            });
        }
    }
}
