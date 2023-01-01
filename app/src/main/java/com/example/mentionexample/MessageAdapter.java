package com.example.mentionexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> listMessage;

    public void setData(List<Message> list) {
        this.listMessage = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {
        Message message = listMessage.get(position);
        if (message == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        holder.tvCreateMessage.setText(convertDateToString(calendar.getTime(), "HH:mm dd/MM/yyyy"));
        holder.tvMessage.setText(message.getMessage());
    }

    public String convertDateToString(Date date, String format){
        format = "HH:mm dd/MM/yyyy";
        if (date == null){
            return null;
        }
        Locale locale = Locale.getDefault();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
        return getDateTimeString(date, dateFormat);
    }

    private String getDateTimeString(Date date, SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMessage;
        private TextView tvCreateMessage;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCreateMessage = itemView.findViewById(R.id.tvCreateMessage);
            tvMessage = itemView.findViewById(R.id.tvMessage);
        }
    }
}