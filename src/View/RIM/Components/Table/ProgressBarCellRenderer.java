package View.RIM.Components.Table;

import java.awt.Color;
import java.awt.Component;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings({ "serial" })
public final class ProgressBarCellRenderer extends JProgressBar implements TableCellRenderer
{
	private Hashtable<?, ?> m_limitColors;

	/**
	 * Used to get values.
	 */
	private int[] m_limitValues;

	public ProgressBarCellRenderer()
	{
		this(false, true, 0, 100, new Hashtable<Object, Object>(), Color.WHITE);
	}

	public ProgressBarCellRenderer(boolean paintNum, boolean paintBorder, int min, int max, Hashtable<?, ?> limitColors, Color bg)
	{
		super(SwingConstants.HORIZONTAL, min, max);
		setStringPainted(paintNum);
		setBorderPainted(paintBorder);
		setBackground(bg);
		setLimits(limitColors);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		int n = 0;

		if (!(value instanceof Number))
		{
			String str;
			if (value instanceof String)
			{
				str = (String) value;
			}
			else
			{
				str = value.toString();
			}

			try
			{
				n = Integer.valueOf(str).intValue();
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			n = ((Number) value).intValue();
		}

		Color color = getColor(n);
		if (color != null)
		{
			setForeground(color);
		}

		setValue(n);

		return (this);
	}

	public void setLimits(Hashtable<?, ?> limitColors)
	{
		m_limitColors = limitColors;

		if (m_limitColors != null)
		{
			int i = 0;
			int n = m_limitColors.size();
			m_limitValues = new int[n];
			Enumeration<?> e = m_limitColors.keys();
			while (e.hasMoreElements())
			{
				m_limitValues[i++] = ((Integer) e.nextElement()).intValue();
			}

			sort(m_limitValues);
		}
	}

	private Color getColor(int value)
	{
		Color color = null;
		if (m_limitValues != null)
		{
			for (int i = 0; i < m_limitValues.length; i++)
			{
				if (m_limitValues[i] < value)
				{
					color = (Color) m_limitColors.get(new Integer(m_limitValues[i]));
				}
			}
		}

		return (color);
	}

	private static void sort(int[] a)
	{
		int n = a.length;
		for (int i = 0; i < n - 1; i++)
		{
			int k = i;
			for (int j = i + 1; j < n; j++)
			{
				if (a[j] < a[k])
				{
					k = j;
				}
			}

			int tmp = a[i];
			a[i] = a[k];
			a[k] = tmp;
		}
	}

}
