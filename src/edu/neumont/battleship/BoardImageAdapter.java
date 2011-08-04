package edu.neumont.battleship;

import java.util.Random;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class BoardImageAdapter extends BaseAdapter
{
	private Context context;
	private static Random gen;
	
	 // references to our images
    private Integer[] thumbIds = {
//            R.drawable.ic_menu_refresh, R.drawable.icon,
    		R.drawable.battleshiptile1
    };
    private final int tempMultiplier = 100/thumbIds.length;

	
	public BoardImageAdapter(Context c)
	{
		context = c;
		gen = new Random();
	}

	public int getCount()
	{
		return thumbIds.length * tempMultiplier;
	}

	public Object getItem(int idx)
	{
		return null;
//		return thumbIds[idx];
	}

	public long getItemId(int arg0)
	{
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageView;
        if (convertView == null) // if it's not recycled, initialize some attributes
        {  
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else 
        {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(thumbIds[gen.nextInt(thumbIds.length)]);
        return imageView;

	}
	
}
