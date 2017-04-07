package com.example.sharma.vertosacademy.NoteTaker;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.Drawer_Activity.MainPage;
import com.example.sharma.vertosacademy.NoteTaker.DataModel.Note;
import com.example.sharma.vertosacademy.NoteTaker.SQLDatabase.NotesDataSource;
import com.example.sharma.vertosacademy.R;
import com.kosalgeek.android.photoutil.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class NoteMain extends AppCompatActivity {
    /**
     * User Interface Elements Declarations
     */
    Toolbar appToolbar ;
    ListView lvAllNotesList;

    List<Note> allNotes= new ArrayList<>();

    /**
     * Data Source
     */
    NotesDataSource dbSource;
    /**
     * Array adapter
     */
    CustomListAllAdapter customListAllAdapter;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);

        /**
         * Setup the Database source
         */
        dbSource= new NotesDataSource(this);
        dbSource.open();
        /**
         * Setup the user interfaces elements
         */
        setupToolbar();
        /**
         * setup the List All Notes Adapter.
         */
        allNotes= dbSource.getAllNotes();
        customListAllAdapter = new CustomListAllAdapter(this,allNotes);
        lvAllNotesList.setAdapter(customListAllAdapter);

    }
    /**
     * Setup the Toolbar.
     */
    private void setupToolbar() {

        appToolbar=(Toolbar)findViewById(R.id.appToolbar);
        appToolbar.setTitle("Note Taker");
        appToolbar.setNavigationIcon(R.mipmap.home);
        appToolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        setSupportActionBar(appToolbar);

        appToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteMain.this, MainPage.class));
                //Toast.makeText(NoteMain.this, "Add The Action Onclick Nav Icon", Toast.LENGTH_SHORT).show();
            }
        });
        // link All Notes List
        lvAllNotesList = (ListView) findViewById(R.id.listAllNotes);

    }

    /**
     * Event handler..
     * @param view
     */
    public void onClickFloatingButton(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_add_note);
        Button btnCancel= (Button) dialog.findViewById(R.id.btnCancel);
        Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
        dialog.setTitle("Add New Note");
        final EditText etTitle = (EditText)dialog.findViewById(R.id.etNoteTitle) ;
        final EditText etText = (EditText)dialog.findViewById(R.id.etNoteText) ;



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nTitle = etTitle.getText().toString();
                String nText = etText.getText().toString();
                if(!nText.equals("") && !nTitle.equals("")){
                    // add to database
                    Note note =dbSource.addNewNote(nTitle,nText);
                    // add to the method
                    Toast.makeText(getApplicationContext(),note.getTitle()+" Added !",Toast.LENGTH_LONG).show();
                    customListAllAdapter.add(note);
                    customListAllAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(dialog.getContext(),"fill the input plz!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
