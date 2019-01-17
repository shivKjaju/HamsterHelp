package com.csce.hamstersftw.hamsterhelp;

/**
 * Created by ducle on 4/10/2018.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/14/2017.
 */

public class PersonListAdapter extends ArrayAdapter<Userinfo> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView Firstname;
        TextView Rating;
        TextView NumberRating;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public PersonListAdapter(Context context, int resource, ArrayList<Userinfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String Firstname = getItem(position).getFirstName();
        String Rating = getItem(position).getRating();
        String NumberRating = getItem(position).getNumberRating();

        //Create the person object with the information
        Userinfo userinfo = new Userinfo();
        userinfo.setFirstName(Firstname);
        userinfo.setRating(Rating);
        userinfo.setNumberRating(NumberRating);


        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.Firstname = (TextView) convertView.findViewById(R.id.textView1);
            holder.NumberRating = (TextView) convertView.findViewById(R.id.textView2);
            holder.Rating = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.Firstname.setText(userinfo.getFirstName());
        holder.NumberRating.setText(userinfo.getNumberRating());
        holder.Rating.setText(userinfo.getRating());


        return convertView;
    }
}
