/*  This file is part of DroidPad.
 *
 *  DroidPad is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  DroidPad is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with DroidPad.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.digitalsquid.droidpad.buttons;

//import android.graphics.Canvas;
//import android.graphics.PointF;
//import android.graphics.RectF;

public class Slider extends Item {
	
	private static final long serialVersionUID = -7180651801411890289L;

	public static final int SLIDER_TOT = 16384;
	public static final int SLIDER_CUTOFF = 700;
	
	public static final int SLIDER_GAP = 16;
	public static final int SLIDER_SIZE = 10;
	
	public final Orientation type;
	
	/**
	 * Axis X direction thing
	 */
	public int ax;
	/**
	 * Axis Y direction thing
	 */
	public int ay;
	
	private float tmpAx, tmpAy;

	public Slider(int x, int y, int sx, int sy, Orientation type) {
		super(x, y, sx, sy);
		this.type = type;
	}

	public Slider(float x, float y, float sx, float sy, boolean free, Orientation type) {
		super(x, y, sx, sy, free);
		this.type = type;
	}

//	@Override
//	public void drawInArea(Canvas c, RectF area, PointF centre, boolean landscape) {
//		float tempXw = area.width() - (2 * SLIDER_GAP);
//		float tempYw = area.height() - (2 * SLIDER_GAP);
//
//		float posOnScreenX = ((float)ax / (float)SLIDER_TOT * tempXw / 2f) + centre.x;
//		float posOnScreenY = ((float)ay / (float)SLIDER_TOT * tempYw / 2) + centre.y;
//
//		if(type == Orientation.X || type == Orientation.Both)
//			c.drawLine(posOnScreenX, area.top, posOnScreenX, area.bottom, pGrayBG);
//
//		if(type == Orientation.Y || type == Orientation.Both)
//			c.drawLine(area.left, posOnScreenY, area.right, posOnScreenY, pGrayBG);
//
//		c.drawCircle(posOnScreenX, posOnScreenY, SLIDER_SIZE, pText);
//	}

	@Override
	public String getOutputString() {
		switch(type) {
		case X:
			return "{S" + ax + "}";
		case Y:
			return "{S" + ay + "}";
		case Both:
		default:
			return "{A" + ax + "," + ay + "}";
		}
	}
	
	private boolean axesFloat = false;
	
	/**
	 * When axes are floating, they don't reset when let go of
	 * @param axesFloat If true, axes will float.
	 */
	public void setAxesFloat(boolean axesFloat) {
		this.axesFloat = axesFloat;
	}

	@Override
	public void resetStickyLock() {
		if(!axesFloat) {
			tmpAx = 0;
			tmpAy = 0;
		}
	}

	@Override
	public void finaliseState() {
		ax = (int) tmpAx;
		ay = (int) tmpAy;
	}

	@Override
	public void onMouseOn(ScreenInfo info, float x, float y) {
//		PointF centre = pos.computeCentre(info);
//		RectF area = pos.computeArea(info);
//		float tempXw = area.width() - (2 * SLIDER_GAP);
//		float tempYw = area.height() - (2 * SLIDER_GAP);
//
//		if(type == Orientation.X || type == Orientation.Both)
//		{
//			tmpAx = ((float)(x - centre.x) / tempXw * 2f * SLIDER_TOT);
//			if(tmpAx < -SLIDER_TOT)
//				tmpAx = -SLIDER_TOT;
//			else if(tmpAx > SLIDER_TOT)
//				tmpAx = SLIDER_TOT;
//			ax = (int) tmpAx;
//		}
//		if(type == Orientation.Y || type == Orientation.Both)
//		{
//			tmpAy = ((y - centre.y) / tempYw * 2 * SLIDER_TOT);
//			if(tmpAy < -SLIDER_TOT)
//				tmpAy = -SLIDER_TOT;
//			else if(tmpAy > SLIDER_TOT)
//				tmpAy = SLIDER_TOT;
//			ay = (int) tmpAy;
//		}
	}

	public synchronized void setPosition(float x, float y) {
		if(type == Orientation.X || type == Orientation.Both)
		{
			tmpAx = ((x - 0.5f) * 2f * SLIDER_TOT);
			if(tmpAx < -SLIDER_TOT)
				tmpAx = -SLIDER_TOT;
			else if(tmpAx > SLIDER_TOT)
				tmpAx = SLIDER_TOT;
			ax = (int) tmpAx;
		}
		if(type == Orientation.Y || type == Orientation.Both)
		{
			tmpAy = ((y - 0.5f) * 2 * SLIDER_TOT);
			if(tmpAy < -SLIDER_TOT)
				tmpAy = -SLIDER_TOT;
			else if(tmpAy > SLIDER_TOT)
				tmpAy = SLIDER_TOT;
			ay = (int) tmpAy;
		}
	}

	@Override
	public void onMouseOff() {
	}

	@Override
	int getFlags() {
		int extraFlags = 0;
		
		switch(type) {
		case X:
			extraFlags = FLAG_HAS_X_AXIS;
			break;
		case Y:
			extraFlags = FLAG_HAS_Y_AXIS;
			break;
		case Both:
			extraFlags = FLAG_HAS_X_AXIS | FLAG_HAS_Y_AXIS;
			break;
		}
		
		return FLAG_SLIDER | extraFlags;
	}

	@Override
	int getData1() {
		switch(type) {
		case X:
		case Both:
			return ax;
		default:
			return 0;
		}
	}

	@Override
	int getData2() {
		switch(type) {
		case Y:
		case Both:
			return ay;
		default:
			return 0;
		}
	}

	@Override
	int getData3() {
		return 0;
	}
}
