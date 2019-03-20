package prototype.codingexercise;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * Given the following class definition, we are asked to implement Line.deepCopy() to perform a deep copy of the current Line Object.
 */

class Point implements Serializable
{
	public int x, y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}

class Line implements Serializable
{
	public Point start, end;

	public Line(Point start, Point end)
	{
		this.start = start;
		this.end = end;
	}

	//TODO
	public Line deepCopy()
	{
		//Approach 1: (Naive-wrong answer) return new Line(start, end); //shallow copy

		//Approach 2: Deep copy
		/*Point newStart = new Point(start.x, start.y);
		Point newEnd = new Point(end.x, end.y);
		return new Line(newStart, newEnd);*/

		//Approach 3: serialization
		return SerializationUtils.roundtrip(this);

	}

	@Override
	public String toString() {
		return "Line{" +
				"start=" + start +
				", end=" + end +
				'}';
	}
}



public class Demo {
	//boo
}
