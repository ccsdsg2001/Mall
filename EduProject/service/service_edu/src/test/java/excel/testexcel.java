package excel;

import com.alibaba.excel.EasyExcel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * @date 2023年01月21日 20:24
 */
public class testexcel {
    public static void main(String[] args) {
//        //设置写入文件夹地址 和 excel名称
//        String filename="E:\\write.xlsx";
//        //调用 easyexcel方法实现写操作
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());


        //读操作
        String filename="E:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class, new ExcelListener()).sheet().doRead();

    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("lucy"+i);
            list.add(demoData);

        }
        return list;
    }
}
