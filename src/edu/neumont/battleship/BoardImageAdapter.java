package edu.neumont.battleship;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class BoardImageAdapter extends BaseAdapter
{
	private Context context;
	
	 // references to our images
	private final int tempMultiplier = 50;
    private Integer[] thumbIds = {
            R.drawable.ic_menu_refresh, R.drawable.icon,
    };

	
	public BoardImageAdapter(Context c)
	{
		context = c;
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

        imageView.setImageResource(thumbIds[position%2]);
        return imageView;

	}
	
}
