package com.example.picoyplaca.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.picoyplaca.fragments.HistoryFragment.OnListFragmentInteractionListener;
import com.example.picoyplaca.R;
import com.example.picoyplaca.dummy.DummyContent.DummyItem;
import com.example.picoyplaca.models.ItemHistoryObject;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHistoryItemRecyclerViewAdapter extends RecyclerView.Adapter<MyHistoryItemRecyclerViewAdapter.ViewHolder> {

    private final List<ItemHistoryObject> mValues;
    private final OnListFragmentInteractionListener mListener;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm");

    public MyHistoryItemRecyclerViewAdapter(List<ItemHistoryObject> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mPlateView.setText(mValues.get(position).getPlate());
        Timestamp stamp = new Timestamp( Long.parseLong(mValues.get(position).getTimestamp()));
        Date date = new Date(stamp.getTime());
        String dateString = sdf.format(date);
        holder.mTimestampView.setText(dateString);
        if (mValues.get(position).isSenior_citizen() == 1){
            holder.mSeniorCitizenView.setText("Tercera edad");
        }else {
            holder.mSeniorCitizenView.setText("");
        }
        if (mValues.get(position).isHandicapped() == 1){
            holder.mHandicappedView.setText("Capacidades especiales");
        }else {
            holder.mHandicappedView.setText("");
        }
        if (mValues.get(position).isInfringement() == 1){
            holder.mInfringementView.setText("HUBO CONTRAVENCIÓN");
        }else {
            holder.mInfringementView.setText("NO HUBO CONTRAVENCIÓN");
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.refreshHistory(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mPlateView;
        public final TextView mTimestampView;
        public final TextView mSeniorCitizenView;
        public final TextView mHandicappedView;
        public final TextView mInfringementView;

        public ItemHistoryObject mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPlateView = view.findViewById(R.id.plate_text_view);
            mTimestampView = view.findViewById(R.id.timestamp_text_view);
            mSeniorCitizenView = view.findViewById(R.id.senior_text_view);
            mHandicappedView = view.findViewById(R.id.handicapped_text_view);
            mInfringementView = view.findViewById(R.id.infrigement_text_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPlateView.getText() + "'";
        }
    }
}
