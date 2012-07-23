package Controller.RIM.Utils;

/**
 * A very simple timer for timing java method calls and other processes.
 * Millisecond precision.
 */
public final class TimerUtil
{

	public TimerUtil(){}
}

final class MilliTimer
{
	/**
	 * Used to time the calls.
	 */
	private long m_millis = 0L;

	/**
	 * Constructor.
	 */
	public MilliTimer()
	{
	}

	/**
	 * Resets the timer.
	 */
	public synchronized void reset()
	{
		m_millis = 0L;
	}

	/**
	 * Starts timing a job.
	 */
	public synchronized void start()
	{
		// Get current time in milliseconds
		m_millis = System.currentTimeMillis();
	}

	/**
	 * Get elapsed time in milliseconds.
	 * <p>
	 * 
	 * @return The elapsed time in milliseconds.
	 */
	public long elapsedMillis()
	{
		// Get elapsed time in milliseconds
		long elapsed = System.currentTimeMillis() - m_millis;
		return (elapsed);
	}

	/**
	 * Get elapsed time in seconds.
	 * <p>
	 * 
	 * @return The elapsed time in seconds.
	 */
	public double elapsedSeconds()
	{
		// Get elapsed time in seconds
		double elapsed = (double) elapsedMillis() / 1000.0;
		return (elapsed);
	}

}


final class NanoTimer
{
	/**
	 * Used to get the call time.
	 */
	private long m_nanos = 0L;

	/**
	 * Constructor.
	 */
	public NanoTimer()
	{
	}

	/**
	 * Resets the timer.
	 */
	public synchronized void reset()
	{
		m_nanos = 0L;
	}

	/**
	 * Starts timing a job.
	 */
	public synchronized void start()
	{
		// Get current time in milliseconds
		m_nanos = System.nanoTime();
	}

	/**
	 * Gets elapsed time in nanoseconds.
	 * <p>
	 * 
	 * @return The elapsed time in nanoseconds.
	 */
	public long elapsedNanos()
	{
		// Get elapsed time in nanoseconds
		long elapsed = System.nanoTime() - m_nanos;
		return (elapsed);
	}

	/**
	 * Gets elapsed time in milliseconds.
	 * <p>
	 * 
	 * @return The elapsed time in milliseconds.
	 */
	public double elapsedMillis()
	{
		double elapsed = (double) elapsedNanos() / 1000000.0;
		return (elapsed);
	}

	/**
	 * Gets elapsed time in seconds.
	 * <p>
	 * 
	 * @return The elapsed time in seconds.
	 */
	public double elapsedSeconds()
	{
		// Get elapsed time in seconds
		double elapsed = elapsedMillis() / 1000.0;
		return (elapsed);
	}
}
