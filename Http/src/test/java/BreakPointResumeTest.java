import break_point_resume.BreakPointResume;

/**
 * @author simple
 */
public class BreakPointResumeTest {
    public static void main(String[] args) throws InterruptedException {
//        new BreakPointResume().download();
        new BreakPointResume("http://vjs.zencdn.net/v/oceans.mp4").download();
    }
}
