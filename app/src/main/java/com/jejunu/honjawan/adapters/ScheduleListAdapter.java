package com.jejunu.honjawan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jejunu.honjawan.R;
import com.jejunu.honjawan.repositories.ScheduleRepository;
import com.jejunu.honjawan.vo.ScheduleVO;

import java.util.ArrayList;

public class ScheduleListAdapter extends BaseAdapter {
    private final String TAG = "[ScheduleAdapter]";
    private ArrayList<ScheduleVO> listViewItemList;
    private ScheduleRepository repository;

    public ScheduleListAdapter(ArrayList<ScheduleVO> list) {
        this.listViewItemList = list;
        this.repository = ScheduleRepository.getInstance();
    }

    @Override
    public int getCount() {
        if (listViewItemList != null) {
            return listViewItemList.size() ;
        } else {
            this.listViewItemList = new ArrayList<ScheduleVO>();
            return 0;
        }
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

        // Delete 이벤트 설정
        convertView.findViewById(R.id.btn_schedule_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.delete(scheduleVO.getId());
                Toast.makeText(context, "해당 일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                listViewItemList.remove(position);
                notifyDataSetChanged();
            }
        });

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
