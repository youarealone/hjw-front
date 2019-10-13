package com.example.hjw_front.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hjw_front.R;
import com.example.hjw_front.vo.ScheduleVO;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleListAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ScheduleVO> listViewItemList = new ArrayList<ScheduleVO>() ;

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_schedule, parent, false);
        }

        TextView tvTime = convertView.findViewById(R.id.tv_schedule_time);
        TextView tvContent = convertView.findViewById(R.id.tv_schedule_content);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ScheduleVO scheduleVO = listViewItemList.get(position);

        String state = "AM";
        int hour = scheduleVO.getHour();
        // 아이템 내 각 위젯에 데이터 반영
        if (hour > 12) {
            hour -= 12;
            state = "PM";
        }
        // EditText에 출력할 형식 지정
        tvTime.setText(state + " " + hour + "시 " + scheduleVO.getMinutes() + "분");
        tvContent.setText(scheduleVO.getContent());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    public void addItem(ScheduleVO scheduleVO) {
        listViewItemList.add(scheduleVO);
    }
}
