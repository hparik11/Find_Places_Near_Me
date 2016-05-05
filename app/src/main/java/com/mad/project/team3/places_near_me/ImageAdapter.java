package com.mad.project.team3.places_near_me;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
    LayoutInflater layoutInflater1,layoutInflater2;
    private int[] RestImages = new int[] {
            R.drawable.res1,
            R.drawable.res2,
            R.drawable.res3,
            R.drawable.res4,
            R.drawable.res5,
            R.drawable.gas1,
            R.drawable.gas2,
            R.drawable.gas3,

    };
    private String[] RestNames = new String[]{"Dominos Pizza", "Cloud Gate/Chicago Bean", "Adler Planetarium", "Amber Inn", "Art Institute of Chicago","Navy Pier","Shedd Aquarium","Willis Tower"};


    private String[] Description = new String[]{"Domino's Pizza, Inc. /ˈdɒmᵻnoʊz/ is an American restaurant chain and international franchise pizza delivery corporation headquartered at the Domino Farms Office Park in Ann Arbor Charter Township, Michigan, United States, near Ann Arbor, Michigan","The Cloud Gate Sculpture, also known as the 'bean, is one of the highlights of Millennium Park. Designed by the artists Anish Kapoor, the Chicago the Bean sculpture is made of 168 highly polished stainless steel plates - giving the appearance of liquid mercury. Up close, the highly reflective nature of the sculpture captures the beautiful skyline of Chicago. The Cloud Gate Sculpture has thus become a tourist hot spot and is the perfect place to take your vacation snapshots.",
    "The Adler Planetarium is a public museum dedicated to the study of astronomy and astrophysics. It was founded in 1930 by Chicago business leader Max Adler. It is located on the northeast tip of Northerly Island at the shore of Lake Michigan in Chicago, Illinois. The Adler is America's first planetarium and part of Chicago's Museum Campus, which includes the John G. Shedd Aquarium and The Field Museum. The Adler's mission is to inspire exploration and understanding of the Universe.\n" +
            "\n","This venerable, no-frills budget hotel in Bronzeville is 2 blocks to the El train and 1.1 miles to U.S. Cellular Field. \n" +
            "\n" +
            "The simple rooms feature minifridges, microwaves, sitting areas, cable TV and air-conditioning. The 1- and 2-bedroom suites add Jacuzzis.\n" +
            "\n" +
            "Perks include a restaurant with a Southern buffet and a takeout joint.","The Art Institute of Chicago (AIC) is an encyclopedic art museum located in Chicago's Grant Park. It features a collection of Impressionist and Post-Impressionist art in its permanent collection. Its holdings also include American art, Old Masters, European and American decorative arts, Asian art, Islamic art, Ancient classical and Egyptian art, modern and contemporary art, and architecture and industrial and graphic design. In addition, it houses the Ryerson & Burnham Libraries.\n" +
            "\n" +
            "Tracing its history to a free art school and gallery founded in 1866, the museum is located at 111 South Michigan Avenue in the Chicago Landmark Historic Michigan Boulevard District. It is associated with the School of the Art Institute of Chicago and is overseen by president and Eloise W. Martin director James Rondeau.[2] It is one of the most visited art museums in the world with about 1.5 million visitors annually (2013), and with one million square feet in eight buildings, it is the second-largest art museum in the United States, after the Metropolitan Museum of Art.","Navy Pier is a 3,300-foot-long (1,010 m) pier on the Chicago shoreline of Lake Michigan. It is located in the Streeterville neighborhood of the Near North Side community area. The Navy Pier currently encompasses more than fifty acres of parks, gardens, shops, restaurants, family attractions and exhibition facilities and is the top leisure destination in the Midwest, drawing nearly nine million visitors annually.[2] It is one of the most visited attractions in the entire Midwestern United States and is Chicago's number one tourist attraction.","Shedd Aquarium (formally the John G. Shedd Aquarium) is an indoor public aquarium in Chicago, Illinois in the United States that opened on May 30, 1930. The aquarium contains 32,000 animals,[1] and was for some time the largest indoor aquarium in the world with 5,000,000 US gallons (19,000,000 l; 4,200,000 imp gal) of water. Shedd Aquarium was the first inland aquarium with a permanent saltwater fish collection. It is surrounded by Museum Campus Chicago, which it shares with the Adler Planetarium and the Field Museum of Natural History. The aquarium has 2 million annual visitors; it was the most visited aquarium in the U.S. in 2005, and in 2007, it surpassed the Field Museum as the most popular cultural attraction in Chicago.[8] It contains 1500 species including fish, marine mammals, birds, snakes, amphibians, and insects.[9] The aquarium received awards for best exhibit from the Association of Zoos and Aquariums (AZA) for Seahorse Symphony in 1999, Amazon Rising in 2001, and Wild Reef in 2004.","The Willis Tower, built and still commonly referred to as Sears Tower, is a 108-story, 1,451-foot (442 m) skyscraper in Chicago, Illinois, United States.[3] At completion in 1973, it surpassed the World Trade Center towers in New York to become the tallest building in the world, a title it held for nearly 25 years. The Willis Tower is the second-tallest building in the United States and the 14th-tallest in the world. More than one million people visit its observation deck each year, making it one of Chicago's most popular tourist destinations. The structure was renamed in 2009 by the Willis Group as part of its lease on a portion of the tower's space.\n" +
            "\n" +
            "As of December 2013, the building's largest tenant is United Airlines, which moved its corporate headquarters from the United Building at 77 West Wacker Drive in 2012 and today occupies around 20 floors with its headquarters and operations center.[4][5]\n" +
            "\n" +
            "The building's official address is 233 South Wacker Drive, Chicago, Illinois 60606."};

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
    public Object instantiateItem(final ViewGroup container, final int position) {

        layoutInflater1 = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View item_view1 =  layoutInflater1.inflate(R.layout.swipe_layout,container,false);


        final TextView txtdesc = (TextView) item_view1.findViewById(R.id.txtDesc);
        ImageView imageView = (ImageView) item_view1.findViewById(R.id.rest_view);

        TextView txtView = (TextView) item_view1.findViewById(R.id.restName);
        txtView.setText(RestNames[position]);

        imageView.setImageResource(RestImages[position]);
        imageView.setOnClickListener(new View.OnClickListener() {

                                         @Override
                                         public void onClick(View v) {
                                             txtdesc.setText(Description[position]);
                                             txtdesc.setVisibility(View.VISIBLE);
                                         }
                                     });




        ((ViewPager)container).addView(item_view1);
        return item_view1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}

