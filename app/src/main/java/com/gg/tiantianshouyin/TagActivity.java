package com.gg.tiantianshouyin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
import com.ximalaya.ting.android.opensdk.model.tag.Tag;
import com.ximalaya.ting.android.opensdk.model.tag.TagList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 2017/7/4.
 */

public class TagActivity extends Activity {
    public  static final String APP_SECRET = "2d5e40f87904d6cb292b6388e82680a7";
    private static final String TAG = "zhangmin";

    public static String CATEGORY_ID = "0";

    private List<Tag>  mTagList = new ArrayList<>();

    private List<Category> mCategoryList  = new ArrayList<>();

    private TagAdapter mTagAdapter;

    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "执行到onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tag);
        Log.d(TAG, "执行到onCreate");
        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_tag);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);


        // sdk初始化
        CommonRequest.getInstanse().init(this, APP_SECRET);

        Intent i = getIntent();
        String data = i.getStringExtra("category");
        Log.d("执行",  "YES");
        switch (data){
            case "music":
                Log.d("执行",  "NO");
                /**
                 * 获取分类列表
                 */

                CommonRequest.getCategories(null, new IDataCallBack<CategoryList>() {
                    @Override
                    public void onSuccess(CategoryList list) {
                        Log.d("执行",  "ONSUCCESS");

                        mCategoryList = list.getCategories();
                        Log.d(TAG, "*****************所有分类列表**************");
                        for (Category c : mCategoryList) {
                            Log.d(TAG, "CategoryName: " + c.getCategoryName() + ",Id: " + c.getId());
                        }
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.d(TAG, "获取喜马拉雅FM内容分类失败");
                    }
                });

                Log.d("执行",  "准备执行标签");
                /**
                 * 获取标签列表
                 *
                 */

                Map<String, String> tagDesc = new HashMap<String, String>();
                tagDesc.put(DTransferConstants.CATEGORY_ID, "2");
                tagDesc.put(DTransferConstants.TYPE, "0");
                CommonRequest.getTags(tagDesc, new IDataCallBack<TagList>() {
                    @Override
                    public void onSuccess(TagList list) {
                        Log.d("执行",  "ONSUCCESS标签");
                        mTagList = list.getTagList();
                        Log.d(TAG, "*****************音乐分类标签列表**************");
                        for (Tag t : mTagList) {
                            Log.d(TAG, "TagName: " + t.getTagName());
                        }

                        CATEGORY_ID = "2";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG,"执行线程");
                                mTagAdapter = new TagAdapter(mTagList);
                                recyclerView.setAdapter(mTagAdapter);
                                Log.d(TAG,"执行线程完成");

                            }
                        });
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                break;

            case "children":

                /**
                 * 获取标签列表
                 *
                 */

                Map<String, String> tag_children = new HashMap<String, String>();
                tag_children.put(DTransferConstants.CATEGORY_ID, "6");
                tag_children.put(DTransferConstants.TYPE, "0");
                CommonRequest.getTags(tag_children, new IDataCallBack<TagList>() {
                    @Override
                    public void onSuccess(TagList list) {
                        Log.d("执行",  "ONSUCCESS标签");
                        mTagList = list.getTagList();
                        Log.d(TAG, "*****************音乐分类标签列表**************");
                        for (Tag t : mTagList) {
                            Log.d(TAG, "TagName: " + t.getTagName());
                        }

                        CATEGORY_ID = "6";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG,"执行线程");
                                mTagAdapter = new TagAdapter(mTagList);
                                recyclerView.setAdapter(mTagAdapter);
                                Log.d(TAG,"执行线程完成");

                            }
                        });



                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                break;

            case "humanity":

                /**
                 * 获取标签列表
                 *
                 */

                Map<String, String> tag_humanity = new HashMap<String, String>();
                tag_humanity.put(DTransferConstants.CATEGORY_ID, "39");
                tag_humanity.put(DTransferConstants.TYPE, "0");
                CommonRequest.getTags(tag_humanity, new IDataCallBack<TagList>() {
                    @Override
                    public void onSuccess(TagList list) {
                        Log.d("执行",  "ONSUCCESS标签");
                        mTagList = list.getTagList();
                        Log.d(TAG, "*****************音乐分类标签列表**************");
                        for (Tag t : mTagList) {
                            Log.d(TAG, "TagName: " + t.getTagName());
                        }

                        CATEGORY_ID = "39";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG,"执行线程");
                                mTagAdapter = new TagAdapter(mTagList);
                                recyclerView.setAdapter(mTagAdapter);
                                Log.d(TAG,"执行线程完成");

                            }
                        });



                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                break;

            case "english":
                /**
                 * 获取分类列表
                 */


                /**
                 * 获取标签列表
                 *
                 */

                Map<String, String> tag_english = new HashMap<String, String>();
                tag_english.put(DTransferConstants.CATEGORY_ID, "38");
                tag_english.put(DTransferConstants.TYPE, "0");
                CommonRequest.getTags(tag_english, new IDataCallBack<TagList>() {
                    @Override
                    public void onSuccess(TagList list) {
                        Log.d("执行",  "ONSUCCESS标签");
                        mTagList = list.getTagList();
                        Log.d(TAG, "*****************音乐分类标签列表**************");
                        for (Tag t : mTagList) {
                            Log.d(TAG, "TagName: " + t.getTagName());
                        }

                        CATEGORY_ID = "38";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG,"执行线程");
                                mTagAdapter = new TagAdapter(mTagList);
                                recyclerView.setAdapter(mTagAdapter);
                                Log.d(TAG,"执行线程完成");

                            }
                        });
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
