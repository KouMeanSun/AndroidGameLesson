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
import com.gmy.gamelesson.surfaceview.DrawHyperboloid2Activity;
import com.gmy.gamelesson.ui.AssertsActivity;
import com.gmy.gamelesson.ui.Audio2Activity;
import com.gmy.gamelesson.ui.AudioPlayActivity;
import com.gmy.gamelesson.ui.Ball2SurfaceActivity;
import com.gmy.gamelesson.ui.Ball3SurfaceActivity;
import com.gmy.gamelesson.ui.Ball4SurfaceActivity;
import com.gmy.gamelesson.ui.BallSurfaceActivity;
import com.gmy.gamelesson.ui.BallTexture2Activity;
import com.gmy.gamelesson.ui.BallTextureActivity;
import com.gmy.gamelesson.ui.Blend2Activity;
import com.gmy.gamelesson.ui.Blend3Activity;
import com.gmy.gamelesson.ui.BlendActivity;
import com.gmy.gamelesson.ui.Chapter11FourActivity;
import com.gmy.gamelesson.ui.Chapter11OneActivity;
import com.gmy.gamelesson.ui.Chapter11ThreeActivity;
import com.gmy.gamelesson.ui.Chapter11TwoActivity;
import com.gmy.gamelesson.ui.Cube2Activity;
import com.gmy.gamelesson.ui.CubeColorRectActivity;
import com.gmy.gamelesson.ui.CubeIndex2SurfaceActivity;
import com.gmy.gamelesson.ui.CubeVertexSurfaceActivity;
import com.gmy.gamelesson.ui.DrawCircleActivity;
import com.gmy.gamelesson.ui.DrawCirque2Activity;
import com.gmy.gamelesson.ui.DrawCirqueActivity;
import com.gmy.gamelesson.ui.DrawCylinder2Activity;
import com.gmy.gamelesson.ui.DrawCylinder3Activity;
import com.gmy.gamelesson.ui.DrawCylinderActivity;
import com.gmy.gamelesson.ui.DrawHelicoidSurface2Activity;
import com.gmy.gamelesson.ui.DrawHelicoidSurfaceActivity;
import com.gmy.gamelesson.ui.DrawParaboloid2Activity;
import com.gmy.gamelesson.ui.DrawTaper2Activity;
import com.gmy.gamelesson.ui.DrawTaperActivity;
import com.gmy.gamelesson.ui.HexagonActivity;
import com.gmy.gamelesson.ui.PointLinesSurfaceActivity;
import com.gmy.gamelesson.ui.SDCardActivity;
import com.gmy.gamelesson.ui.SQLiteActivity;
import com.gmy.gamelesson.ui.SharedPreferencesActivity;
import com.gmy.gamelesson.ui.Spheroid2Activity;
import com.gmy.gamelesson.ui.Spheroid3Activity;
import com.gmy.gamelesson.ui.SpheroidActivity;
import com.gmy.gamelesson.ui.SurfaceActivity;
import com.gmy.gamelesson.ui.TextureActivity;
import com.gmy.gamelesson.ui.TextureRectActivity;
import com.gmy.gamelesson.ui.TrianglePairSurfaceActivity;
import com.gmy.gamelesson.ui.TriangleSurfaceViewActivity;
import com.gmy.gamelesson.ui.WorldActivity;
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
        this.dataList.add("Audio");
        this.dataList.add("Audio2");
        this.dataList.add("SQLite");
        this.dataList.add("sd卡");
        this.dataList.add("assets");
        this.dataList.add("SharedPreferences");
        this.dataList.add("Surface");
        this.dataList.add("OpenGL Triangle");
        this.dataList.add("Points Lines");
        this.dataList.add("TrianglePair");
        this.dataList.add("Hexagon");
        this.dataList.add("Ball Light");
        this.dataList.add("Ball multiple Light");
        this.dataList.add("ambient diffused specular shininess Material Light ");
        this.dataList.add("ambient diffused specular shininess Material Light 2");
        this.dataList.add("ambient diffused specular shininess Material Light 3");
        this.dataList.add("ambient diffused specular shininess Material Light 4");
        this.dataList.add("Texture");
        this.dataList.add("Ball Texture");
        this.dataList.add("Ball earth moon");
        this.dataList.add("Texture Rect");
        this.dataList.add("Cube Color Rect");
        this.dataList.add("Cylinder");
        this.dataList.add("Cylinder2 ");
        this.dataList.add("DrawTaper");
        this.dataList.add("DrawTaper2 ");
        this.dataList.add("Cirque");
        this.dataList.add("Cirque2");
        this.dataList.add("Cylinder3");
        this.dataList.add("Parabolold2");
        this.dataList.add("DrawCircle");
        this.dataList.add("Hyperboloid2");
        this.dataList.add("HelicoidSurface");
        this.dataList.add("HelicoidSurface2");
        this.dataList.add("Spheroid");
        this.dataList.add("Spheroid2");
        this.dataList.add("Spheroid3");
        this.dataList.add("Cube2");
        this.dataList.add("World");
        //第十章
        this.dataList.add("Blend");
        this.dataList.add("Blend2");
        this.dataList.add("Blend3");
        //第十一章
        this.dataList.add("Chapter11 one");
        this.dataList.add("Chapter11 two");
        this.dataList.add("Chapter11 three");
        this.dataList.add("Chapter11 four");
    }

    private void initViews(){
        this.mRvList = this.findViewById(R.id.rv_list);
        this.mRvList.setLayoutManager(new LinearLayoutManager(this));
        this.mRvList.setNestedScrollingEnabled(true);
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
            case 11:
            {
                Intent ballLightIntent = new Intent(mContext, BallSurfaceActivity.class);
                startActivity(ballLightIntent);
            }
            break;
            case 12:
            {
                Intent ball2LightIntent = new Intent(mContext, Ball2SurfaceActivity.class);
                startActivity(ball2LightIntent);
            }
            break;
            case 13:
            {
                Intent ball3LightIntent = new Intent(mContext, Ball3SurfaceActivity.class);
                startActivity(ball3LightIntent);
            }
            break;
            case 14:
            {
                Intent ball4LightIntent = new Intent(mContext, Ball4SurfaceActivity.class);
                startActivity(ball4LightIntent);
            }
            break;
            case 15:
            {
                Intent cubeLightIntent = new Intent(mContext, CubeVertexSurfaceActivity.class);
                startActivity(cubeLightIntent);
            }
            break;

            case 16:
            {
                Intent cube2LightIntent = new Intent(mContext, CubeIndex2SurfaceActivity.class);
                startActivity(cube2LightIntent);
            }
            break;

            case 17:
            {
                Intent textureIntent = new Intent(mContext, TextureActivity.class);
                startActivity(textureIntent);
            }
            break;
            case 18:
            {
                Intent balltextureIntent = new Intent(mContext, BallTextureActivity.class);
                startActivity(balltextureIntent);
            }
            break;
            case 19:
            {
                Intent balltexture2Intent = new Intent(mContext, BallTexture2Activity.class);
                startActivity(balltexture2Intent);
            }
            break;
            case 20:
            {
                Intent texture2RectIntent = new Intent(mContext, TextureRectActivity.class);
                startActivity(texture2RectIntent);
            }
            break;
            case 21:
            {
                Intent cubeRectIntent = new Intent(mContext, CubeColorRectActivity.class);
                startActivity(cubeRectIntent);
            }
            break;
            case 22:
            {
                Intent cubeRectIntent = new Intent(mContext, DrawCylinderActivity.class);
                startActivity(cubeRectIntent);
            }
            break;
            case 23:
            {
                Intent cubeRectIntent = new Intent(mContext, DrawCylinder2Activity.class);
                startActivity(cubeRectIntent);
            }
            break;
            case 24:
            {
                Intent DrawTaperIntent = new Intent(mContext, DrawTaperActivity.class);
                startActivity(DrawTaperIntent);
            }
            break;
            case 25:
            {
                Intent DrawTaper2Intent = new Intent(mContext, DrawTaper2Activity.class);
                startActivity(DrawTaper2Intent);
            }
            break;
            case 26:
            {
                Intent DrawCirqueIntent = new Intent(mContext, DrawCirqueActivity.class);
                startActivity(DrawCirqueIntent);
            }
            break;
            case 27:
            {
                Intent DrawCirque2Intent = new Intent(mContext, DrawCirque2Activity.class);
                startActivity(DrawCirque2Intent);
            }
            break;
            case 28:
            {
                Intent DrawCylinder3Intent = new Intent(mContext, DrawCylinder3Activity.class);
                startActivity(DrawCylinder3Intent);
            }
            break;
            case 29:
            {
                Intent DrawParaboloid2Intent = new Intent(mContext, DrawParaboloid2Activity.class);
                startActivity(DrawParaboloid2Intent);
            }
            break;
            case 30:
            {
                Intent DrawCircleIntent = new Intent(mContext, DrawCircleActivity.class);
                startActivity(DrawCircleIntent);
            }
            break;
            case 31:
            {
                Intent DrawHyperboloid2Intent = new Intent(mContext, DrawHyperboloid2Activity.class);
                startActivity(DrawHyperboloid2Intent);
            }
            break;
            case 32:
            {
                Intent DrawHelicoidSurfaceIntent = new Intent(mContext, DrawHelicoidSurfaceActivity.class);
                startActivity(DrawHelicoidSurfaceIntent);
            }
            break;
            case 33:
            {
                Intent DrawHelicoidSurface2Intent = new Intent(mContext, DrawHelicoidSurface2Activity.class);
                startActivity(DrawHelicoidSurface2Intent);
            }
            break;
            case 34:
            {
                Intent SpheroidSurface2Intent = new Intent(mContext, SpheroidActivity.class);
                startActivity(SpheroidSurface2Intent);
            }
            break;
            case 35:
            {
                Intent Spheroid2Surface2Intent = new Intent(mContext, Spheroid2Activity.class);
                startActivity(Spheroid2Surface2Intent);
            }
            break;
            case 36:
            {
                Intent Spheroid3Surface2Intent = new Intent(mContext, Spheroid3Activity.class);
                startActivity(Spheroid3Surface2Intent);
            }
            break;
            case 37:
            {
                Intent cube2Intent = new Intent(mContext, Cube2Activity.class);
                startActivity(cube2Intent);
            }
            break;
            case 38:
            {
                Intent wordIntent = new Intent(mContext, WorldActivity.class);
                startActivity(wordIntent);
            }
            break;
            case 39:
            {
                Intent blendIntent = new Intent(mContext, BlendActivity.class);
                startActivity(blendIntent);
            }
            break;
            case 40:
            {
                Intent blend2Intent = new Intent(mContext, Blend2Activity.class);
                startActivity(blend2Intent);
            }
            break;
            case 41:
            {
                Intent blend3Intent = new Intent(mContext, Blend3Activity.class);
                startActivity(blend3Intent);
            }
            break;
            case 42:
            {
                Intent Intent = new Intent(mContext, Chapter11OneActivity.class);
                startActivity(Intent);
            }
            break;
            case 43:
            {
                Intent Intent = new Intent(mContext, Chapter11TwoActivity.class);
                startActivity(Intent);
            }
            break;
            case 44:
            {
                Intent Intent = new Intent(mContext, Chapter11ThreeActivity.class);
                startActivity(Intent);
            }
            break;
            case 45:
            {
                Intent Intent = new Intent(mContext, Chapter11FourActivity.class);
                startActivity(Intent);
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
