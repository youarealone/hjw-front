package com.example.hjw_front.fragments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.hjw_front.R;
import com.example.hjw_front.utils.FragmentChanger;

import static com.example.hjw_front.R.drawable.speaker;
import static com.example.hjw_front.R.drawable.speaker_mute;
import static com.example.hjw_front.R.drawable.speaker_vibration;

public class SosFragment extends Fragment {
    FragmentChanger fragmentChanger = null;
    final String[] listSound = {"소리", "진동", "무음"};
    private int index;
    private int soundTemp;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSIONS_REQUEST_WRITE_CONTACTS = 101;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentChanger = new FragmentChanger(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sos, container, false);

        final Switch SwitchSOS = view.findViewById(R.id.switch2);
        Button btnSound = view.findViewById(R.id.btn_sound);
        ImageView imageView = view.findViewById(R.id.img_sound);

        index = 0;
        soundTemp = 0;
        view.findViewById(R.id.getContract).setOnClickListener(v -> fragmentChanger.changeFragment(new ContractFragment()));

        btnSound.setOnClickListener(v -> {
            index = index != listSound.length - 1 ? index + 1 : 0;
            if (index == 0) {
                imageView.setImageResource(speaker);
                soundTemp = 0;
            } else if (index == 1) {
                imageView.setImageResource(speaker_vibration);
                soundTemp = 1;
            } else if (index == 2) {
                imageView.setImageResource(speaker_mute);
                soundTemp = 2;
            }
            btnSound.setText(listSound[index]);
        });

        SwitchSOS.setOnCheckedChangeListener((compoundButton, b) -> {
            if (SwitchSOS.isChecked()) {
                createNotification();
            } else {
                removeNotification();
            }
        });
        return view;
    }

    private void createNotification() {
        Bitmap LargeIconSOS = BitmapFactory.decodeResource(getResources(), R.drawable.sos);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "default");
        builder.setSmallIcon(R.mipmap.hjw_logo_round);
        builder.setLargeIcon(LargeIconSOS);
        builder.setContentTitle("SOS 메세지 기능 활성화");
        builder.setContentText("누르시면 지정된 연락처로 메세지가 전송됩니다.");
        switch (soundTemp) {
            case 1:
                builder.setDefaults(Notification.DEFAULT_SOUND);
                break;
            case 2:
                builder.setDefaults(Notification.DEFAULT_VIBRATE);
                break;
            case 3:
                builder.setDefaults(Notification.DEFAULT_LIGHTS);
                break;
//            default:
//                builder.setDefaults(Notification.DEFAULT_SOUND);
//                break;
        }

        builder.setColor(Color.RED);
        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(false);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(1, builder.build());
    }

    private void removeNotification() {
        // Notification 제거
        NotificationManagerCompat.from(this.getContext()).cancel(1);
    }


}
