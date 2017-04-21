
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;



/**
 *
 * @author David-College
 */
public class SoundActionHandler implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
       
        switch(command)
        {
            case"A1":
                try 
                {
                    InputStream in = new FileInputStream(new File("BombFired.mp3"));
                    AudioStream audio  = new AudioStream(in);
                    AudioPlayer.player.start(audio);
                } 
                catch (FileNotFoundException ex) {System.out.println("File not found!");} 
                catch (IOException ex) {System.out.println("IO Error!");}    
        }    
    }
}        

