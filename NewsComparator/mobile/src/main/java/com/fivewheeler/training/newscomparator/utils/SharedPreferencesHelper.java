package com.fivewheeler.training.newscomparator.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 26/01/16.
 */
public class SharedPreferencesHelper {
    private static SharedPreferencesHelper mPrefHelper;
    private static String PREFS_FILE = "myprefs";
    private static String FIRST_LAUNCH = "firstlaunch";
    private static String FEED_SELECTIONS = "feedSelections";
    private static String NATIONAL_NEWS = "national";
    private static String INTERNATIONAL_NEWS = "International";
    private static String BUSINESS_NEWS = "Business";
    private static String SPORTS_NEWS = "Sports";
    private static String EDUCATION_NEWS = "Education";
    private static String HEALTH_NEWS = "Health";
    private static String TECHNOLOGY_NEWS = "Technology";

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private Context mContext;


    public static SharedPreferencesHelper getInstance(Context context){
        if(mPrefHelper == null){
            mPrefHelper = new SharedPreferencesHelper(context);

        }

        return mPrefHelper;
    }

    private SharedPreferencesHelper(Context context){
        mContext = context;
    }

    public Set<String> getNationalNewsFeeds(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getStringSet(NATIONAL_NEWS, new HashSet<String>());

    }

    public void setNationalNewsFeeds(Set<String> newsFeedsList){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        mEditor.putStringSet(NATIONAL_NEWS, newsFeedsList);
        mEditor.apply();

    }

    public Set<String> getInterNationalNewsFeeds(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getStringSet(INTERNATIONAL_NEWS, new HashSet<String>());

    }

    public void setInterNationalNewsFeeds(Set<String> newsFeedsList){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        mEditor.putStringSet(INTERNATIONAL_NEWS, newsFeedsList);
        mEditor.apply();

    }

    public Set<String> getBusinessNewsFeeds(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getStringSet(BUSINESS_NEWS, new HashSet<String>());

    }

    public void setBusinessNewsFeeds(Set<String> newsFeedsList){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        mEditor.putStringSet(BUSINESS_NEWS, newsFeedsList);
        mEditor.apply();

    }

    public Set<String> getSportsNewsFeeds(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getStringSet(SPORTS_NEWS, new HashSet<String>());

    }

    public void setSportsNewsFeeds(Set<String> newsFeedsList){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        mEditor.putStringSet(SPORTS_NEWS, newsFeedsList);
        mEditor.apply();

    }

    public Set<String> getHealthNewsFeeds(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getStringSet(HEALTH_NEWS, new HashSet<String>());

    }

    public void setHealthNewsFeeds(Set<String> newsFeedsList){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        mEditor.putStringSet(HEALTH_NEWS, newsFeedsList);
        mEditor.apply();

    }

    public Set<String> getEducationNewsFeeds(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getStringSet(EDUCATION_NEWS, new HashSet<String>());

    }

    public void setEducationNewsFeeds(Set<String> newsFeedsList){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        mEditor.putStringSet(EDUCATION_NEWS, newsFeedsList);
        mEditor.apply();

    }

    public Set<String> getTechnologyNewsFeeds(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getStringSet(TECHNOLOGY_NEWS, new HashSet<String>());

    }

    public void setTechnologyNewsFeeds(Set<String> newsFeedsList){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        mEditor.putStringSet(TECHNOLOGY_NEWS, newsFeedsList);
        mEditor.apply();

    }

    public boolean isFirstTimeLaunch(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getBoolean(FIRST_LAUNCH, true);

    }

    public void setFirstLaunch(boolean isLaunched){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        mEditor.putBoolean(FIRST_LAUNCH, isLaunched);
        mEditor.apply();

    }

    public Set<String> getFeedsSelected(){
        mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return mPrefs.getStringSet(FEED_SELECTIONS, new HashSet<String>());

    }

    public void setFeedSelections(List<Integer> feedsSelected){
        if(mPrefs==null){
            mPrefs = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        mEditor = mPrefs.edit();
        HashSet<String> feedList = new HashSet<String>();
        feedList.addAll(feedList);
        mEditor.putStringSet(FEED_SELECTIONS, feedList);
        mEditor.apply();

    }
}
