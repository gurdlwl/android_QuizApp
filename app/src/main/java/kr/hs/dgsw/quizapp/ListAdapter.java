package kr.hs.dgsw.quizapp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.dgsw.quizapp.Model.QuestionBean;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private ArrayList<QuestionBean> data;
    private ListClickListener listener;

    public ListAdapter(ArrayList<QuestionBean> data, ListClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_question, viewGroup, false);

        return new ListViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        QuestionBean questionBean = data.get(i);
        listViewHolder.textViewQuestion.setText(questionBean.getQuestion());

        if(questionBean.getType() == QuestionBean.TYPE_TEXT){
            listViewHolder.imageViewType.setImageResource(R.drawable.baseline_text_fields_black_48dp);
        } else if(questionBean.getType() == QuestionBean.TYPE_IMAGE){
            listViewHolder.imageViewType.setImageResource(R.drawable.baseline_photo_black_48dp);
        }

        final int index = i; //questionBean.getId()-1;
        listViewHolder.itemView.setOnClickListener(v -> {
            listener.onClick(v, index);
        });
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        else
            return data.size();
    }
}
