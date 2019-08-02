package priv.cweiy.pro;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;
import java.io.IOException;

/**
 * @author yichen
 * @version V1.0
 * @ClassName: Test
 * @Description: (用一句话描述该文件做什么)
 * @Date 2019/8/2 9:34
 */
public class Test {
    public static void main(String[] args) {
        System.setProperty("jna.library.path","32".equals(System.getProperty("sun.arch.data.model"))?"lib / win32-x86":"lib / win32-x86 -64");
        String path = "E://test//cweiy-webmagic//src//main//photo";        //我的项目存放路径

        File imageFile = new File(path + "//test.png");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setLanguage("chi_sim");//添加中文字库
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        System.out.println(" path  :   "+tessDataFolder.getPath());
        instance.setDatapath(tessDataFolder.getPath()); // path to tessdata directory

        try {
            long startTime = System.currentTimeMillis();
            String result = instance.doOCR(imageFile);
            long endTime = System.currentTimeMillis();
            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
