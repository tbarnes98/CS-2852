package barnestr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.List;

import javax.sound.sampled.*;

import edu.msoe.taylor.audio.Note;
import edu.msoe.taylor.audio.SimpleAudio;

/**
 * The Guitar class generates guitar sounds based on user input.
 * <p>
 * In order to use this class correctly, one must create a Guitar
 * object, add all of the desired notes to the object, and then
 * call the play() method.  The play() method will generate a
 * list of samples for all of the notes to be played (by calling
 * an internal method (jaffeSmith())) and then send them to the
 * audio output stream.
 *
 * @author t a y l o r@msoe.edu
 * @version 2018.03.26_2.3
 */
public class Guitar {
    /**
     * Default sample rate in Hz
     */
    private static final int DEFAULT_SAMPLE_RATE = 8000;

    /**
     * Maximum sample rate in Hz
     */
    private static final int MAX_SAMPLE_RATE = 48000;

    /**
     * Default decay rate
     */
    private static final float DEFAULT_DECAY_RATE = 0.99f;

    /**
     * Queue of notes
     */
    private Queue<Note> notes = new ArrayDeque<>();

    /**
     * Sample rate in samples per second
     */
    private int sampleRate;

    /**
     * Decay rate
     */
    private float decayRate;

    /**
     * Constructs a new Guitar object with the default sample rate
     * and decay rate.
     */
    public Guitar() {
        sampleRate = DEFAULT_SAMPLE_RATE;
        decayRate = DEFAULT_DECAY_RATE;
    }

    /**
     * Constructs a new Guitar object with the specified parameters.
     * If an invalid sampleRate or decayRate is specified, the
     * default value will be used and an error message is sent to
     * System.err.
     *
     * @param sampleRate sample rate (between 8000 Hz and 48000 Hz)
     * @param decayRate  decay rate (between 0.0f and 1.0f)
     */
    public Guitar(int sampleRate, float decayRate) {
        this.sampleRate = sampleRate;
        this.decayRate = decayRate;
    }

    /**
     * Adds the specified note to this Guitar.
     *
     * @param note Note to be added.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Generates the audio samples for the notes listed in the
     * current Guitar object by calling the jaffeSmith algorithm and
     * sends the samples to the speakers.
     *
     * @throws LineUnavailableException If audio line is unavailable.
     * @throws IOException              If any other input/output problem is encountered.
     */
    public void play() throws LineUnavailableException, IOException {
        SimpleAudio audio = new SimpleAudio();
        audio.play(jaffeSmith());

    }

    /**
     * Uses the Jaffe-Smith algorithm to generate the audio samples.
     * <br />Implementation note:<br />
     * Use Jaffe-Smith algorithm described on the assignment page
     * to generate a sequence of samples for each note in the list
     * of notes.
     *
     * @return List of samples comprising the pluck sound(s).
     */
    private List<Float> jaffeSmith() {
        List<Float> samples = new ArrayList<>();
        for (Note note : notes) {

            // INITIALIZATION PHASE

            // Calculate samplesPerPeriod
            float samplesPerPeriod = sampleRate / note.getFrequency();
            // Calculate the numberOfSamples
            int numberOfSamples = (sampleRate / 1000) * (int) (note.getDuration());
            // Initialize a Queue<Float> of periodSamples
            Queue<Float> periodSamples = new ArrayDeque<>(numberOfSamples);
            for (int j = 0; j < samplesPerPeriod; j++) {
                periodSamples.offer((float) ((Math.random() * 2) - 1));
            }
            float previousSample = 0;

            // LOOPING PHASE

            // Loop numberOfSamples times
            for (int j = 0; j < numberOfSamples; j++) {
                // Dequeue the current off of the periodSamples queue
                if (!(periodSamples.peek() == null)) {
                    float currentSample = periodSamples.poll();
                    // Calculate the new sample value
                    float newSample = ((previousSample + currentSample) / 2) * decayRate;
                    samples.add(newSample);
                    periodSamples.offer(newSample);
                    previousSample = currentSample;
                }
            }
        }
        return samples;
    }

    /**
     * Returns an array containing all the notes in this Guitar.
     * OPTIONAL
     *
     * @return An array of Notes in the Guitar object.
     */
    public Note[] getNotes() {
        throw new UnsupportedOperationException("Optional method not implemented");
    }

    /**
     * Creates a new file and writes to that file.
     * OPTIONAL
     *
     * @param file File to write to.
     * @throws IOException If any other input/output problem is encountered.
     */
    public void write(File file) throws IOException {
        throw new UnsupportedOperationException("Optional method not implemented");
    }
}
