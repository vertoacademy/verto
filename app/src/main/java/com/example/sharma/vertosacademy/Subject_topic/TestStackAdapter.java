package com.example.sharma.vertosacademy.Subject_topic;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;

import java.util.List;

public class TestStackAdapter extends StackAdapter<Integer> {

    Context mContext;
    public static List<ProgramData> topiclist;

    public TestStackAdapter(Context context) {
        super(context);
        mContext = context;
        setDATA.getCustomInstance(context).showSavedValues();
        topiclist = setDATA.getCustomInstance(getContext()).getList();
    }


    @Override
    public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {

        if (holder instanceof ColorItemViewHolder) {
            ColorItemViewHolder h = (ColorItemViewHolder) holder;
            h.onBind(data, position);
        }
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view;
        view = getLayoutInflater().inflate(R.layout.list_card_item, parent, false);
        return new ColorItemViewHolder(view);

    }

    @Override
    public int getItemViewType(int position) {

        return R.layout.list_card_item;
    }

    class ColorItemViewHolder extends CardStackView.ViewHolder {
        View mLayout;
        View mContainerContent;
        TextView mTextTitle, mTopicDescription;

        public ColorItemViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);
            mTopicDescription = (TextView) view.findViewById(R.id.topic_content_discription);

        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        public void onBind(Integer data, int position) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);

            ProgramData pd = topiclist.get(position);
            mTextTitle.setText(pd.topic_name);
            mTopicDescription.setText(pd.topic_description);

        }

    }


}
