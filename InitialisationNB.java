package lecteurvideo;
/*
    Native Bibliotheque NB
*/
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class InitialisationNB{
    public InitialisationNB(){}

    public static void LoadLibraryNB(String pathNBvlc){
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),pathNBvlc);
       Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
       LibXUtil.initialise();
     }
}

