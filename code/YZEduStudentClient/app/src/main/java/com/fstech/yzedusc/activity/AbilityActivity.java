package com.fstech.yzedusc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fstech.yzedusc.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by shaoxin on 18-4-10.
 * 能力档案主界面
 */

public class AbilityActivity extends AppCompatActivity {
    private RadarChart mChart;
    private final String[] mParties = {"理论分析", "实践操作", "语言表达", "创新思维", "独立思考", "团队协作", ""};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability);
        initView();
        setChart();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        mChart = (RadarChart) findViewById(R.id.radarChart);
    }


    private void setChart() {
        // 绘制线条宽度，圆形向外辐射的线条
        mChart.setWebLineWidth(1.5f);
        // 内部线条宽度，外面的环状线条
        mChart.setWebLineWidthInner(1.0f);
        // 所有线条WebLine透明度
        mChart.setWebAlpha(100);
        // 点击的时候弹出对应的布局显示数据
        mChart.setHighlightPerTapEnabled(false);
        // 动画
        mChart.animateY(1000, Easing.EasingOption.Linear);
        mChart.animateX(1000, Easing.EasingOption.Linear);
        mChart.setDescription(null);    // 不显示描述信息
        mChart.getLegend().setEnabled(false);   // 不显示图例

        setData();

        XAxis xAxis = mChart.getXAxis();
        // X坐标值字体大小
        xAxis.setTextSize(12f);
        YAxis yAxis = mChart.getYAxis();
        // Y轴不显示
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "";
            }
        });
        // X轴显示对应中文
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int position = (int) value;
                return mParties[position];
            }
        });


        // Y坐标值标签个数
        yAxis.setLabelCount(6, false);
        // Y坐标值字体大小
        yAxis.setTextSize(15f);
        // Y坐标值是否从0开始
        yAxis.setStartAtZero(true);

        Legend l = mChart.getLegend();
        // 图例位置
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        // 图例X间距
        l.setXEntrySpace(1f);
        // 图例Y间距
        l.setYEntrySpace(1f);
    }

    public void setData() {

        // Y的值，数据填充
        ArrayList<RadarEntry> yVals1 = new ArrayList<RadarEntry>();

        yVals1.add(new RadarEntry(87, 0));
        yVals1.add(new RadarEntry(73, 1));
        yVals1.add(new RadarEntry(69, 2));
        yVals1.add(new RadarEntry(92, 3));
        yVals1.add(new RadarEntry(77, 4));
        yVals1.add(new RadarEntry(89, 5));

        RadarDataSet set1 = new RadarDataSet(yVals1, "能力档案");
        // Y数据颜色设置
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        // 是否实心填充区域
        set1.setDrawFilled(true);
        // 数据线条宽度
        set1.setLineWidth(2f);

        RadarData data = new RadarData(set1);
        // 数据字体大小
        data.setValueTextSize(8f);
        // 是否绘制Y值到图表
        data.setDrawValues(true);

        mChart.setData(data);

        mChart.invalidate();
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

}
