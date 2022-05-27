package com.lite.holistic_tracking;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String tag1, tag2, tag3;
    private long backKeyPressedTime = 0;

    public static String p_email;
    public static String p_password;
    public static int p_userID;
    public static String p_name;

    //권한
    private final int MY_PERMISSIONS_REQUEST_CAMERA=1001;
    // Run the pipeline and the model inference on GPU or CPU.
    private static final boolean RUN_ON_GPU = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permssionCheck!= PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "거절 시 휴대폰 내의 설정을 바꿔 주세요.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }



        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        //로그인한 사용자 정보 전역변수에 저장
//        p_name = bundle.getString("name");
//        p_email = bundle.getString("email");
//        p_password = bundle.getString("password");
//        p_userID = bundle.getInt("UserID");

        //바텀 네비게이션 이용하기
        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());

                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.navigation_2);
    }
    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }


        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        //페이지 옮기기
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                tag1=tag;
                fragment = new FragmentPage1();
            } else if (id == R.id.navigation_2){
                tag2=tag;
                fragment = new FragmentPage2();
            }else {
                tag3=tag;
                fragment = new FragmentPage3();
            }

            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        } else {
            if(fragment==fragmentManager.findFragmentByTag(tag2)) { //메인 페이지 항시 리플래쉬
                fragmentTransaction.remove(fragment);
                fragment = new FragmentPage2();
                fragmentTransaction.add(R.id.content_layout, fragment, tag2);
            }else {
                fragmentTransaction.show(fragment);
            }
        }

        clearBackStack();
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }

    public void toMain(int a){
        String tag0;
        String tag4="endtalk",tag5="workcard",tag6="worddict";
        if(a==0){tag0=tag4;}else if(a==1){tag0=tag5;}else{tag0=tag6;}
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        Fragment fragment = fragmentManager.findFragmentByTag(tag0);
        if (fragment == null) {
            if (a == 2) {
                fragment = new Fragment_WordDict();
            }
            else if(a==1){
                fragment = new Fragment_WordCard();
            }
            else{
                fragment = new Fragment_EndTalk();
            }
            fragmentTransaction.add(R.id.content_layout, fragment, tag0);
        }
        else {
            fragmentTransaction.show(fragment);
        }

        clearBackStack();
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack(null);//뒤로가기 눌렀을 떄 이전 프래그먼트로 이동가능
        fragmentTransaction.commitAllowingStateLoss();
    }


    //백스택 제거 작업
    private void clearBackStack() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    //키보드 내리기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //두번 눌러 종료
    @Override
    public void onBackPressed(){

        Toast t = Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                t.show();
            } else {
                t.cancel();
                finish();
            }
        } else {
            super.onBackPressed();
        }

    }

}