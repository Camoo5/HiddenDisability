package com.tenacity.hiddendisabilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class HiddenDisabilityAdapter extends ListAdapter <HiddenDisability, HiddenDisabilityAdapter.HiddenDisabilitiesHolder> {
    private static final DiffUtil.ItemCallback <HiddenDisability> DIFF_CALLBACK = new DiffUtil.ItemCallback <HiddenDisability> () {
        @Override
        public boolean areItemsTheSame(@NonNull HiddenDisability oldItem, @NonNull HiddenDisability newItem) {
            return oldItem.getId () == newItem.getId ();
        }

        @Override
        public boolean areContentsTheSame(@NonNull HiddenDisability oldItem, @NonNull HiddenDisability newItem) {
            return oldItem.getDisability ().equals ( newItem.getDisability () ) &&
                    oldItem.getSpecialist ().equals ( newItem.getSpecialist () ) &&
                    oldItem.getTreatment ().equals ( newItem.getTreatment () ) &&
                    oldItem.getNote_title ().equals ( newItem.getNote_title () ) &&
                    oldItem.getNote_description ().equals ( newItem.getNote_description () ) &&
                    oldItem.getPriority () == newItem.getPriority ();
        }
    };
    private OnItemClickListener listener;

    public HiddenDisabilityAdapter() {
        super ( DIFF_CALLBACK );
    }

    @NonNull
    @Override
    public HiddenDisabilitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from ( parent.getContext () )
                .inflate ( R.layout.hiddendisability_item, parent, false );
        return new HiddenDisabilitiesHolder ( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull HiddenDisabilitiesHolder holder, int position) {
        HiddenDisability currentHiddenDisability = getItem ( position );
        holder.textViewDisability.setText ( currentHiddenDisability.getDisability () );
        holder.textViewSpecialist.setText ( currentHiddenDisability.getSpecialist () );
        holder.textViewTreatment.setText ( currentHiddenDisability.getTreatment () );
        holder.textViewNote_Title.setText ( currentHiddenDisability.getNote_title () );
        holder.textViewNote_Description.setText ( currentHiddenDisability.getNote_description () );
        holder.textViewPriority.setText ( String.valueOf ( currentHiddenDisability.getPriority () ) );

    }

    public HiddenDisability getHiddenDisabilityAt(int position) {
        return getItem ( position );
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(HiddenDisability hiddenDisability);
    }

    class HiddenDisabilitiesHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDisability;
        private final TextView textViewSpecialist;
        private final TextView textViewTreatment;
        private final TextView textViewNote_Title;
        private final TextView textViewNote_Description;
        private final TextView textViewPriority;

        HiddenDisabilitiesHolder(@NonNull View itemView) {
            super ( itemView );
            textViewDisability = itemView.findViewById ( R.id.text_view_disability );
            textViewSpecialist = itemView.findViewById ( R.id.text_view_specialist );
            textViewTreatment = itemView.findViewById ( R.id.text_view_treatment );
            textViewNote_Title = itemView.findViewById ( R.id.text_view_note_title );
            textViewNote_Description = itemView.findViewById ( R.id.text_view_note_description );
            textViewPriority = itemView.findViewById ( R.id.text_view_priority );

            itemView.setOnClickListener ( v -> {
                int position = getAdapterPosition ();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick ( getItem ( position ) );
                }
            } );
        }
    }
}
