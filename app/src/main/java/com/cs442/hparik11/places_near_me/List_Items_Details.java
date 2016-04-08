package com.cs442.hparik11.places_near_me;

/**
 * Created by harsh on 3/11/16.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class List_Items_Details extends AppCompatActivity {
    ListView listView;
    MyCustomAdapter dataAdapter = null;
    //public static int RESULT_OK=-1;
    Boolean checkLong = false;
    static double total_bill=0;
    //  final ListView listView=null;
    final ArrayList<List_Menu_Details> FoodItemList = new ArrayList<List_Menu_Details>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        List_Menu_Details r=new List_Menu_Details("McDonalds","Chicago");
        FoodItemList.add(r);
        r=new List_Menu_Details("Pizza Hut","New York");
        FoodItemList.add(r);
        dataAdapter = new MyCustomAdapter(this,
                R.layout.list_item_details, FoodItemList);
        listView =(ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),new_list.class);
                startActivity(i);
            }
        });
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class MyCustomAdapter extends ArrayAdapter<List_Menu_Details> {

        private ArrayList<List_Menu_Details> FoodItemList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<List_Menu_Details> FoodItemList) {
            super(context, textViewResourceId, FoodItemList);
            this.FoodItemList = new ArrayList<List_Menu_Details>();
            this.FoodItemList.addAll(FoodItemList);
        }

        private class ViewHolder {
            // ImageView image;
            // TextView name;
            TextView name;
            // TextView location;
            Button button1;
            Button button2;
            Button button3;
            //CheckBox Item;
           /* TextView Price;
            TextView Type*/;


        }

        public  void removeitem(int position){
            FoodItemList.remove(position);



        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_item_details, null);

                holder = new ViewHolder();
                // holder.image=(ImageView)convertView.findViewById(R.id.icon);
                //holder.Item = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                //  holder.Price = (TextView) convertView.findViewById(R.id.Price);
                //  holder.location=(TextView) convertView.findViewById(R.id.location);
                holder.button1=(Button) convertView.findViewById(R.id.button1);
                holder.button2=(Button) convertView.findViewById(R.id.button2);
                holder.button3=(Button) convertView.findViewById(R.id.button3);

                /*holder.Price = (TextView) convertView.findViewById(R.id.Price);
                holder.Type = (TextView) convertView.findViewById(R.id.Type);*/
                convertView.setTag(holder);

               /* holder.Item.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        FoodMenuItem Item = (FoodMenuItem) cb.getTag();
                        if(Item.isSelected())
                        {
                            Item.selected=false;
                        }
                        else
                        {
                            Item.selected=true;
                        }
                       *//* Toast.makeText(getApplicationContext(),
                                "Selected  Item: " + Item.getName(),
                                Toast.LENGTH_LONG).show();
                        Item.setSelected(cb.isChecked());*//*
                    }
                });*/

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            //listView.setAdapter(dataAdapter);
            int i=1;
            List_Menu_Details Item = FoodItemList.get(position);
            //holder.image.setImageResource(imgid[position]);
            holder.name.setText(" (" + Item.getName() + ")");
            holder.name.setText(Item.getName());



            return convertView;

        }

    }


}