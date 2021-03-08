/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.complex;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;


/**
 * 
 * @author Shamil
 */
public class BufferedImageCreating {

	int x, y;
	BufferedImage b;

	// внутренний статический класс для задания отступов графика
	private static class OffSetImgBuffPlot {
		//static, иначе придется объявлять как (new BufferedImageCreating()).new OffSetImgBuffPlot(0.1);
		double offset;

		public OffSetImgBuffPlot(double offset) throws OffsetException {

			if (offset < 0.1 || offset > 0.5)
				throw new OffsetException();
			else
				this.offset = offset;
		}

		public double getOffset() {
			return offset;
		}

		// внутренний внутренний класс исключения
		public class OffsetException extends Exception {

			public OffsetException() {
				super("Неправильно задан отступ. Минимум: 0.1, максимум: 0.5.");
			}

		}

	}

	// вывод столбиковой диаграммы (по оси Y значения вектора <Integer>a)
	public static BufferedImage vectorToColumnsBarPlot(Vector<Integer> a,
													   int width, int height, double offset1percent) {

		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		int count = a.size();

		// максимальный элемент для дальнейшего определения высоты единицы
		// деления по Y
		int maxVectorElement = 0;
		for (Iterator iterator = a.iterator(); iterator.hasNext();) {

			Integer integer = (Integer) iterator.next();
			maxVectorElement = Math.max(maxVectorElement, integer);

		}

		Graphics2D g2 = img.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, img.getWidth(), img.getHeight());// заливаем все белым

		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(2));
		g2.setFont(new Font("Serif", Font.BOLD, 12));

		int xAxisesYPosition = (int) (height * (1 - offset1percent));// уровень оси X по y
		int yAxisesXPosition = (int) (width * offset1percent);// уровень оси Y по x
		// рисуем оси
		g2.drawLine(0, xAxisesYPosition, width, xAxisesYPosition);// X
		g2.drawLine(yAxisesXPosition, 0, yAxisesXPosition, height);// Y
		int intervalWidth = (int) (width * (1 - offset1percent) / count);// ширина интервала
		int columnsOnePointHeight = (int) (height * (1 - offset1percent) / maxVectorElement);// высота единицы деления по Y

		for (int i = 0; i < a.size(); i++) {
			// набор координат
			int MarkX1 = yAxisesXPosition + intervalWidth * (i + 1);
			int MarkY1 = xAxisesYPosition;
			int MarkX2 = yAxisesXPosition + intervalWidth * (i + 1);
			int MarkY2 = xAxisesYPosition
					+ (int) (height * offset1percent * 0.5);

			int stringX = yAxisesXPosition + intervalWidth * i
					+ (int) (intervalWidth * 0.25);
			int stringY = xAxisesYPosition
					+ (int) (height * offset1percent * 0.75);

			int barX = yAxisesXPosition + intervalWidth * i;
			int barY = xAxisesYPosition - a.get(i) * columnsOnePointHeight;
			int barWidth = intervalWidth;
			int barHeight = a.get(i) * columnsOnePointHeight;
			// рисуем график
			g2.drawLine(MarkX1, MarkY1, MarkX2, MarkY2);// зарубки на оси X
			g2.setColor(Color.RED);
			g2.drawString(a.get(i).toString(), stringX, stringY);// обозначения чисел на X
			g2.setColor(Color.GREEN);
			g2.drawRect(barX, barY, barWidth, barHeight);// столбики

		}

		g2.dispose();
		return img;

	}

	public static BufferedImage getImage() {
		BufferedImage imgBuff3 = new BufferedImage(500, 500,
				BufferedImage.TYPE_INT_RGB);

		// чепуха
		Graphics2D g2 = imgBuff3.createGraphics();
		g2.setColor(Color.RED);
		g2.setFont(new Font("Serif", Font.ITALIC, 48));
		g2.drawString("Hello World!", 100, 100);
		g2.setStroke(new BasicStroke(15));
		g2.drawLine(50, 150, 300, 150);
		g2.drawLine(50, 150, 50, 50);
		g2.drawOval(100, 100, 50, 50);

		// вывод графика
		Vector<Integer> imgVector = new Vector<Integer>();
		for (int i = 0; i < 10; i++)
			imgVector.add((int) (Math.random() * 10));

		OffSetImgBuffPlot offset;
		try {
			offset = new OffSetImgBuffPlot(0.1); // необходима подобная конструкция, т.к. внутренний класс
			g2.drawImage(// вставляем столбиковую диаграмму
					vectorToColumnsBarPlot(imgVector, 200, 200,
							offset.getOffset()), null, 100, 200);
		} catch (OffSetImgBuffPlot.OffsetException e) {
			e.printStackTrace();
		}

		g2.dispose();
		return imgBuff3;
	}
}
