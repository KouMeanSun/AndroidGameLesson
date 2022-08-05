package com.gmy.gamelesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.gmy.gamelesson.adapter.MainListAdapter;
import com.gmy.gamelesson.onClickListener.RecyclerViewListOnItemClickListener;
import com.gmy.gamelesson.ui.AssertsActivity;
import com.gmy.gamelesson.ui.Audio2Activity;
import com.gmy.gamelesson.ui.AudioPlayActivity;
import com.gmy.gamelesson.ui.HexagonActivity;
import com.gmy.gamelesson.ui.PointLinesSurfaceActivity;
import com.gmy.gamelesson.ui.SDCardActivity;
import com.gmy.gamelesson.ui.SQLiteActivity;
import com.gmy.gamelesson.ui.SharedPreferencesActivity;
import com.gmy.gamelesson.ui.SurfaceActivity;
import com.gmy.gamelesson.ui.TrianglePairSurfaceActivity;
import com.gmy.gamelesson.ui.TriangleSurfaceViewActivity;
import com.gmy.gamelesson.utils.FileStorageHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends AppCompatActivity implements RecyclerViewListOnItemClickListener{
    private static final String TAG = "MainActivity";

    private RecyclerView mRvList;
    private List<String> dataList = new ArrayList<>();
    private MainListAdapter mListAdapter;

    private Context mContext;

    private String androidSummaryFileName = "AndroidSummary.txt";
    private String androidSummaryFullPath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.commonInit();
    }

    private void commonInit(){
        this.mContext = this;
        this.checkPermission();
        this.initData();
        this.initViews();
        this.copyFilesFromAssets(this);
    }

    private void initData(){
        this.dataList.add("音频播放");
        this.dataList.add("音频播放2");
        this.dataList.add("数据库");
        this.dataList.add("sd卡");
        this.dataList.add("assets");
        this.dataList.add("SharedPreferences");
        this.dataList.add("Surface");
        this.dataList.add("OpenGL Triangle");
        this.dataList.add("Points Lines");
        this.dataList.add("TrianglePair");
        this.dataList.add("Hexagon");
    }

    private void initViews(){
        this.mRvList = this.findViewById(R.id.rv_list);
        this.mRvList.setLayoutManager(new LinearLayoutManager(this));
        this.mRvList.setNestedScrollingEnabled(false);
        this.mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        this.mListAdapter = new MainListAdapter(this,this.mRvList,this.dataList);
        this.mListAdapter.itemClickListener = this;
        this.mRvList.setAdapter(this.mListAdapter);
    }

    @Override
    public void onListClick(View view, int position) {
        Log.d(TAG, "onListClick: 点击了列表 position:"+position);
        switch (position){
            case 0:
            {
                Intent audioIntent = new Intent(mContext, AudioPlayActivity.class);
                startActivity(audioIntent);
            }
                break;
            case 1:
            {
                Intent audio2Intent = new Intent(mContext, Audio2Activity.class);
                startActivity(audio2Intent);
            }
                break;

            case 2:
            {
                Intent sqlIntent = new Intent(mContext, SQLiteActivity.class);
                startActivity(sqlIntent);
            }
                break;
            case 3:
            {
                Intent sdcardIntent = new Intent(mContext, SDCardActivity.class);
                sdcardIntent.putExtra("androidSummaryFullPath",androidSummaryFullPath);
                startActivity(sdcardIntent);
            }
            break;
            case 4:
            {
                Intent assetsIntent = new Intent(mContext, AssertsActivity.class);
                startActivity(assetsIntent);
            }
            break;
            case 5:
            {
                Intent assetsIntent = new Intent(mContext, SharedPreferencesActivity.class);
                startActivity(assetsIntent);
            }
            break;
            case 6:
            {
                Intent surfaceIntent = new Intent(mContext, SurfaceActivity.class);
                startActivity(surfaceIntent);
            }
            break;
            case 7:
            {
                Intent triangleSurfaceIntent = new Intent(mContext, TriangleSurfaceViewActivity.class);
                startActivity(triangleSurfaceIntent);
            }
            break;
            case 8:
            {
                Intent pointsLinesSurfaceIntent = new Intent(mContext, PointLinesSurfaceActivity.class);
                startActivity(pointsLinesSurfaceIntent);
            }
            break;
            case 9:
            {
                Intent trianglePairIntent = new Intent(mContext, TrianglePairSurfaceActivity.class);
                startActivity(trianglePairIntent);
            }
            break;
            case 10:
            {
                Intent trianglePairIntent = new Intent(mContext, HexagonActivity.class);
                startActivity(trianglePairIntent);
            }
            break;
            default:

                break;
        }
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, 200);
                    return;
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && requestCode == 200) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "请在设置中打开摄像头和存储权限", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, 200);
                    return;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 200) {
            checkPermission();
        }
    }

    private  void copyFilesFromAssets(Context context){
        String cachePath = FileStorageHelper.getDiskCacheDir(context);

        String mnnPath = cachePath + "/game";
        FileStorageHelper.copyFilesFromAssets(context, androidSummaryFileName, mnnPath);

        androidSummaryFullPath = mnnPath + File.separator + androidSummaryFileName;

        Log.e(TAG,"androidSummaryFilePath:"+androidSummaryFullPath);



    }
}
