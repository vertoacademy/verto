package com.example.sharma.vertosacademy.NoteTaker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.NoteTaker.DataModel.Note;
import com.example.sharma.vertosacademy.NoteTaker.SQLDatabase.NotesDataSource;
import com.example.sharma.vertosacademy.R;

import java.util.List;

/**
 * Created by Ashish Kumar Satyam on 4/5/2017.
 */

public class CustomListAllAdapter extends ArrayAdapter {
    Context context;
    List<Note> allNotes;
    public final int  EDIT = 1;
    public final int  READ = 0;

    /**
     * Constructor.
     * @param context
     * @param notes
     */
    public CustomListAllAdapter(Activity context, List<Note> notes) {
        super(context, R.layout.custom_list_all_notes, notes);
        this.allNotes= notes;
        this.context=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow=layoutInflater.inflate(R.layout.custom_list_all_notes,null,true);
        TextView tvTitle =(TextView) viewRow.findViewById(R.id.tvNoteTitle);

        // set the textView title
        tvTitle.setText(allNotes.get(position).getTitle());

        // setup the images Buttons
        ImageButton ibEdit= (ImageButton) viewRow.findViewById(R.id.ibEdit);
        ImageButton ibDelete= (ImageButton) viewRow.findViewById(R.id.ibDelete);
        ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesDataSource ds = new NotesDataSource(context);
                ds.open();
                Note note = ds.getNoteById((Note)getItem(position));
                openEditDialog(note,EDIT);
                ds.close();
            }
        });
        ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesDataSource ds = new NotesDataSource(context);
                ds.open();
                ds.removeNoteById((Note)getItem(position));
                remove(getItem(position));
                notifyDataSetChanged();
                ds.close();
            }
        });

        //setup show the note
        final LinearLayout layoutReading = (LinearLayout) viewRow.findViewById(R.id.layoutReadingNote);
        layoutReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditDialog((Note)getItem(position),READ);
            }
        });
        return viewRow;
    }

    /**
     * Open dialog View
     * @param note
     * @param Op
     */
    private void openEditDialog(final Note note, int Op){
        final NotesDataSource dbSource= new NotesDataSource(context);
        dbSource.open();
        final long noteId = note.getId();
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_add_note);
        Button btnCancel= (Button) dialog.findViewById(R.id.btnCancel);
        EditText etTitle = (EditText)dialog.findViewById(R.id.etNoteTitle) ;
        EditText etText = (EditText)dialog.findViewById(R.id.etNoteText) ;
        etTitle.setText(note.getTitle());
        etText.setText(note.getText());
        Button btnEdit = (Button) dialog.findViewById(R.id.btnAdd);

        if(Op == EDIT) {
            dialog.setTitle("Edit Note ");
            btnEdit.setText("Edit Note");
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText etTitle = (EditText)dialog.findViewById(R.id.etNoteTitle) ;
                    EditText etText = (EditText)dialog.findViewById(R.id.etNoteText) ;
                    String nTitle = etTitle.getText().toString();
                    String nText = etText.getText().toString();
                    Note n = new Note();
                    n.setId(noteId);
                    n.setTitle(nTitle);
                    n.setText(nText);
                    if(!nText.equals("") && !nTitle.equals("")){
                        // add to database
                        long updatedId=dbSource.updateNoteById(n);
                        // add to the method
                        Toast.makeText(context,note.getTitle()+" Edited !",Toast.LENGTH_LONG).show();
                        remove(note);
                        add(n);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(dialog.getContext(),"fill the input plz!",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else if (Op ==READ){
            dialog.setTitle("Read Note ");
            btnEdit.setVisibility(View.INVISIBLE);
            etText.setEnabled(false);
            etTitle.setEnabled(false);
        }



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
