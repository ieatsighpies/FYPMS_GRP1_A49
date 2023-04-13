package main.Utils;

/**
* Provide methods to generate Unique ID for request and project
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class UIDGenerator {
    /**
     * Java epoch
     */
    private static final long twepoch = 1288834974657L;
    /**
     * id length in bits
     */
    private static final long sequenceBits = 17;
    /**
     * max sequence in a millis
     */
    private static final long sequenceMax = 65536;
    /**
     * from previoud timestamp
     */
    private static volatile long lastTimestamp = -1L;
    /**
     * the current sequence
     */
    private static volatile long sequence = 0L;

    // method to generate UID
    /**
     * Method to 65536 UID in a millis
     * @return generated UID
     */
    public static synchronized Long generateLongId() {
        long timestamp = System.currentTimeMillis();
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        Long id = ((timestamp - twepoch) << sequenceBits) | sequence;
        return id;
    }

    /**
     * Helper method for generateLongId method
     * 
     * @param lastTimestamp the previoud time stamp
     * @return the time stmap
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
