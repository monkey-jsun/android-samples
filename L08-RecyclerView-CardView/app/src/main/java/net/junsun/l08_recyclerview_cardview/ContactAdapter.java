package net.junsun.l08_recyclerview_cardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jsun on 4/17/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<ContactInfo> contactList;

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;
        protected TextView vPhone;

        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vPhone = (TextView) v.findViewById(R.id.txtPhone);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }

    public ContactAdapter(List<ContactInfo> contactList) {
        this.contactList = contactList;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        ContactInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vSurname.setText(ci.surname);
        contactViewHolder.vEmail.setText(ci.email);
        contactViewHolder.vPhone.setText(ci.phone);
        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.namecard_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    // called by touch helper callback
    public void onItemDismissed(int position) {
        contactList.remove(position);
        notifyItemRemoved(position);
    }

    // called by touch helper callback
    public boolean onItemMove(int fromPosition, int toPosition) {
        ContactInfo temp = contactList.get(fromPosition);
        contactList.set(fromPosition, contactList.get(toPosition));
        contactList.set(toPosition, temp);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

}