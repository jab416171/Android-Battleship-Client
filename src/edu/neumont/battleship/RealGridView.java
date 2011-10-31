/**
 * 
 */
package edu.neumont.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author gwatson
 * 
 */
public class RealGridView extends ViewGroup
{	
	protected int numOfColumns;
	protected int numOfRows;
	public final int DEFAULT_COLUMNS = 10;
	public final int DEFAULT_ROWS = 10;
Paint paint;
	
	// Constuctors
	public RealGridView(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		numOfColumns = DEFAULT_COLUMNS;
		numOfRows = DEFAULT_ROWS;
paint = new Paint();
paint.setColor(0x77777777);
paint.setTextSize(24);
	}
	
	public RealGridView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		numOfColumns = attrs.getAttributeIntValue("RealGridView", "columns", DEFAULT_COLUMNS);
		numOfRows = attrs.getAttributeIntValue("RealGridView", "rows", DEFAULT_ROWS);
		
paint = new Paint();
paint.setColor(0x77777777);
paint.setTextSize(24);		
	}
	
	public RealGridView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		numOfColumns = attrs.getAttributeIntValue("RealGridView", "columns", DEFAULT_COLUMNS);
		numOfRows = attrs.getAttributeIntValue("RealGridView", "rows", DEFAULT_ROWS);
paint = new Paint();
paint.setColor(0x77777777);
paint.setTextSize(24);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * Implement this to do your drawing. Parameters canvas the canvas on which
	 * the background will be drawn
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		/*for(int i=0; i<getChildCount(); i++)
		{
			getChildAt(i).draw(canvas);
		}
		*/
		
		canvas.drawText("BOARD", 0, 0, paint);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * Measure the view and its content to determine the measured width and the
	 * measured height. This method is invoked by measure(int, int) and should
	 * be overriden by subclasses to provide accurate and efficient measurement
	 * of their contents.
	 * 
	 * CONTRACT: When overriding this method, you must call
	 * setMeasuredDimension(int, int) to store the measured width and height of
	 * this view. Failure to do so will trigger an IllegalStateException, thrown
	 * by measure(int, int). Calling the superclass' onMeasure(int, int) is a
	 * valid use.
	 * 
	 * The base class implementation of measure defaults to the background size,
	 * unless a larger size is allowed by the MeasureSpec. Subclasses should
	 * override onMeasure(int, int) to provide better measurements of their
	 * content.
	 * 
	 * If this method is overridden, it is the subclass's responsibility to make
	 * sure the measured height and width are at least the view's minimum height
	 * and width (getSuggestedMinimumHeight() and getSuggestedMinimumWidth()).
	 * 
	 * Parameters widthMeasureSpec horizontal space requirements as imposed by
	 * the parent. The requirements are encoded with View.MeasureSpec.
	 * heightMeasureSpec vertical space requirements as imposed by the parent.
	 * The requirements are encoded with View.MeasureSpec.
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int height = 0;
		int width = 0;
		for(int i=0; i<getChildCount(); i++)
		{
			View child = getChildAt(i);
			if(i / numOfColumns == 0)
				height += child.getHeight();
			if(i % numOfRows == 0)
				width += child.getWidth();
		}
		
//		height = height < getSuggestedMinimumHeight() ? getSuggestedMinimumHeight() : height;
//		width = width < getSuggestedMinimumWidth() ? getSuggestedMinimumWidth() : width;
		
		setMeasuredDimension(width, height);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * Called from layout when this view should assign a size and position to
	 * each of its children. Derived classes with children should override this
	 * method and call layout on each of their children.
	 * 
	 * Parameters change This is a new size or position for this view left Left
	 * position, relative to parent top Top position, relative to parent right
	 * Right position, relative to parent bottom Bottom position, relative to
	 * parent
	 * 
	 * @see android.view.View#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		//super.onLayout(changed, left, top, right, bottom);
		
		for(int i=0; i<getChildCount(); i++)
			getChildAt(i).layout(left, top, right, bottom);
		
	}
	
	/*
	 * (non-Javadoc) This is called during layout when the size of this view has
	 * changed. If you were just added to the view hierarchy, you're called with
	 * the old values of 0.
	 * 
	 * Parameters w Current width of this view. h Current height of this view.
	 * oldw Old width of this view. oldh Old height of this view.
	 * 
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		
	}
}
