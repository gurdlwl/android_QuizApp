package kr.hs.dgsw.quizapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewHolder extends RecyclerView.ViewHolder {
    ImageView imageViewType;
    TextView textViewQuestion;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewType = itemView.findViewById(R.id.imageViewType);
        textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
    }
}
