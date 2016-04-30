package com.mad.project.team3.places_near_me;

/**
 * Created by shiv on 4/27/2016.
 */


        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.List;

public class Recycler_Adaptor extends RecyclerView.Adapter<Recycler_Adaptor.MyViewHolder> {

    private List<User_Attribute> user_attributes;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView key, value ;

        public MyViewHolder(View view) {
            super(view);
            key = (TextView) view.findViewById(R.id.key);
            value = (TextView) view.findViewById(R.id.value);

        }
    }


    public Recycler_Adaptor(List<User_Attribute> user_attributes) {
        this.user_attributes = user_attributes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usr_prop_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User_Attribute user_a = user_attributes.get(position);
        holder.key.setText(user_a.getKey());
        holder.value.setText(user_a.getValue());

    }

    @Override
    public int getItemCount() {
        return user_attributes.size();
    }
}