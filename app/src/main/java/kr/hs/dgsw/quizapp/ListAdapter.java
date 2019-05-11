package kr.hs.dgsw.quizapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private ImageView imageViewType;
    private TextView textView;
    private ListClickListener listener;

    public ListAdapter(ListClickListener listener){
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
        listViewHolder.textViewQuestion.setText(this.textView.getText());

        final int index = i;
        listViewHolder.itemView.setOnClickListener(v -> listener.onClick(v, index));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
