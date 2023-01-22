package excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author cc
 * @date 2023年01月21日 23:02
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {


    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println(data);
    }

    public void invokeHeadMap(Map<Integer,String> headmap, AnalysisContext analysisContext){
        System.out.println(headmap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
