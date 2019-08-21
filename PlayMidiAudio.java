import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.MidiEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;




public class PlayMidiAudio {


	public static void main(String[] args) throws Exception {

		// Obtains the default Sequencer connected to a default device.
		File file= new File("Final Destination Theme.mid");
		Sequencer sequencer = MidiSystem.getSequencer();
		Sequence sequence = MidiSystem.getSequence(file);

		sequencer.setSequence(sequence);
		Track[] tracks =sequence.getTracks();
		for(int i =0; i< tracks[0].size(); i++){
			MidiEvent event = tracks[0].get(i);
		}
		// Opens the device, indicating that it should now acquire any
	    // system resources it requires and become operational.
		sequencer.open();

	    // create a stream from a file


	    // Sets the current sequence on which the sequencer operates.
	    // The stream must point to MIDI file data.


	   

	    // Starts playback of the MIDI data in the currently loaded sequence.
		sequencer.start();
		Float division =sequence.getDivisionType();// Obtains which division type the MIDI is formatted in. This information was needed to divide the MIDI file into pieces.
		System.out.println(division);
		int resolution = sequence.getResolution();// Obtains resolution needed to calculate the amount of ticks per second
		System.out.println(resolution);

		double ticksPerSecond= resolution*(170.0/60.0);// Calculates ticks per second
		double tickSize = 1.0/ticksPerSecond;// Calculates tick size

		System.out.println("Ticks Per Second is"+ " "+ ticksPerSecond);// Prints out the values calculated above
		System.out.println("Tick Size is"+ " "+tickSize);

       // The duration of the song is 206 seconds

      
    // The division 0.0 so its probably PPQ
	   // The resolution is 960 
       //  Ticks Per Second is 2720.0
      // Tick Size is 3.676470588235294E-4

       

       //  Next Step:Make an array as long the amount of seconds in the song. Use a for loop.

		int[] notes = new int[206];// Makes an array holding every second of the song
		for(int i =0; i<206; i++){
			notes[i]= i*2720;// Each "note" is multiplied by the number of ticks per second in order to play that second of music
		}

		shuffleArray(notes);// Shuffles the array holding the divided pieces of the MIDI file

		
        Scanner input= new Scanner(System.in);// Scans for user input
        System.out.println("1.) Merge Sort 2.) Insertion Sort");// Lets the user decide which sorting algorithm they wanted to use
        String user_input= input.nextLine();
			
        
        for(int j=0; j<notes.length/4; j=j+3){
			sequencer.setTickPosition(notes[j]);
			sequencer.start();
			Thread.sleep(1000);
		
		}// Plays the song in random order

        
        
        if(user_input.equals("1")){
		sort(notes,0,206);
	}  else if(user_input.equals("2")){
		order(notes);
	} else{
		System.out.println("What's that? Sounds like Merge Sort to me!");
		sort(notes,0,206);
	}
		for(int k=0; k<notes.length;k++){
			sequencer.setTickPosition(notes[k]);
			sequencer.start();
			Thread.sleep(1000);
			sequencer.stop();
		}// Based on the users decision the song is sorted by one of the algorithms and then played


        
      

}

	static void shuffleArray(int [] ar){
		Random r= ThreadLocalRandom.current();
		for(int i= ar.length-1; i>0; i--){
			int index = r.nextInt(i+1);
			int a= ar[index];
			ar[index]= ar[i];
       		ar[i]=a;
       	}
       // Shuffles the array



     }

 
	public static void merge(int arr[], int l, int m, int r)
	{
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int [n1];
		int R[] = new int [n2];

		/*Copy data to temp arrays*/
		for (int i=0; i<n1; ++i)
			L[i] = arr[l + i];
		for (int j=0; j<n2; ++j)
			R[j] = arr[m + 1+ j];


		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2)
		{
			if (L[i] <= R[j])
			{
				arr[k] = L[i];
				i++;
			}
			else
			{
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1)
		{
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (j < n2)
		{
			arr[k] = R[j];
			j++;
			k++;
		}


	}

	public static void sort(int arr[], int l, int r)
	{
		if (l < r)
		{
			// Find the middle point
			int m = (l+r)/2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr , m+1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}

	}

	public static void order(int[] a){
		for(int n=1; n<a.length; n++){
			int aTemp= a[n];
			int i = n;
			while(i>0&&aTemp< a[i-1]){
				a[i]=a[i-1];
				i--;
			}a[i]=aTemp;

		}// Code for the insertion sort
	}
}

















//This section plays the song
	// Look up way to scramle the order of the song
    // Maybe setLoopStartPoint and setLoopEndPoint or setTickPosition



