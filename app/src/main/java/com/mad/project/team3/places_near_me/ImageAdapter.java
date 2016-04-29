package com.mad.project.team3.places_near_me;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by harsh on 4/28/16.
 */
public class ImageAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    private int[] RestImages = new int[] {
            R.drawable.res1,
            R.drawable.res2,
            R.drawable.res3,
            R.drawable.res4,
            R.drawable.res5,

    };
    private String[] RestNames = new String[]{"Dominos Pizza", "Hyatt Regency", "Chinatown Hotel", "Amber Inn", "Hilton Chicago"};


    private int[] GasImages = new int[] {
            R.drawable.gas1,
            R.drawable.gas2,
            R.drawable.gas3,
            R.drawable.gas4,
            R.drawable.gas5,

    };
    private String[] GSNames = new String[]{"Shell Chinatown","Tulsa Power Service Inc","Shell", "Mobil", "BP"};


    private int[] ATMImages = new int[] {
            R.drawable.atm1,
            R.drawable.atm2,
            R.drawable.atm3,
            R.drawable.atm4,
            R.drawable.atm5,

    };
    private String[] ATMNames = new String[]{"Chase","Seaway 35th Street","Chase Bank", "Bank of America", "Citi Bank"};


    public ImageAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return RestImages.length;
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }


    @Override
    public Object instantiateItem(View container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        TextView tv = (TextView) item_view.findViewById(R.id.rest);
        tv.setSelected(true);
        tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv.setSingleLine(true);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.rest_view);

        TextView txtView = (TextView) item_view.findViewById(R.id.restName);
        txtView.setText(RestNames[position]);

        //Log.d("NNNNNNNNNN:  ",Names[position]);
        imageView.setImageResource(RestImages[position]);


        ImageView imageView1 = (ImageView) item_view.findViewById(R.id.gas_view);
        TextView txtView1 = (TextView) item_view.findViewById(R.id.gas_Name);
        txtView1.setText(GSNames[position]);
        //Log.d("NNNNNNNNNN:  ",Names[position]);
        imageView1.setImageResource(GasImages[position]);


        ImageView imageView2 = (ImageView) item_view.findViewById(R.id.atm_view);
        TextView txtView2 = (TextView) item_view.findViewById(R.id.atm_Name);
        txtView2.setText(ATMNames[position]);
        //Log.d("NNNNNNNNNN:  ",Names[position]);
        imageView2.setImageResource(ATMImages[position]);


        ((ViewPager)container).addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}

